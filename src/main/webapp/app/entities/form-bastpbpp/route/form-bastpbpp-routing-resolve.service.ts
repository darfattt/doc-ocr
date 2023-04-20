import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IFormBASTPBPP } from '../form-bastpbpp.model';
import { FormBASTPBPPService } from '../service/form-bastpbpp.service';

@Injectable({ providedIn: 'root' })
export class FormBASTPBPPRoutingResolveService implements Resolve<IFormBASTPBPP | null> {
  constructor(protected service: FormBASTPBPPService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFormBASTPBPP | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((formBASTPBPP: HttpResponse<IFormBASTPBPP>) => {
          if (formBASTPBPP.body) {
            return of(formBASTPBPP.body);
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
