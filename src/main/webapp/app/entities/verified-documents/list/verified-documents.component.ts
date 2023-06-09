import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Data, ParamMap, Router } from '@angular/router';
import { combineLatest, filter, Observable, switchMap, tap } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IVerifiedDocuments } from '../verified-documents.model';

import { ITEMS_PER_PAGE, PAGE_HEADER, TOTAL_COUNT_RESPONSE_HEADER } from 'app/config/pagination.constants';
import { ASC, DESC, SORT, ITEM_DELETED_EVENT, DEFAULT_SORT_DATA } from 'app/config/navigation.constants';
import { EntityArrayResponseType, VerifiedDocumentsService } from '../service/verified-documents.service';
import { VerifiedDocumentsDeleteDialogComponent } from '../delete/verified-documents-delete-dialog.component';
import { FilterOptions, IFilterOptions, IFilterOption } from 'app/shared/filter/filter.model';
import { DocumentsService } from 'app/entities/documents/service/documents.service';
import { ALL, DOC_TYPES, DOC_TYPES_NO, DOC_VERIFIED_STATUSES } from 'app/app.constants';

@Component({
  selector: 'jhi-verified-documents',
  templateUrl: './verified-documents.component.html',
})
export class VerifiedDocumentsComponent implements OnInit {
  verifiedDocuments?: IVerifiedDocuments[];
  isLoading = false;

  predicate = 'id';
  ascending = true;
  filters: IFilterOptions = new FilterOptions();

  itemsPerPage = ITEMS_PER_PAGE;
  totalItems = 0;
  page = 1;
  all: string = ALL;
  filterDocType: string = ALL;
  filterDocNumber: string = '';
  filterDocFileName: string = '';
  filterDocBranch: string = ALL;
  filterDocStatus: string = ALL;
  types: string[] = DOC_TYPES;
  typeNumbers: string[] = DOC_TYPES_NO;
  statuses: string[] = DOC_VERIFIED_STATUSES;

  constructor(
    protected verifiedDocumentsService: VerifiedDocumentsService,
    protected activatedRoute: ActivatedRoute,
    public router: Router,
    protected modalService: NgbModal,
    private http: HttpClient,
    protected documentsService: DocumentsService
  ) {}

  trackId = (_index: number, item: IVerifiedDocuments): string => this.verifiedDocumentsService.getVerifiedDocumentsIdentifier(item);

  ngOnInit(): void {
    this.load();
    this.filters.filterChanges.subscribe(filterOptions => this.handleNavigation(1, this.predicate, this.ascending, filterOptions));
  }

  delete(verifiedDocuments: IVerifiedDocuments): void {
    const modalRef = this.modalService.open(VerifiedDocumentsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.verifiedDocuments = verifiedDocuments;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed
      .pipe(
        filter(reason => reason === ITEM_DELETED_EVENT),
        switchMap(() => this.loadFromBackendWithRouteInformations())
      )
      .subscribe({
        next: (res: EntityArrayResponseType) => {
          this.onResponseSuccess(res);
        },
      });
  }

  load(): void {
    this.loadFromBackendWithRouteInformations().subscribe({
      next: (res: EntityArrayResponseType) => {
        this.onResponseSuccess(res);
      },
    });
  }

  navigateToWithComponentValues(): void {
    this.handleNavigation(this.page, this.predicate, this.ascending, this.filters.filterOptions);
  }

  navigateToPage(page = this.page): void {
    this.handleNavigation(page, this.predicate, this.ascending, this.filters.filterOptions);
  }

  protected loadFromBackendWithRouteInformations(): Observable<EntityArrayResponseType> {
    return combineLatest([this.activatedRoute.queryParamMap, this.activatedRoute.data]).pipe(
      tap(([params, data]) => this.fillComponentAttributeFromRoute(params, data)),
      switchMap(() => this.queryBackend(this.page, this.predicate, this.ascending, this.filters.filterOptions))
    );
  }

  protected fillComponentAttributeFromRoute(params: ParamMap, data: Data): void {
    const page = params.get(PAGE_HEADER);
    this.page = +(page ?? 1);
    const sort = (params.get(SORT) ?? data[DEFAULT_SORT_DATA]).split(',');
    this.predicate = sort[0];
    this.ascending = sort[1] === ASC;
    this.filters.initializeFromParams(params);
  }

  protected onResponseSuccess(response: EntityArrayResponseType): void {
    this.fillComponentAttributesFromResponseHeader(response.headers);
    const dataFromBody = this.fillComponentAttributesFromResponseBody(response.body);
    this.verifiedDocuments = dataFromBody;
  }

  protected fillComponentAttributesFromResponseBody(data: IVerifiedDocuments[] | null): IVerifiedDocuments[] {
    return data ?? [];
  }

  protected fillComponentAttributesFromResponseHeader(headers: HttpHeaders): void {
    this.totalItems = Number(headers.get(TOTAL_COUNT_RESPONSE_HEADER));
  }

  protected queryBackend(
    page?: number,
    predicate?: string,
    ascending?: boolean,
    filterOptions?: IFilterOption[]
  ): Observable<EntityArrayResponseType> {
    this.isLoading = true;
    const pageToLoad: number = page ?? 1;
    const queryObject: any = {
      page: pageToLoad - 1,
      size: this.itemsPerPage,
      sort: this.getSortQueryParam(predicate, ascending),
    };
    //queryObject['status.equals'] = 'VERIFIED';
    filterOptions?.forEach(filterOption => {
      queryObject[filterOption.name] = filterOption.values;
    });
    if (this.filterDocNumber) {
      queryObject['id.contains'] = this.filterDocNumber;
    }
    if (this.filterDocType !== ALL) {
      queryObject['type.contains'] = this.filterDocType;
    }
    if (this.filterDocFileName) {
      queryObject['name.contains'] = this.filterDocFileName;
    }
    if (this.filterDocBranch !== ALL) {
      // queryObject['branch.contains'] = this.filterDocBranch;
    }
    if (this.filterDocStatus !== ALL) {
      queryObject['status.contains'] = this.filterDocStatus;
    }

    return this.verifiedDocumentsService.query(queryObject).pipe(tap(() => (this.isLoading = false)));
  }

  protected handleNavigation(page = this.page, predicate?: string, ascending?: boolean, filterOptions?: IFilterOption[]): void {
    const queryParamsObj: any = {
      page,
      size: this.itemsPerPage,
      sort: this.getSortQueryParam(predicate, ascending),
    };

    filterOptions?.forEach(filterOption => {
      queryParamsObj[filterOption.nameAsQueryParam()] = filterOption.values;
    });

    this.router.navigate(['./'], {
      relativeTo: this.activatedRoute,
      queryParams: queryParamsObj,
    });
  }

  protected getSortQueryParam(predicate = this.predicate, ascending = this.ascending): string[] {
    const ascendingQueryParam = ascending ? ASC : DESC;
    if (predicate === '') {
      return [];
    } else {
      return [predicate + ',' + ascendingQueryParam];
    }
  }

  search() {
    console.log('search');
    this.load();
  }

  async download(doc: IVerifiedDocuments) {
    if (doc && doc.attachmentGroupId) {
      const req = this.documentsService.downloadAttachment(doc.attachmentGroupId);
      this.http.request(req).subscribe((res: any) => {
        if (res instanceof HttpResponse) {
          if (res.body) {
            console.log(res.headers);
            const a: any = document.createElement('a');
            a.href = window.URL.createObjectURL(res.body);
            a.target = '_blank';
            a.download = 'document-' + new Date().getTime();
            document.body.appendChild(a);
            a.click();
          }
        }
      });
    }
  }

  getViewEditUrl(type: any) {
    let viewEditUrl = '';
    if (type == DOC_TYPES_NO[0]) {
      viewEditUrl = '/form-pengeluaran-barang';
    } else if (type == DOC_TYPES_NO[1]) {
      viewEditUrl = '/form-surat-jalan';
    } else if (type == DOC_TYPES_NO[2]) {
      viewEditUrl = '/form-pernyataan';
    } else if (type == DOC_TYPES_NO[3]) {
      viewEditUrl = '/form-bastpbpp';
    } else if (type == DOC_TYPES_NO[4]) {
      viewEditUrl = '/form-bastpbp';
    }
    return viewEditUrl;
  }
}
