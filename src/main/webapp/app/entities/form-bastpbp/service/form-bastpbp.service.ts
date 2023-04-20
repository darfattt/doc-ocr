import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IFormBASTPBP, NewFormBASTPBP } from '../form-bastpbp.model';

export type PartialUpdateFormBASTPBP = Partial<IFormBASTPBP> & Pick<IFormBASTPBP, 'id'>;

type RestOf<T extends IFormBASTPBP | NewFormBASTPBP> = Omit<T, 'createdDate' | 'lastModifiedDate'> & {
  createdDate?: string | null;
  lastModifiedDate?: string | null;
};

export type RestFormBASTPBP = RestOf<IFormBASTPBP>;

export type NewRestFormBASTPBP = RestOf<NewFormBASTPBP>;

export type PartialUpdateRestFormBASTPBP = RestOf<PartialUpdateFormBASTPBP>;

export type EntityResponseType = HttpResponse<IFormBASTPBP>;
export type EntityArrayResponseType = HttpResponse<IFormBASTPBP[]>;

@Injectable({ providedIn: 'root' })
export class FormBASTPBPService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/form-bastpbps');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(formBASTPBP: NewFormBASTPBP): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(formBASTPBP);
    return this.http
      .post<RestFormBASTPBP>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(formBASTPBP: IFormBASTPBP): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(formBASTPBP);
    return this.http
      .put<RestFormBASTPBP>(`${this.resourceUrl}/${this.getFormBASTPBPIdentifier(formBASTPBP)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(formBASTPBP: PartialUpdateFormBASTPBP): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(formBASTPBP);
    return this.http
      .patch<RestFormBASTPBP>(`${this.resourceUrl}/${this.getFormBASTPBPIdentifier(formBASTPBP)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestFormBASTPBP>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestFormBASTPBP[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getFormBASTPBPIdentifier(formBASTPBP: Pick<IFormBASTPBP, 'id'>): string {
    return formBASTPBP.id;
  }

  compareFormBASTPBP(o1: Pick<IFormBASTPBP, 'id'> | null, o2: Pick<IFormBASTPBP, 'id'> | null): boolean {
    return o1 && o2 ? this.getFormBASTPBPIdentifier(o1) === this.getFormBASTPBPIdentifier(o2) : o1 === o2;
  }

  addFormBASTPBPToCollectionIfMissing<Type extends Pick<IFormBASTPBP, 'id'>>(
    formBASTPBPCollection: Type[],
    ...formBASTPBPSToCheck: (Type | null | undefined)[]
  ): Type[] {
    const formBASTPBPS: Type[] = formBASTPBPSToCheck.filter(isPresent);
    if (formBASTPBPS.length > 0) {
      const formBASTPBPCollectionIdentifiers = formBASTPBPCollection.map(
        formBASTPBPItem => this.getFormBASTPBPIdentifier(formBASTPBPItem)!
      );
      const formBASTPBPSToAdd = formBASTPBPS.filter(formBASTPBPItem => {
        const formBASTPBPIdentifier = this.getFormBASTPBPIdentifier(formBASTPBPItem);
        if (formBASTPBPCollectionIdentifiers.includes(formBASTPBPIdentifier)) {
          return false;
        }
        formBASTPBPCollectionIdentifiers.push(formBASTPBPIdentifier);
        return true;
      });
      return [...formBASTPBPSToAdd, ...formBASTPBPCollection];
    }
    return formBASTPBPCollection;
  }

  protected convertDateFromClient<T extends IFormBASTPBP | NewFormBASTPBP | PartialUpdateFormBASTPBP>(formBASTPBP: T): RestOf<T> {
    return {
      ...formBASTPBP,
      createdDate: formBASTPBP.createdDate?.toJSON() ?? null,
      lastModifiedDate: formBASTPBP.lastModifiedDate?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restFormBASTPBP: RestFormBASTPBP): IFormBASTPBP {
    return {
      ...restFormBASTPBP,
      createdDate: restFormBASTPBP.createdDate ? dayjs(restFormBASTPBP.createdDate) : undefined,
      lastModifiedDate: restFormBASTPBP.lastModifiedDate ? dayjs(restFormBASTPBP.lastModifiedDate) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestFormBASTPBP>): HttpResponse<IFormBASTPBP> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestFormBASTPBP[]>): HttpResponse<IFormBASTPBP[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
