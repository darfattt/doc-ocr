import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IFormPernyataan, NewFormPernyataan } from '../form-pernyataan.model';

export type PartialUpdateFormPernyataan = Partial<IFormPernyataan> & Pick<IFormPernyataan, 'id'>;

type RestOf<T extends IFormPernyataan | NewFormPernyataan> = Omit<T, 'createdDate' | 'lastModifiedDate'> & {
  createdDate?: string | null;
  lastModifiedDate?: string | null;
};

export type RestFormPernyataan = RestOf<IFormPernyataan>;

export type NewRestFormPernyataan = RestOf<NewFormPernyataan>;

export type PartialUpdateRestFormPernyataan = RestOf<PartialUpdateFormPernyataan>;

export type EntityResponseType = HttpResponse<IFormPernyataan>;
export type EntityArrayResponseType = HttpResponse<IFormPernyataan[]>;

@Injectable({ providedIn: 'root' })
export class FormPernyataanService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/form-pernyataans');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(formPernyataan: NewFormPernyataan): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(formPernyataan);
    return this.http
      .post<RestFormPernyataan>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(formPernyataan: IFormPernyataan): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(formPernyataan);
    return this.http
      .put<RestFormPernyataan>(`${this.resourceUrl}/${this.getFormPernyataanIdentifier(formPernyataan)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(formPernyataan: PartialUpdateFormPernyataan): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(formPernyataan);
    return this.http
      .patch<RestFormPernyataan>(`${this.resourceUrl}/${this.getFormPernyataanIdentifier(formPernyataan)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestFormPernyataan>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestFormPernyataan[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getFormPernyataanIdentifier(formPernyataan: Pick<IFormPernyataan, 'id'>): string {
    return formPernyataan.id;
  }

  compareFormPernyataan(o1: Pick<IFormPernyataan, 'id'> | null, o2: Pick<IFormPernyataan, 'id'> | null): boolean {
    return o1 && o2 ? this.getFormPernyataanIdentifier(o1) === this.getFormPernyataanIdentifier(o2) : o1 === o2;
  }

  addFormPernyataanToCollectionIfMissing<Type extends Pick<IFormPernyataan, 'id'>>(
    formPernyataanCollection: Type[],
    ...formPernyataansToCheck: (Type | null | undefined)[]
  ): Type[] {
    const formPernyataans: Type[] = formPernyataansToCheck.filter(isPresent);
    if (formPernyataans.length > 0) {
      const formPernyataanCollectionIdentifiers = formPernyataanCollection.map(
        formPernyataanItem => this.getFormPernyataanIdentifier(formPernyataanItem)!
      );
      const formPernyataansToAdd = formPernyataans.filter(formPernyataanItem => {
        const formPernyataanIdentifier = this.getFormPernyataanIdentifier(formPernyataanItem);
        if (formPernyataanCollectionIdentifiers.includes(formPernyataanIdentifier)) {
          return false;
        }
        formPernyataanCollectionIdentifiers.push(formPernyataanIdentifier);
        return true;
      });
      return [...formPernyataansToAdd, ...formPernyataanCollection];
    }
    return formPernyataanCollection;
  }

  protected convertDateFromClient<T extends IFormPernyataan | NewFormPernyataan | PartialUpdateFormPernyataan>(
    formPernyataan: T
  ): RestOf<T> {
    return {
      ...formPernyataan,
      createdDate: formPernyataan.createdDate?.toJSON() ?? null,
      lastModifiedDate: formPernyataan.lastModifiedDate?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restFormPernyataan: RestFormPernyataan): IFormPernyataan {
    return {
      ...restFormPernyataan,
      createdDate: restFormPernyataan.createdDate ? dayjs(restFormPernyataan.createdDate) : undefined,
      lastModifiedDate: restFormPernyataan.lastModifiedDate ? dayjs(restFormPernyataan.lastModifiedDate) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestFormPernyataan>): HttpResponse<IFormPernyataan> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestFormPernyataan[]>): HttpResponse<IFormPernyataan[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
