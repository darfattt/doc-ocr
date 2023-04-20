import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { FormSuratJalanComponent } from '../list/form-surat-jalan.component';
import { FormSuratJalanDetailComponent } from '../detail/form-surat-jalan-detail.component';
import { FormSuratJalanUpdateComponent } from '../update/form-surat-jalan-update.component';
import { FormSuratJalanRoutingResolveService } from './form-surat-jalan-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const formSuratJalanRoute: Routes = [
  {
    path: '',
    component: FormSuratJalanComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: FormSuratJalanDetailComponent,
    resolve: {
      formSuratJalan: FormSuratJalanRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: FormSuratJalanUpdateComponent,
    resolve: {
      formSuratJalan: FormSuratJalanRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: FormSuratJalanUpdateComponent,
    resolve: {
      formSuratJalan: FormSuratJalanRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(formSuratJalanRoute)],
  exports: [RouterModule],
})
export class FormSuratJalanRoutingModule {}
