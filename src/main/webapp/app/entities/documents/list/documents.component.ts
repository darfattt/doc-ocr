import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpEvent, HttpClient, HttpHeaders } from '@angular/common/http';
import { ActivatedRoute, Data, ParamMap, Router } from '@angular/router';
import { combineLatest, filter, Observable, switchMap, tap } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { IDocuments } from '../documents.model';
import { ITEMS_PER_PAGE, PAGE_HEADER } from 'app/config/pagination.constants';
import { ASC, DESC, SORT, ITEM_DELETED_EVENT, DEFAULT_SORT_DATA } from 'app/config/navigation.constants';
import { EntityArrayResponseType, DocumentsService } from '../service/documents.service';
import { DocumentsDeleteDialogComponent } from '../delete/documents-delete-dialog.component';
import { FilterOptions, IFilterOptions, IFilterOption } from 'app/shared/filter/filter.model';
import { TOTAL_COUNT_RESPONSE_HEADER } from 'app/config/pagination.constants';
import { ALL, DOC_TYPES, DOC_TYPES_NO } from 'app/app.constants';

@Component({
  selector: 'jhi-documents',
  templateUrl: './documents.component.html',
})
export class DocumentsComponent implements OnInit {
  documents?: IDocuments[];
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
  types: string[] = DOC_TYPES;
  typeNumbers: string[] = DOC_TYPES_NO;

  constructor(
    protected documentsService: DocumentsService,
    protected activatedRoute: ActivatedRoute,
    public router: Router,
    protected modalService: NgbModal,
    private http: HttpClient
  ) {}

  trackId = (_index: number, item: IDocuments): string => this.documentsService.getDocumentsIdentifier(item);

  ngOnInit(): void {
    this.load();

    this.filters.filterChanges.subscribe(filterOptions => this.handleNavigation(1, this.predicate, this.ascending, filterOptions));
  }

  delete(documents: IDocuments): void {
    const modalRef = this.modalService.open(DocumentsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.documents = documents;
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
    this.documents = dataFromBody;
  }

  protected fillComponentAttributesFromResponseBody(data: IDocuments[] | null): IDocuments[] {
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
    queryObject['status.equals'] = 'NEW';
    if (this.filterDocNumber) {
      queryObject['id.contains'] = this.filterDocNumber;
    }
    if (this.filterDocType !== ALL) {
      queryObject['type.contains'] = this.filterDocType;
    }
    if (this.filterDocFileName) {
      queryObject['name.contains'] = this.filterDocFileName;
    }
    filterOptions?.forEach(filterOption => {
      queryObject[filterOption.name] = filterOption.values;
    });
    return this.documentsService.query(queryObject).pipe(tap(() => (this.isLoading = false)));
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

  async download(doc: IDocuments) {
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
}
