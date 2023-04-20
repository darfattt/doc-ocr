import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { FormBASTPBPFormService, FormBASTPBPFormGroup } from './form-bastpbp-form.service';
import { IFormBASTPBP } from '../form-bastpbp.model';
import { FormBASTPBPService } from '../service/form-bastpbp.service';

@Component({
  selector: 'jhi-form-bastpbp-update',
  templateUrl: './form-bastpbp-update.component.html',
})
export class FormBASTPBPUpdateComponent implements OnInit {
  isSaving = false;
  formBASTPBP: IFormBASTPBP | null = null;

  editForm: FormBASTPBPFormGroup = this.formBASTPBPFormService.createFormBASTPBPFormGroup();

  constructor(
    protected formBASTPBPService: FormBASTPBPService,
    protected formBASTPBPFormService: FormBASTPBPFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ formBASTPBP }) => {
      this.formBASTPBP = formBASTPBP;
      if (formBASTPBP) {
        this.updateForm(formBASTPBP);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const formBASTPBP = this.formBASTPBPFormService.getFormBASTPBP(this.editForm);
    if (formBASTPBP.id !== null) {
      this.subscribeToSaveResponse(this.formBASTPBPService.update(formBASTPBP));
    } else {
      this.subscribeToSaveResponse(this.formBASTPBPService.create(formBASTPBP));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFormBASTPBP>>): void {
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

  protected updateForm(formBASTPBP: IFormBASTPBP): void {
    this.formBASTPBP = formBASTPBP;
    this.formBASTPBPFormService.resetForm(this.editForm, formBASTPBP);
  }
}
