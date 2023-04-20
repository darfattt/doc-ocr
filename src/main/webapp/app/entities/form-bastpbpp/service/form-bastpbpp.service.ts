import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IFormBASTPBPP, NewFormBASTPBPP } from '../form-bastpbpp.model';

export type PartialUpdateFormBASTPBPP = Partial<IFormBASTPBPP> & Pick<IFormBASTPBPP, 'id'>;

type RestOf<T extends IFormBASTPBPP | NewFormBASTPBPP> = Omit<T, 'createdDate' | 'lastModifiedDate'> & {
  createdDate?: string | null;
  lastModifiedDate?: string | null;
};

export type RestFormBASTPBPP = RestOf<IFormBASTPBPP>;

export type NewRestFormBASTPBPP = RestOf<NewFormBASTPBPP>;

export type PartialUpdateRestFormBASTPBPP = RestOf<PartialUpdateFormBASTPBPP>;

export type EntityResponseType = HttpResponse<IFormBASTPBPP>;
export type EntityArrayResponseType = HttpResponse<IFormBASTPBPP[]>;

@Injectable({ providedIn: 'root' })
export class FormBASTPBPPService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/form-bastpbpps');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(formBASTPBPP: NewFormBASTPBPP): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(formBASTPBPP);
    return this.http
      .post<RestFormBASTPBPP>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(formBASTPBPP: IFormBASTPBPP): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(formBASTPBPP);
    return this.http
      .put<RestFormBASTPBPP>(`${this.resourceUrl}/${this.getFormBASTPBPPIdentifier(formBASTPBPP)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(formBASTPBPP: PartialUpdateFormBASTPBPP): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(formBASTPBPP);
    return this.http
      .patch<RestFormBASTPBPP>(`${this.resourceUrl}/${this.getFormBASTPBPPIdentifier(formBASTPBPP)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestFormBASTPBPP>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestFormBASTPBPP[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getFormBASTPBPPIdentifier(formBASTPBPP: Pick<IFormBASTPBPP, 'id'>): string {
    return formBASTPBPP.id;
  }

  compareFormBASTPBPP(o1: Pick<IFormBASTPBPP, 'id'> | null, o2: Pick<IFormBASTPBPP, 'id'> | null): boolean {
    return o1 && o2 ? this.getFormBASTPBPPIdentifier(o1) === this.getFormBASTPBPPIdentifier(o2) : o1 === o2;
  }

  addFormBASTPBPPToCollectionIfMissing<Type extends Pick<IFormBASTPBPP, 'id'>>(
    formBASTPBPPCollection: Type[],
    ...formBASTPBPPSToCheck: (Type | null | undefined)[]
  ): Type[] {
    const formBASTPBPPS: Type[] = formBASTPBPPSToCheck.filter(isPresent);
    if (formBASTPBPPS.length > 0) {
      const formBASTPBPPCollectionIdentifiers = formBASTPBPPCollection.map(
        formBASTPBPPItem => this.getFormBASTPBPPIdentifier(formBASTPBPPItem)!
      );
      const formBASTPBPPSToAdd = formBASTPBPPS.filter(formBASTPBPPItem => {
        const formBASTPBPPIdentifier = this.getFormBASTPBPPIdentifier(formBASTPBPPItem);
        if (formBASTPBPPCollectionIdentifiers.includes(formBASTPBPPIdentifier)) {
          return false;
        }
        formBASTPBPPCollectionIdentifiers.push(formBASTPBPPIdentifier);
        return true;
      });
      return [...formBASTPBPPSToAdd, ...formBASTPBPPCollection];
    }
    return formBASTPBPPCollection;
  }

  protected convertDateFromClient<T extends IFormBASTPBPP | NewFormBASTPBPP | PartialUpdateFormBASTPBPP>(formBASTPBPP: T): RestOf<T> {
    return {
      ...formBASTPBPP,
      createdDate: formBASTPBPP.createdDate?.toJSON() ?? null,
      lastModifiedDate: formBASTPBPP.lastModifiedDate?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restFormBASTPBPP: RestFormBASTPBPP): IFormBASTPBPP {
    return {
      ...restFormBASTPBPP,
      createdDate: restFormBASTPBPP.createdDate ? dayjs(restFormBASTPBPP.createdDate) : undefined,
      lastModifiedDate: restFormBASTPBPP.lastModifiedDate ? dayjs(restFormBASTPBPP.lastModifiedDate) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestFormBASTPBPP>): HttpResponse<IFormBASTPBPP> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestFormBASTPBPP[]>): HttpResponse<IFormBASTPBPP[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
