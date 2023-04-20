import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { VerifiedDocumentsComponent } from './list/verified-documents.component';
import { VerifiedDocumentsDetailComponent } from './detail/verified-documents-detail.component';
import { VerifiedDocumentsUpdateComponent } from './update/verified-documents-update.component';
import { VerifiedDocumentsDeleteDialogComponent } from './delete/verified-documents-delete-dialog.component';
import { VerifiedDocumentsRoutingModule } from './route/verified-documents-routing.module';

@NgModule({
  imports: [SharedModule, VerifiedDocumentsRoutingModule],
  declarations: [
    VerifiedDocumentsComponent,
    VerifiedDocumentsDetailComponent,
    VerifiedDocumentsUpdateComponent,
    VerifiedDocumentsDeleteDialogComponent,
  ],
})
export class VerifiedDocumentsModule {}
