import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { DocumentsComponent } from './list/documents.component';
import { DocumentsDetailComponent } from './detail/documents-detail.component';
import { DocumentsUpdateComponent } from './update/documents-update.component';
import { DocumentsDeleteDialogComponent } from './delete/documents-delete-dialog.component';
import { DocumentsRoutingModule } from './route/documents-routing.module';
import { DocumentsUploadComponent } from './upload/documents-upload.component';

@NgModule({
  imports: [SharedModule, DocumentsRoutingModule],
  declarations: [
    DocumentsComponent,
    DocumentsDetailComponent,
    DocumentsUpdateComponent,
    DocumentsDeleteDialogComponent,
    DocumentsUploadComponent,
  ],
})
export class DocumentsModule {}
