import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { FormSuratJalanFormService, FormSuratJalanFormGroup } from './form-surat-jalan-form.service';
import { IFormSuratJalan } from '../form-surat-jalan.model';
import { FormSuratJalanService } from '../service/form-surat-jalan.service';

@Component({
  selector: 'jhi-form-surat-jalan-update',
  templateUrl: './form-surat-jalan-update.component.html',
})
export class FormSuratJalanUpdateComponent implements OnInit {
  isSaving = false;
  formSuratJalan: IFormSuratJalan | null = null;

  editForm: FormSuratJalanFormGroup = this.formSuratJalanFormService.createFormSuratJalanFormGroup();

  constructor(
    protected formSuratJalanService: FormSuratJalanService,
    protected formSuratJalanFormService: FormSuratJalanFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ formSuratJalan }) => {
      this.formSuratJalan = formSuratJalan;
      if (formSuratJalan) {
        this.updateForm(formSuratJalan);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const formSuratJalan = this.formSuratJalanFormService.getFormSuratJalan(this.editForm);
    if (formSuratJalan.id !== null) {
      this.subscribeToSaveResponse(this.formSuratJalanService.update(formSuratJalan));
    } else {
      this.subscribeToSaveResponse(this.formSuratJalanService.create(formSuratJalan));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFormSuratJalan>>): void {
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

  protected updateForm(formSuratJalan: IFormSuratJalan): void {
    this.formSuratJalan = formSuratJalan;
    this.formSuratJalanFormService.resetForm(this.editForm, formSuratJalan);
  }
}
