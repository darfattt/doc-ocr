import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IFormBASTPBP, NewFormBASTPBP } from '../form-bastpbp.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IFormBASTPBP for edit and NewFormBASTPBPFormGroupInput for create.
 */
type FormBASTPBPFormGroupInput = IFormBASTPBP | PartialWithRequiredKeyOf<NewFormBASTPBP>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IFormBASTPBP | NewFormBASTPBP> = Omit<T, 'createdDate' | 'lastModifiedDate'> & {
  createdDate?: string | null;
  lastModifiedDate?: string | null;
};

type FormBASTPBPFormRawValue = FormValueOf<IFormBASTPBP>;

type NewFormBASTPBPFormRawValue = FormValueOf<NewFormBASTPBP>;

type FormBASTPBPFormDefaults = Pick<NewFormBASTPBP, 'id' | 'active' | 'createdDate' | 'lastModifiedDate'>;

type FormBASTPBPFormGroupContent = {
  id: FormControl<FormBASTPBPFormRawValue['id'] | NewFormBASTPBP['id']>;
  status: FormControl<FormBASTPBPFormRawValue['status']>;
  active: FormControl<FormBASTPBPFormRawValue['active']>;
  remarks: FormControl<FormBASTPBPFormRawValue['remarks']>;
  createdDate: FormControl<FormBASTPBPFormRawValue['createdDate']>;
  createdBy: FormControl<FormBASTPBPFormRawValue['createdBy']>;
  lastModifiedDate: FormControl<FormBASTPBPFormRawValue['lastModifiedDate']>;
  lastModifiedBy: FormControl<FormBASTPBPFormRawValue['lastModifiedBy']>;
};

export type FormBASTPBPFormGroup = FormGroup<FormBASTPBPFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class FormBASTPBPFormService {
  createFormBASTPBPFormGroup(formBASTPBP: FormBASTPBPFormGroupInput = { id: null }): FormBASTPBPFormGroup {
    const formBASTPBPRawValue = this.convertFormBASTPBPToFormBASTPBPRawValue({
      ...this.getFormDefaults(),
      ...formBASTPBP,
    });
    return new FormGroup<FormBASTPBPFormGroupContent>({
      id: new FormControl(
        { value: formBASTPBPRawValue.id, disabled: formBASTPBPRawValue.id !== null },
        {
          nonNullable: true,
          validators: [Validators.required, Validators.maxLength(50)],
        }
      ),
      status: new FormControl(formBASTPBPRawValue.status, {
        validators: [Validators.maxLength(2)],
      }),
      active: new FormControl(formBASTPBPRawValue.active, {
        validators: [Validators.required],
      }),
      remarks: new FormControl(formBASTPBPRawValue.remarks, {
        validators: [Validators.maxLength(500)],
      }),
      createdDate: new FormControl(formBASTPBPRawValue.createdDate, {
        validators: [Validators.required],
      }),
      createdBy: new FormControl(formBASTPBPRawValue.createdBy, {
        validators: [Validators.maxLength(50)],
      }),
      lastModifiedDate: new FormControl(formBASTPBPRawValue.lastModifiedDate),
      lastModifiedBy: new FormControl(formBASTPBPRawValue.lastModifiedBy, {
        validators: [Validators.maxLength(50)],
      }),
    });
  }

  getFormBASTPBP(form: FormBASTPBPFormGroup): IFormBASTPBP | NewFormBASTPBP {
    return this.convertFormBASTPBPRawValueToFormBASTPBP(form.getRawValue() as FormBASTPBPFormRawValue | NewFormBASTPBPFormRawValue);
  }

  resetForm(form: FormBASTPBPFormGroup, formBASTPBP: FormBASTPBPFormGroupInput): void {
    const formBASTPBPRawValue = this.convertFormBASTPBPToFormBASTPBPRawValue({ ...this.getFormDefaults(), ...formBASTPBP });
    form.reset(
      {
        ...formBASTPBPRawValue,
        id: { value: formBASTPBPRawValue.id, disabled: formBASTPBPRawValue.id !== null },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): FormBASTPBPFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      active: false,
      createdDate: currentTime,
      lastModifiedDate: currentTime,
    };
  }

  private convertFormBASTPBPRawValueToFormBASTPBP(
    rawFormBASTPBP: FormBASTPBPFormRawValue | NewFormBASTPBPFormRawValue
  ): IFormBASTPBP | NewFormBASTPBP {
    return {
      ...rawFormBASTPBP,
      createdDate: dayjs(rawFormBASTPBP.createdDate, DATE_TIME_FORMAT),
      lastModifiedDate: dayjs(rawFormBASTPBP.lastModifiedDate, DATE_TIME_FORMAT),
    };
  }

  private convertFormBASTPBPToFormBASTPBPRawValue(
    formBASTPBP: IFormBASTPBP | (Partial<NewFormBASTPBP> & FormBASTPBPFormDefaults)
  ): FormBASTPBPFormRawValue | PartialWithRequiredKeyOf<NewFormBASTPBPFormRawValue> {
    return {
      ...formBASTPBP,
      createdDate: formBASTPBP.createdDate ? formBASTPBP.createdDate.format(DATE_TIME_FORMAT) : undefined,
      lastModifiedDate: formBASTPBP.lastModifiedDate ? formBASTPBP.lastModifiedDate.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
