import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IFormBASTPBP } from '../form-bastpbp.model';
import { FormBASTPBPService } from '../service/form-bastpbp.service';

@Injectable({ providedIn: 'root' })
export class FormBASTPBPRoutingResolveService implements Resolve<IFormBASTPBP | null> {
  constructor(protected service: FormBASTPBPService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFormBASTPBP | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((formBASTPBP: HttpResponse<IFormBASTPBP>) => {
          if (formBASTPBP.body) {
            return of(formBASTPBP.body);
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
