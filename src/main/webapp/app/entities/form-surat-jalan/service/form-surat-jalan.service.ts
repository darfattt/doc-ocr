import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IFormSuratJalan, NewFormSuratJalan } from '../form-surat-jalan.model';

export type PartialUpdateFormSuratJalan = Partial<IFormSuratJalan> & Pick<IFormSuratJalan, 'id'>;

type RestOf<T extends IFormSuratJalan | NewFormSuratJalan> = Omit<T, 'createdDate' | 'lastModifiedDate'> & {
  createdDate?: string | null;
  lastModifiedDate?: string | null;
};

export type RestFormSuratJalan = RestOf<IFormSuratJalan>;

export type NewRestFormSuratJalan = RestOf<NewFormSuratJalan>;

export type PartialUpdateRestFormSuratJalan = RestOf<PartialUpdateFormSuratJalan>;

export type EntityResponseType = HttpResponse<IFormSuratJalan>;
export type EntityArrayResponseType = HttpResponse<IFormSuratJalan[]>;

@Injectable({ providedIn: 'root' })
export class FormSuratJalanService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/form-surat-jalans');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(formSuratJalan: NewFormSuratJalan): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(formSuratJalan);
    return this.http
      .post<RestFormSuratJalan>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(formSuratJalan: IFormSuratJalan): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(formSuratJalan);
    return this.http
      .put<RestFormSuratJalan>(`${this.resourceUrl}/${this.getFormSuratJalanIdentifier(formSuratJalan)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(formSuratJalan: PartialUpdateFormSuratJalan): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(formSuratJalan);
    return this.http
      .patch<RestFormSuratJalan>(`${this.resourceUrl}/${this.getFormSuratJalanIdentifier(formSuratJalan)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestFormSuratJalan>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestFormSuratJalan[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getFormSuratJalanIdentifier(formSuratJalan: Pick<IFormSuratJalan, 'id'>): string {
    return formSuratJalan.id;
  }

  compareFormSuratJalan(o1: Pick<IFormSuratJalan, 'id'> | null, o2: Pick<IFormSuratJalan, 'id'> | null): boolean {
    return o1 && o2 ? this.getFormSuratJalanIdentifier(o1) === this.getFormSuratJalanIdentifier(o2) : o1 === o2;
  }

  addFormSuratJalanToCollectionIfMissing<Type extends Pick<IFormSuratJalan, 'id'>>(
    formSuratJalanCollection: Type[],
    ...formSuratJalansToCheck: (Type | null | undefined)[]
  ): Type[] {
    const formSuratJalans: Type[] = formSuratJalansToCheck.filter(isPresent);
    if (formSuratJalans.length > 0) {
      const formSuratJalanCollectionIdentifiers = formSuratJalanCollection.map(
        formSuratJalanItem => this.getFormSuratJalanIdentifier(formSuratJalanItem)!
      );
      const formSuratJalansToAdd = formSuratJalans.filter(formSuratJalanItem => {
        const formSuratJalanIdentifier = this.getFormSuratJalanIdentifier(formSuratJalanItem);
        if (formSuratJalanCollectionIdentifiers.includes(formSuratJalanIdentifier)) {
          return false;
        }
        formSuratJalanCollectionIdentifiers.push(formSuratJalanIdentifier);
        return true;
      });
      return [...formSuratJalansToAdd, ...formSuratJalanCollection];
    }
    return formSuratJalanCollection;
  }

  protected convertDateFromClient<T extends IFormSuratJalan | NewFormSuratJalan | PartialUpdateFormSuratJalan>(
    formSuratJalan: T
  ): RestOf<T> {
    return {
      ...formSuratJalan,
      createdDate: formSuratJalan.createdDate?.toJSON() ?? null,
      lastModifiedDate: formSuratJalan.lastModifiedDate?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restFormSuratJalan: RestFormSuratJalan): IFormSuratJalan {
    return {
      ...restFormSuratJalan,
      createdDate: restFormSuratJalan.createdDate ? dayjs(restFormSuratJalan.createdDate) : undefined,
      lastModifiedDate: restFormSuratJalan.lastModifiedDate ? dayjs(restFormSuratJalan.lastModifiedDate) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestFormSuratJalan>): HttpResponse<IFormSuratJalan> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestFormSuratJalan[]>): HttpResponse<IFormSuratJalan[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
