import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IFormSuratJalan } from '../form-surat-jalan.model';
import { FormSuratJalanService } from '../service/form-surat-jalan.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './form-surat-jalan-delete-dialog.component.html',
})
export class FormSuratJalanDeleteDialogComponent {
  formSuratJalan?: IFormSuratJalan;

  constructor(protected formSuratJalanService: FormSuratJalanService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.formSuratJalanService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
