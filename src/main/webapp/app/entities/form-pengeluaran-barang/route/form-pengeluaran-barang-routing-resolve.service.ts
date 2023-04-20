import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IFormPengeluaranBarang } from '../form-pengeluaran-barang.model';
import { FormPengeluaranBarangService } from '../service/form-pengeluaran-barang.service';

@Injectable({ providedIn: 'root' })
export class FormPengeluaranBarangRoutingResolveService implements Resolve<IFormPengeluaranBarang | null> {
  constructor(protected service: FormPengeluaranBarangService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFormPengeluaranBarang | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((formPengeluaranBarang: HttpResponse<IFormPengeluaranBarang>) => {
          if (formPengeluaranBarang.body) {
            return of(formPengeluaranBarang.body);
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
