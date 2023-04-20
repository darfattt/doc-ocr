import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IVerifiedDocuments } from '../verified-documents.model';
import { VerifiedDocumentsService } from '../service/verified-documents.service';

@Injectable({ providedIn: 'root' })
export class VerifiedDocumentsRoutingResolveService implements Resolve<IVerifiedDocuments | null> {
  constructor(protected service: VerifiedDocumentsService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IVerifiedDocuments | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((verifiedDocuments: HttpResponse<IVerifiedDocuments>) => {
          if (verifiedDocuments.body) {
            return of(verifiedDocuments.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(null);
  }
}
