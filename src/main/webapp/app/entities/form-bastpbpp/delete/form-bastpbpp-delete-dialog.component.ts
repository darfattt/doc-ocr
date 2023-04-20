import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IFormBASTPBPP } from '../form-bastpbpp.model';
import { FormBASTPBPPService } from '../service/form-bastpbpp.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './form-bastpbpp-delete-dialog.component.html',
})
export class FormBASTPBPPDeleteDialogComponent {
  formBASTPBPP?: IFormBASTPBPP;

  constructor(protected formBASTPBPPService: FormBASTPBPPService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.formBASTPBPPService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
