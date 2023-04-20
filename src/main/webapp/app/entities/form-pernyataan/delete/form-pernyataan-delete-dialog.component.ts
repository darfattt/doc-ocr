import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IFormPernyataan } from '../form-pernyataan.model';
import { FormPernyataanService } from '../service/form-pernyataan.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './form-pernyataan-delete-dialog.component.html',
})
export class FormPernyataanDeleteDialogComponent {
  formPernyataan?: IFormPernyataan;

  constructor(protected formPernyataanService: FormPernyataanService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.formPernyataanService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
