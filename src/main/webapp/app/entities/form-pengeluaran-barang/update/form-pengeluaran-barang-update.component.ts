import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { FormPengeluaranBarangFormService, FormPengeluaranBarangFormGroup } from './form-pengeluaran-barang-form.service';
import { IFormPengeluaranBarang } from '../form-pengeluaran-barang.model';
import { FormPengeluaranBarangService } from '../service/form-pengeluaran-barang.service';

@Component({
  selector: 'jhi-form-pengeluaran-barang-update',
  templateUrl: './form-pengeluaran-barang-update.component.html',
})
export class FormPengeluaranBarangUpdateComponent implements OnInit {
  isSaving = false;
  formPengeluaranBarang: IFormPengeluaranBarang | null = null;

  editForm: FormPengeluaranBarangFormGroup = this.formPengeluaranBarangFormService.createFormPengeluaranBarangFormGroup();

  constructor(
    protected formPengeluaranBarangService: FormPengeluaranBarangService,
    protected formPengeluaranBarangFormService: FormPengeluaranBarangFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ formPengeluaranBarang }) => {
      this.formPengeluaranBarang = formPengeluaranBarang;
      if (formPengeluaranBarang) {
        this.updateForm(formPengeluaranBarang);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const formPengeluaranBarang = this.formPengeluaranBarangFormService.getFormPengeluaranBarang(this.editForm);
    if (formPengeluaranBarang.id !== null) {
      this.subscribeToSaveResponse(this.formPengeluaranBarangService.update(formPengeluaranBarang));
    } else {
      this.subscribeToSaveResponse(this.formPengeluaranBarangService.create(formPengeluaranBarang));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFormPengeluaranBarang>>): void {
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

  protected updateForm(formPengeluaranBarang: IFormPengeluaranBarang): void {
    this.formPengeluaranBarang = formPengeluaranBarang;
    this.formPengeluaranBarangFormService.resetForm(this.editForm, formPengeluaranBarang);
  }
}
