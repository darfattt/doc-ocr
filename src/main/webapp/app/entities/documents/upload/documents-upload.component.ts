import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IDocuments } from '../documents.model';
import { DocumentsService } from '../service/documents.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Attachment, AttachmentRequest } from '../attachment.model';
import { DocumentsFormService } from '../update/documents-form.service';

@Component({
  selector: 'jhi-documents-upload',
  templateUrl: './documents-upload.component.html',
})
export class DocumentsUploadComponent implements OnInit {
  isSaving = false;
  documents: IDocuments | null = null;

  types: string[] = ['Surat Jalan', 'Pengeluaran Barang', 'Pernyataan', 'BASTPBP', 'BASTPBPP'];
  typeNumbers: string[] = ['001', '002', '003', '004', '005'];
  attachmentRequest = new AttachmentRequest();
  editForm: FormGroup;
  number: string = '';

  constructor(
    protected documentsService: DocumentsService,
    protected documentsFormService: DocumentsFormService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {
    this.editForm = this.fb.group(
      {
        type: ['', Validators.required],
        number: ['', Validators.required],
      },
      {}
    );
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ documents }) => {
      this.documents = documents;
      // if (documents) {
      //   this.updateForm(documents);
      // }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.attachmentRequest.name = this.attachmentRequest.number;
    this.isSaving = true;
    this.subscribeToSaveResponse(this.documentsService.upload(this.attachmentRequest));
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDocuments>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.attachmentRequest = new AttachmentRequest();
    this.editForm.reset();
    // this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  canSave() {
    let valid = this.attachmentRequest.attachments && this.attachmentRequest.attachments.length > 0;
    return valid;
  }

  handleUpload(event: any) {
    const file = event.target.files[0];
    if (file) {
      let attachment = this.convertFile2Attachment(file);
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = () => {
        // data:image/png;base64,iVBO
        // console.log(reader.result);
        attachment.blobFile = reader.result;
        if (attachment.blobFile) {
          attachment.blobFile = attachment.blobFile.split('base64,')[1];
          console.log(attachment);
          this.attachmentRequest.attachments?.push(attachment);
        }
      };
    }
  }

  convertFile2Attachment(file: any) {
    let attachment = new Attachment();
    attachment.name = file.name;
    attachment.type = file.type;
    attachment.size = file.size;
    return attachment;
  }
}
