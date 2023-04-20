import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { FormBASTPBPComponent } from '../list/form-bastpbp.component';
import { FormBASTPBPDetailComponent } from '../detail/form-bastpbp-detail.component';
import { FormBASTPBPUpdateComponent } from '../update/form-bastpbp-update.component';
import { FormBASTPBPRoutingResolveService } from './form-bastpbp-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const formBASTPBPRoute: Routes = [
  {
    path: '',
    component: FormBASTPBPComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: FormBASTPBPDetailComponent,
    resolve: {
      formBASTPBP: FormBASTPBPRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: FormBASTPBPUpdateComponent,
    resolve: {
      formBASTPBP: FormBASTPBPRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: FormBASTPBPUpdateComponent,
    resolve: {
      formBASTPBP: FormBASTPBPRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(formBASTPBPRoute)],
  exports: [RouterModule],
})
export class FormBASTPBPRoutingModule {}
