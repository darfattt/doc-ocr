import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IVerifiedDocuments, NewVerifiedDocuments } from '../verified-documents.model';

export type PartialUpdateVerifiedDocuments = Partial<IVerifiedDocuments> & Pick<IVerifiedDocuments, 'id'>;

type RestOf<T extends IVerifiedDocuments | NewVerifiedDocuments> = Omit<T, 'createdDate' | 'lastModifiedDate'> & {
  createdDate?: string | null;
  lastModifiedDate?: string | null;
};

export type RestVerifiedDocuments = RestOf<IVerifiedDocuments>;

export type NewRestVerifiedDocuments = RestOf<NewVerifiedDocuments>;

export type PartialUpdateRestVerifiedDocuments = RestOf<PartialUpdateVerifiedDocuments>;

export type EntityResponseType = HttpResponse<IVerifiedDocuments>;
export type EntityArrayResponseType = HttpResponse<IVerifiedDocuments[]>;

@Injectable({ providedIn: 'root' })
export class VerifiedDocumentsService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/verified-documents');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(verifiedDocuments: NewVerifiedDocuments): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(verifiedDocuments);
    return this.http
      .post<RestVerifiedDocuments>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(verifiedDocuments: IVerifiedDocuments): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(verifiedDocuments);
    return this.http
      .put<RestVerifiedDocuments>(`${this.resourceUrl}/${this.getVerifiedDocumentsIdentifier(verifiedDocuments)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(verifiedDocuments: PartialUpdateVerifiedDocuments): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(verifiedDocuments);
    return this.http
      .patch<RestVerifiedDocuments>(`${this.resourceUrl}/${this.getVerifiedDocumentsIdentifier(verifiedDocuments)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestVerifiedDocuments>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestVerifiedDocuments[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getVerifiedDocumentsIdentifier(verifiedDocuments: Pick<IVerifiedDocuments, 'id'>): string {
    return verifiedDocuments.id;
  }

  compareVerifiedDocuments(o1: Pick<IVerifiedDocuments, 'id'> | null, o2: Pick<IVerifiedDocuments, 'id'> | null): boolean {
    return o1 && o2 ? this.getVerifiedDocumentsIdentifier(o1) === this.getVerifiedDocumentsIdentifier(o2) : o1 === o2;
  }

  addVerifiedDocumentsToCollectionIfMissing<Type extends Pick<IVerifiedDocuments, 'id'>>(
    verifiedDocumentsCollection: Type[],
    ...verifiedDocumentsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const verifiedDocuments: Type[] = verifiedDocumentsToCheck.filter(isPresent);
    if (verifiedDocuments.length > 0) {
      const verifiedDocumentsCollectionIdentifiers = verifiedDocumentsCollection.map(
        verifiedDocumentsItem => this.getVerifiedDocumentsIdentifier(verifiedDocumentsItem)!
      );
      const verifiedDocumentsToAdd = verifiedDocuments.filter(verifiedDocumentsItem => {
        const verifiedDocumentsIdentifier = this.getVerifiedDocumentsIdentifier(verifiedDocumentsItem);
        if (verifiedDocumentsCollectionIdentifiers.includes(verifiedDocumentsIdentifier)) {
          return false;
        }
        verifiedDocumentsCollectionIdentifiers.push(verifiedDocumentsIdentifier);
        return true;
      });
      return [...verifiedDocumentsToAdd, ...verifiedDocumentsCollection];
    }
    return verifiedDocumentsCollection;
  }

  protected convertDateFromClient<T extends IVerifiedDocuments | NewVerifiedDocuments | PartialUpdateVerifiedDocuments>(
    verifiedDocuments: T
  ): RestOf<T> {
    return {
      ...verifiedDocuments,
      createdDate: verifiedDocuments.createdDate?.toJSON() ?? null,
      lastModifiedDate: verifiedDocuments.lastModifiedDate?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restVerifiedDocuments: RestVerifiedDocuments): IVerifiedDocuments {
    return {
      ...restVerifiedDocuments,
      createdDate: restVerifiedDocuments.createdDate ? dayjs(restVerifiedDocuments.createdDate) : undefined,
      lastModifiedDate: restVerifiedDocuments.lastModifiedDate ? dayjs(restVerifiedDocuments.lastModifiedDate) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestVerifiedDocuments>): HttpResponse<IVerifiedDocuments> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestVerifiedDocuments[]>): HttpResponse<IVerifiedDocuments[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
