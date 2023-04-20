import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { VerifiedDocumentsComponent } from '../list/verified-documents.component';
import { VerifiedDocumentsDetailComponent } from '../detail/verified-documents-detail.component';
import { VerifiedDocumentsUpdateComponent } from '../update/verified-documents-update.component';
import { VerifiedDocumentsRoutingResolveService } from './verified-documents-routing-resolve.service';
import { ASC } from 'app/config/navigation.constants';

const verifiedDocumentsRoute: Routes = [
  {
    path: '',
    component: VerifiedDocumentsComponent,
    data: {
      defaultSort: 'id,' + ASC,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: VerifiedDocumentsDetailComponent,
    resolve: {
      verifiedDocuments: VerifiedDocumentsRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: VerifiedDocumentsUpdateComponent,
    resolve: {
      verifiedDocuments: VerifiedDocumentsRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: VerifiedDocumentsUpdateComponent,
    resolve: {
      verifiedDocuments: VerifiedDocumentsRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(verifiedDocumentsRoute)],
  exports: [RouterModule],
})
export class VerifiedDocumentsRoutingModule {}
