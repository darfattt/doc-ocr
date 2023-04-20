import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { FormBASTPBPPComponent } from '../list/form-bastpbpp.component';
import { FormBASTPBPPDetailComponent } from '../detail/form-bastpbpp-detail.component';
import { FormBASTPBPPUpdateComponent } from '../update/form-bastpbpp-update.component';
import { FormBASTPBPPRoutingResolveService } from './form-bastpbpp-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const formBASTPBPPRoute: Routes = [
  {
    path: '',
    component: FormBASTPBPPComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: FormBASTPBPPDetailComponent,
    resolve: {
      formBASTPBPP: FormBASTPBPPRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: FormBASTPBPPUpdateComponent,
    resolve: {
      formBASTPBPP: FormBASTPBPPRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: FormBASTPBPPUpdateComponent,
    resolve: {
      formBASTPBPP: FormBASTPBPPRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(formBASTPBPPRoute)],
  exports: [RouterModule],
})
export class FormBASTPBPPRoutingModule {}
