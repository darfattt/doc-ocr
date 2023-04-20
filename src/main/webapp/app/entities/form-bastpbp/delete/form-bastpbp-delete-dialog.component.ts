import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IFormBASTPBP } from '../form-bastpbp.model';
import { FormBASTPBPService } from '../service/form-bastpbp.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './form-bastpbp-delete-dialog.component.html',
})
export class FormBASTPBPDeleteDialogComponent {
  formBASTPBP?: IFormBASTPBP;

  constructor(protected formBASTPBPService: FormBASTPBPService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.formBASTPBPService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
