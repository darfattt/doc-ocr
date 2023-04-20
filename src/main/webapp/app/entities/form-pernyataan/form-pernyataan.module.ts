import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { FormPernyataanComponent } from './list/form-pernyataan.component';
import { FormPernyataanDetailComponent } from './detail/form-pernyataan-detail.component';
import { FormPernyataanUpdateComponent } from './update/form-pernyataan-update.component';
import { FormPernyataanDeleteDialogComponent } from './delete/form-pernyataan-delete-dialog.component';
import { FormPernyataanRoutingModule } from './route/form-pernyataan-routing.module';

@NgModule({
  imports: [SharedModule, FormPernyataanRoutingModule],
  declarations: [
    FormPernyataanComponent,
    FormPernyataanDetailComponent,
    FormPernyataanUpdateComponent,
    FormPernyataanDeleteDialogComponent,
  ],
})
export class FormPernyataanModule {}
