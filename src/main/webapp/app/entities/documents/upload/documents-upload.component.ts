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
import { AlertService } from 'app/core/util/alert.service';
import { DOC_TYPES, DOC_TYPES_NO } from 'app/app.constants';

@Component({
  selector: 'jhi-documents-upload',
  templateUrl: './documents-upload.component.html',
})
export class DocumentsUploadComponent implements OnInit {
  isSaving = false;
  documents: IDocuments | null = null;

  types: string[] = DOC_TYPES;
  typeNumbers: string[] = DOC_TYPES_NO;
  attachmentRequest = new AttachmentRequest();
  editForm: FormGroup;
  number: string = '';

  constructor(
    protected documentsService: DocumentsService,
    protected documentsFormService: DocumentsFormService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    private alertService: AlertService
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
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
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
    let alert: string | null = 'Upload document success';
    this.alertService.addAlert({
      type: 'success',
      message: alert,
    });
    // this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
    let alert: string | null = 'Upload document failed';
    this.alertService.addAlert({
      type: 'danger',
      message: alert,
    });
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
          // console.log(attachment);
          this.attachmentRequest.name = file.name;
          this.attachmentRequest.attachments = [];
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
