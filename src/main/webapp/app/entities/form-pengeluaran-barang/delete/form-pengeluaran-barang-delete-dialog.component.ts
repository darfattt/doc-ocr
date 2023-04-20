import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IFormPengeluaranBarang } from '../form-pengeluaran-barang.model';
import { FormPengeluaranBarangService } from '../service/form-pengeluaran-barang.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './form-pengeluaran-barang-delete-dialog.component.html',
})
export class FormPengeluaranBarangDeleteDialogComponent {
  formPengeluaranBarang?: IFormPengeluaranBarang;

  constructor(protected formPengeluaranBarangService: FormPengeluaranBarangService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.formPengeluaranBarangService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
