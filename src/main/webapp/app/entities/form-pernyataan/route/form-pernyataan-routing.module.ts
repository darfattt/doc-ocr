import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { FormPernyataanComponent } from '../list/form-pernyataan.component';
import { FormPernyataanDetailComponent } from '../detail/form-pernyataan-detail.component';
import { FormPernyataanUpdateComponent } from '../update/form-pernyataan-update.component';
import { FormPernyataanRoutingResolveService } from './form-pernyataan-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const formPernyataanRoute: Routes = [
  {
    path: '',
    component: FormPernyataanComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: FormPernyataanDetailComponent,
    resolve: {
      formPernyataan: FormPernyataanRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: FormPernyataanUpdateComponent,
    resolve: {
      formPernyataan: FormPernyataanRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: FormPernyataanUpdateComponent,
    resolve: {
      formPernyataan: FormPernyataanRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(formPernyataanRoute)],
  exports: [RouterModule],
})
export class FormPernyataanRoutingModule {}
