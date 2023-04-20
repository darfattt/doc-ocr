import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IFormSuratJalan } from '../form-surat-jalan.model';
import { FormSuratJalanService } from '../service/form-surat-jalan.service';

@Injectable({ providedIn: 'root' })
export class FormSuratJalanRoutingResolveService implements Resolve<IFormSuratJalan | null> {
  constructor(protected service: FormSuratJalanService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFormSuratJalan | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((formSuratJalan: HttpResponse<IFormSuratJalan>) => {
          if (formSuratJalan.body) {
            return of(formSuratJalan.body);
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
