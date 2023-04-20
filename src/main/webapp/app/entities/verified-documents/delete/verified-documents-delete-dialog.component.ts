import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IVerifiedDocuments } from '../verified-documents.model';
import { VerifiedDocumentsService } from '../service/verified-documents.service';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';

@Component({
  templateUrl: './verified-documents-delete-dialog.component.html',
})
export class VerifiedDocumentsDeleteDialogComponent {
  verifiedDocuments?: IVerifiedDocuments;

  constructor(protected verifiedDocumentsService: VerifiedDocumentsService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: string): void {
    this.verifiedDocumentsService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
