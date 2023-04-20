import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IFormPengeluaranBarang, NewFormPengeluaranBarang } from '../form-pengeluaran-barang.model';

export type PartialUpdateFormPengeluaranBarang = Partial<IFormPengeluaranBarang> & Pick<IFormPengeluaranBarang, 'id'>;

type RestOf<T extends IFormPengeluaranBarang | NewFormPengeluaranBarang> = Omit<T, 'createdDate' | 'lastModifiedDate'> & {
  createdDate?: string | null;
  lastModifiedDate?: string | null;
};

export type RestFormPengeluaranBarang = RestOf<IFormPengeluaranBarang>;

export type NewRestFormPengeluaranBarang = RestOf<NewFormPengeluaranBarang>;

export type PartialUpdateRestFormPengeluaranBarang = RestOf<PartialUpdateFormPengeluaranBarang>;

export type EntityResponseType = HttpResponse<IFormPengeluaranBarang>;
export type EntityArrayResponseType = HttpResponse<IFormPengeluaranBarang[]>;

@Injectable({ providedIn: 'root' })
export class FormPengeluaranBarangService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/form-pengeluaran-barangs');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(formPengeluaranBarang: NewFormPengeluaranBarang): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(formPengeluaranBarang);
    return this.http
      .post<RestFormPengeluaranBarang>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(formPengeluaranBarang: IFormPengeluaranBarang): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(formPengeluaranBarang);
    return this.http
      .put<RestFormPengeluaranBarang>(`${this.resourceUrl}/${this.getFormPengeluaranBarangIdentifier(formPengeluaranBarang)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(formPengeluaranBarang: PartialUpdateFormPengeluaranBarang): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(formPengeluaranBarang);
    return this.http
      .patch<RestFormPengeluaranBarang>(`${this.resourceUrl}/${this.getFormPengeluaranBarangIdentifier(formPengeluaranBarang)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestFormPengeluaranBarang>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestFormPengeluaranBarang[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getFormPengeluaranBarangIdentifier(formPengeluaranBarang: Pick<IFormPengeluaranBarang, 'id'>): string {
    return formPengeluaranBarang.id;
  }

  compareFormPengeluaranBarang(o1: Pick<IFormPengeluaranBarang, 'id'> | null, o2: Pick<IFormPengeluaranBarang, 'id'> | null): boolean {
    return o1 && o2 ? this.getFormPengeluaranBarangIdentifier(o1) === this.getFormPengeluaranBarangIdentifier(o2) : o1 === o2;
  }

  addFormPengeluaranBarangToCollectionIfMissing<Type extends Pick<IFormPengeluaranBarang, 'id'>>(
    formPengeluaranBarangCollection: Type[],
    ...formPengeluaranBarangsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const formPengeluaranBarangs: Type[] = formPengeluaranBarangsToCheck.filter(isPresent);
    if (formPengeluaranBarangs.length > 0) {
      const formPengeluaranBarangCollectionIdentifiers = formPengeluaranBarangCollection.map(
        formPengeluaranBarangItem => this.getFormPengeluaranBarangIdentifier(formPengeluaranBarangItem)!
      );
      const formPengeluaranBarangsToAdd = formPengeluaranBarangs.filter(formPengeluaranBarangItem => {
        const formPengeluaranBarangIdentifier = this.getFormPengeluaranBarangIdentifier(formPengeluaranBarangItem);
        if (formPengeluaranBarangCollectionIdentifiers.includes(formPengeluaranBarangIdentifier)) {
          return false;
        }
        formPengeluaranBarangCollectionIdentifiers.push(formPengeluaranBarangIdentifier);
        return true;
      });
      return [...formPengeluaranBarangsToAdd, ...formPengeluaranBarangCollection];
    }
    return formPengeluaranBarangCollection;
  }

  protected convertDateFromClient<T extends IFormPengeluaranBarang | NewFormPengeluaranBarang | PartialUpdateFormPengeluaranBarang>(
    formPengeluaranBarang: T
  ): RestOf<T> {
    return {
      ...formPengeluaranBarang,
      createdDate: formPengeluaranBarang.createdDate?.toJSON() ?? null,
      lastModifiedDate: formPengeluaranBarang.lastModifiedDate?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restFormPengeluaranBarang: RestFormPengeluaranBarang): IFormPengeluaranBarang {
    return {
      ...restFormPengeluaranBarang,
      createdDate: restFormPengeluaranBarang.createdDate ? dayjs(restFormPengeluaranBarang.createdDate) : undefined,
      lastModifiedDate: restFormPengeluaranBarang.lastModifiedDate ? dayjs(restFormPengeluaranBarang.lastModifiedDate) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestFormPengeluaranBarang>): HttpResponse<IFormPengeluaranBarang> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestFormPengeluaranBarang[]>): HttpResponse<IFormPengeluaranBarang[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
