import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'documents',
        data: { pageTitle: 'Documents' },
        loadChildren: () => import('./documents/documents.module').then(m => m.DocumentsModule),
      },
      {
        path: 'verified-documents',
        data: { pageTitle: 'VerifiedDocuments' },
        loadChildren: () => import('./verified-documents/verified-documents.module').then(m => m.VerifiedDocumentsModule),
      },
      {
        path: 'form-surat-jalan',
        data: { pageTitle: 'FormSuratJalans' },
        loadChildren: () => import('./form-surat-jalan/form-surat-jalan.module').then(m => m.FormSuratJalanModule),
      },
      {
        path: 'form-pengeluaran-barang',
        data: { pageTitle: 'FormPengeluaranBarangs' },
        loadChildren: () => import('./form-pengeluaran-barang/form-pengeluaran-barang.module').then(m => m.FormPengeluaranBarangModule),
      },
      {
        path: 'form-pernyataan',
        data: { pageTitle: 'FormPernyataans' },
        loadChildren: () => import('./form-pernyataan/form-pernyataan.module').then(m => m.FormPernyataanModule),
      },
      {
        path: 'form-bastpbp',
        data: { pageTitle: 'FormBASTPBPS' },
        loadChildren: () => import('./form-bastpbp/form-bastpbp.module').then(m => m.FormBASTPBPModule),
      },
      {
        path: 'form-bastpbpp',
        data: { pageTitle: 'FormBASTPBPPS' },
        loadChildren: () => import('./form-bastpbpp/form-bastpbpp.module').then(m => m.FormBASTPBPPModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
