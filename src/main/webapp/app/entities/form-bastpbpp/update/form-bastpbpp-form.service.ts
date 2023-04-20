import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IFormBASTPBPP, NewFormBASTPBPP } from '../form-bastpbpp.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IFormBASTPBPP for edit and NewFormBASTPBPPFormGroupInput for create.
 */
type FormBASTPBPPFormGroupInput = IFormBASTPBPP | PartialWithRequiredKeyOf<NewFormBASTPBPP>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IFormBASTPBPP | NewFormBASTPBPP> = Omit<T, 'createdDate' | 'lastModifiedDate'> & {
  createdDate?: string | null;
  lastModifiedDate?: string | null;
};

type FormBASTPBPPFormRawValue = FormValueOf<IFormBASTPBPP>;

type NewFormBASTPBPPFormRawValue = FormValueOf<NewFormBASTPBPP>;

type FormBASTPBPPFormDefaults = Pick<NewFormBASTPBPP, 'id' | 'active' | 'createdDate' | 'lastModifiedDate'>;

type FormBASTPBPPFormGroupContent = {
  id: FormControl<FormBASTPBPPFormRawValue['id'] | NewFormBASTPBPP['id']>;
  status: FormControl<FormBASTPBPPFormRawValue['status']>;
  active: FormControl<FormBASTPBPPFormRawValue['active']>;
  remarks: FormControl<FormBASTPBPPFormRawValue['remarks']>;
  createdDate: FormControl<FormBASTPBPPFormRawValue['createdDate']>;
  createdBy: FormControl<FormBASTPBPPFormRawValue['createdBy']>;
  lastModifiedDate: FormControl<FormBASTPBPPFormRawValue['lastModifiedDate']>;
  lastModifiedBy: FormControl<FormBASTPBPPFormRawValue['lastModifiedBy']>;
};

export type FormBASTPBPPFormGroup = FormGroup<FormBASTPBPPFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class FormBASTPBPPFormService {
  createFormBASTPBPPFormGroup(formBASTPBPP: FormBASTPBPPFormGroupInput = { id: null }): FormBASTPBPPFormGroup {
    const formBASTPBPPRawValue = this.convertFormBASTPBPPToFormBASTPBPPRawValue({
      ...this.getFormDefaults(),
      ...formBASTPBPP,
    });
    return new FormGroup<FormBASTPBPPFormGroupContent>({
      id: new FormControl(
        { value: formBASTPBPPRawValue.id, disabled: formBASTPBPPRawValue.id !== null },
        {
          nonNullable: true,
          validators: [Validators.required, Validators.maxLength(50)],
        }
      ),
      status: new FormControl(formBASTPBPPRawValue.status, {
        validators: [Validators.maxLength(2)],
      }),
      active: new FormControl(formBASTPBPPRawValue.active, {
        validators: [Validators.required],
      }),
      remarks: new FormControl(formBASTPBPPRawValue.remarks, {
        validators: [Validators.maxLength(500)],
      }),
      createdDate: new FormControl(formBASTPBPPRawValue.createdDate, {
        validators: [Validators.required],
      }),
      createdBy: new FormControl(formBASTPBPPRawValue.createdBy, {
        validators: [Validators.maxLength(50)],
      }),
      lastModifiedDate: new FormControl(formBASTPBPPRawValue.lastModifiedDate),
      lastModifiedBy: new FormControl(formBASTPBPPRawValue.lastModifiedBy, {
        validators: [Validators.maxLength(50)],
      }),
    });
  }

  getFormBASTPBPP(form: FormBASTPBPPFormGroup): IFormBASTPBPP | NewFormBASTPBPP {
    return this.convertFormBASTPBPPRawValueToFormBASTPBPP(form.getRawValue() as FormBASTPBPPFormRawValue | NewFormBASTPBPPFormRawValue);
  }

  resetForm(form: FormBASTPBPPFormGroup, formBASTPBPP: FormBASTPBPPFormGroupInput): void {
    const formBASTPBPPRawValue = this.convertFormBASTPBPPToFormBASTPBPPRawValue({ ...this.getFormDefaults(), ...formBASTPBPP });
    form.reset(
      {
        ...formBASTPBPPRawValue,
        id: { value: formBASTPBPPRawValue.id, disabled: formBASTPBPPRawValue.id !== null },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): FormBASTPBPPFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      active: false,
      createdDate: currentTime,
      lastModifiedDate: currentTime,
    };
  }

  private convertFormBASTPBPPRawValueToFormBASTPBPP(
    rawFormBASTPBPP: FormBASTPBPPFormRawValue | NewFormBASTPBPPFormRawValue
  ): IFormBASTPBPP | NewFormBASTPBPP {
    return {
      ...rawFormBASTPBPP,
      createdDate: dayjs(rawFormBASTPBPP.createdDate, DATE_TIME_FORMAT),
      lastModifiedDate: dayjs(rawFormBASTPBPP.lastModifiedDate, DATE_TIME_FORMAT),
    };
  }

  private convertFormBASTPBPPToFormBASTPBPPRawValue(
    formBASTPBPP: IFormBASTPBPP | (Partial<NewFormBASTPBPP> & FormBASTPBPPFormDefaults)
  ): FormBASTPBPPFormRawValue | PartialWithRequiredKeyOf<NewFormBASTPBPPFormRawValue> {
    return {
      ...formBASTPBPP,
      createdDate: formBASTPBPP.createdDate ? formBASTPBPP.createdDate.format(DATE_TIME_FORMAT) : undefined,
      lastModifiedDate: formBASTPBPP.lastModifiedDate ? formBASTPBPP.lastModifiedDate.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
