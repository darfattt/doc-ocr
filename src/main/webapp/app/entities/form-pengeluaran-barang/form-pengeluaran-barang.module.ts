import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { FormPengeluaranBarangComponent } from './list/form-pengeluaran-barang.component';
import { FormPengeluaranBarangDetailComponent } from './detail/form-pengeluaran-barang-detail.component';
import { FormPengeluaranBarangUpdateComponent } from './update/form-pengeluaran-barang-update.component';
import { FormPengeluaranBarangDeleteDialogComponent } from './delete/form-pengeluaran-barang-delete-dialog.component';
import { FormPengeluaranBarangRoutingModule } from './route/form-pengeluaran-barang-routing.module';

@NgModule({
  imports: [SharedModule, FormPengeluaranBarangRoutingModule],
  declarations: [
    FormPengeluaranBarangComponent,
    FormPengeluaranBarangDetailComponent,
    FormPengeluaranBarangUpdateComponent,
    FormPengeluaranBarangDeleteDialogComponent,
  ],
})
export class FormPengeluaranBarangModule {}
