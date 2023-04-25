import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IDocuments } from '../documents.model';
import { DocumentsService } from '../service/documents.service';
import { DocumentsFormGroup, DocumentsFormService } from '../update/documents-form.service';

@Component({
  selector: 'jhi-documents-upload',
  templateUrl: './documents-upload.component.html',
})
export class DocumentsUploadComponent implements OnInit {
  isSaving = false;
  documents: IDocuments | null = null;

  editForm: DocumentsFormGroup = this.documentsFormService.createDocumentsFormGroup();
  types: string[] = ['Surat Jalan', 'Pengeluaran Barang', 'Pernyataan', 'BASTPBP', 'BASTPBPP'];

  constructor(
    protected documentsService: DocumentsService,
    protected documentsFormService: DocumentsFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ documents }) => {
      this.documents = documents;
      if (documents) {
        this.updateForm(documents);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const documents = this.documentsFormService.getDocuments(this.editForm);
    if (documents.id !== null) {
      this.subscribeToSaveResponse(this.documentsService.update(documents));
    } else {
      this.subscribeToSaveResponse(this.documentsService.create(documents));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDocuments>>): void {
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

  protected updateForm(documents: IDocuments): void {
    this.documents = documents;
    this.documentsFormService.resetForm(this.editForm, documents);
  }
}
