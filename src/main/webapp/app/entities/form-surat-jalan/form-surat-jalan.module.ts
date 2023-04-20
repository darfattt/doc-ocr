import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { FormSuratJalanComponent } from './list/form-surat-jalan.component';
import { FormSuratJalanDetailComponent } from './detail/form-surat-jalan-detail.component';
import { FormSuratJalanUpdateComponent } from './update/form-surat-jalan-update.component';
import { FormSuratJalanDeleteDialogComponent } from './delete/form-surat-jalan-delete-dialog.component';
import { FormSuratJalanRoutingModule } from './route/form-surat-jalan-routing.module';

@NgModule({
  imports: [SharedModule, FormSuratJalanRoutingModule],
  declarations: [
    FormSuratJalanComponent,
    FormSuratJalanDetailComponent,
    FormSuratJalanUpdateComponent,
    FormSuratJalanDeleteDialogComponent,
  ],
})
export class FormSuratJalanModule {}
