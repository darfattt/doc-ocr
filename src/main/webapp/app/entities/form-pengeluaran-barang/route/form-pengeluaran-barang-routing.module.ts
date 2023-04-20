import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { FormPengeluaranBarangComponent } from '../list/form-pengeluaran-barang.component';
import { FormPengeluaranBarangDetailComponent } from '../detail/form-pengeluaran-barang-detail.component';
import { FormPengeluaranBarangUpdateComponent } from '../update/form-pengeluaran-barang-update.component';
import { FormPengeluaranBarangRoutingResolveService } from './form-pengeluaran-barang-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const formPengeluaranBarangRoute: Routes = [
  {
    path: '',
    component: FormPengeluaranBarangComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: FormPengeluaranBarangDetailComponent,
    resolve: {
      formPengeluaranBarang: FormPengeluaranBarangRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: FormPengeluaranBarangUpdateComponent,
    resolve: {
      formPengeluaranBarang: FormPengeluaranBarangRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: FormPengeluaranBarangUpdateComponent,
    resolve: {
      formPengeluaranBarang: FormPengeluaranBarangRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(formPengeluaranBarangRoute)],
  exports: [RouterModule],
})
export class FormPengeluaranBarangRoutingModule {}
