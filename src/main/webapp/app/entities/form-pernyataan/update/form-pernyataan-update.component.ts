import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { FormPernyataanFormService, FormPernyataanFormGroup } from './form-pernyataan-form.service';
import { IFormPernyataan } from '../form-pernyataan.model';
import { FormPernyataanService } from '../service/form-pernyataan.service';

@Component({
  selector: 'jhi-form-pernyataan-update',
  templateUrl: './form-pernyataan-update.component.html',
})
export class FormPernyataanUpdateComponent implements OnInit {
  isSaving = false;
  formPernyataan: IFormPernyataan | null = null;

  editForm: FormPernyataanFormGroup = this.formPernyataanFormService.createFormPernyataanFormGroup();

  constructor(
    protected formPernyataanService: FormPernyataanService,
    protected formPernyataanFormService: FormPernyataanFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ formPernyataan }) => {
      this.formPernyataan = formPernyataan;
      if (formPernyataan) {
        this.updateForm(formPernyataan);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const formPernyataan = this.formPernyataanFormService.getFormPernyataan(this.editForm);
    if (formPernyataan.id !== null) {
      this.subscribeToSaveResponse(this.formPernyataanService.update(formPernyataan));
    } else {
      this.subscribeToSaveResponse(this.formPernyataanService.create(formPernyataan));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFormPernyataan>>): void {
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

  protected updateForm(formPernyataan: IFormPernyataan): void {
    this.formPernyataan = formPernyataan;
    this.formPernyataanFormService.resetForm(this.editForm, formPernyataan);
  }
}
