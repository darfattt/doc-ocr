import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { VerifiedDocumentsFormService, VerifiedDocumentsFormGroup } from './verified-documents-form.service';
import { IVerifiedDocuments } from '../verified-documents.model';
import { VerifiedDocumentsService } from '../service/verified-documents.service';

@Component({
  selector: 'jhi-verified-documents-update',
  templateUrl: './verified-documents-update.component.html',
})
export class VerifiedDocumentsUpdateComponent implements OnInit {
  isSaving = false;
  verifiedDocuments: IVerifiedDocuments | null = null;

  editForm: VerifiedDocumentsFormGroup = this.verifiedDocumentsFormService.createVerifiedDocumentsFormGroup();

  constructor(
    protected verifiedDocumentsService: VerifiedDocumentsService,
    protected verifiedDocumentsFormService: VerifiedDocumentsFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ verifiedDocuments }) => {
      this.verifiedDocuments = verifiedDocuments;
      if (verifiedDocuments) {
        this.updateForm(verifiedDocuments);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const verifiedDocuments = this.verifiedDocumentsFormService.getVerifiedDocuments(this.editForm);
    if (verifiedDocuments.id !== null) {
      this.subscribeToSaveResponse(this.verifiedDocumentsService.update(verifiedDocuments));
    } else {
      this.subscribeToSaveResponse(this.verifiedDocumentsService.create(verifiedDocuments));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVerifiedDocuments>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(verifiedDocuments: IVerifiedDocuments): void {
    this.verifiedDocuments = verifiedDocuments;
    this.verifiedDocumentsFormService.resetForm(this.editForm, verifiedDocuments);
  }
}
