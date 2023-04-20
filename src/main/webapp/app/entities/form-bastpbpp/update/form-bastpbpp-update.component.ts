import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { FormBASTPBPPFormService, FormBASTPBPPFormGroup } from './form-bastpbpp-form.service';
import { IFormBASTPBPP } from '../form-bastpbpp.model';
import { FormBASTPBPPService } from '../service/form-bastpbpp.service';

@Component({
  selector: 'jhi-form-bastpbpp-update',
  templateUrl: './form-bastpbpp-update.component.html',
})
export class FormBASTPBPPUpdateComponent implements OnInit {
  isSaving = false;
  formBASTPBPP: IFormBASTPBPP | null = null;

  editForm: FormBASTPBPPFormGroup = this.formBASTPBPPFormService.createFormBASTPBPPFormGroup();

  constructor(
    protected formBASTPBPPService: FormBASTPBPPService,
    protected formBASTPBPPFormService: FormBASTPBPPFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ formBASTPBPP }) => {
      this.formBASTPBPP = formBASTPBPP;
      if (formBASTPBPP) {
        this.updateForm(formBASTPBPP);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const formBASTPBPP = this.formBASTPBPPFormService.getFormBASTPBPP(this.editForm);
    if (formBASTPBPP.id !== null) {
      this.subscribeToSaveResponse(this.formBASTPBPPService.update(formBASTPBPP));
    } else {
      this.subscribeToSaveResponse(this.formBASTPBPPService.create(formBASTPBPP));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFormBASTPBPP>>): void {
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

  protected updateForm(formBASTPBPP: IFormBASTPBPP): void {
    this.formBASTPBPP = formBASTPBPP;
    this.formBASTPBPPFormService.resetForm(this.editForm, formBASTPBPP);
  }
}
