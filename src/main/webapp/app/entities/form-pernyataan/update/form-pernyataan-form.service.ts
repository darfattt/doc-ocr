import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IFormPernyataan, NewFormPernyataan } from '../form-pernyataan.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IFormPernyataan for edit and NewFormPernyataanFormGroupInput for create.
 */
type FormPernyataanFormGroupInput = IFormPernyataan | PartialWithRequiredKeyOf<NewFormPernyataan>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IFormPernyataan | NewFormPernyataan> = Omit<T, 'createdDate' | 'lastModifiedDate'> & {
  createdDate?: string | null;
  lastModifiedDate?: string | null;
};

type FormPernyataanFormRawValue = FormValueOf<IFormPernyataan>;

type NewFormPernyataanFormRawValue = FormValueOf<NewFormPernyataan>;

type FormPernyataanFormDefaults = Pick<NewFormPernyataan, 'id' | 'active' | 'createdDate' | 'lastModifiedDate'>;

type FormPernyataanFormGroupContent = {
  id: FormControl<FormPernyataanFormRawValue['id'] | NewFormPernyataan['id']>;
  status: FormControl<FormPernyataanFormRawValue['status']>;
  active: FormControl<FormPernyataanFormRawValue['active']>;
  remarks: FormControl<FormPernyataanFormRawValue['remarks']>;
  createdDate: FormControl<FormPernyataanFormRawValue['createdDate']>;
  createdBy: FormControl<FormPernyataanFormRawValue['createdBy']>;
  lastModifiedDate: FormControl<FormPernyataanFormRawValue['lastModifiedDate']>;
  lastModifiedBy: FormControl<FormPernyataanFormRawValue['lastModifiedBy']>;
};

export type FormPernyataanFormGroup = FormGroup<FormPernyataanFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class FormPernyataanFormService {
  createFormPernyataanFormGroup(formPernyataan: FormPernyataanFormGroupInput = { id: null }): FormPernyataanFormGroup {
    const formPernyataanRawValue = this.convertFormPernyataanToFormPernyataanRawValue({
      ...this.getFormDefaults(),
      ...formPernyataan,
    });
    return new FormGroup<FormPernyataanFormGroupContent>({
      id: new FormControl(
        { value: formPernyataanRawValue.id, disabled: formPernyataanRawValue.id !== null },
        {
          nonNullable: true,
          validators: [Validators.required, Validators.maxLength(50)],
        }
      ),
      status: new FormControl(formPernyataanRawValue.status, {
        validators: [Validators.maxLength(2)],
      }),
      active: new FormControl(formPernyataanRawValue.active, {
        validators: [Validators.required],
      }),
      remarks: new FormControl(formPernyataanRawValue.remarks, {
        validators: [Validators.maxLength(500)],
      }),
      createdDate: new FormControl(formPernyataanRawValue.createdDate, {
        validators: [Validators.required],
      }),
      createdBy: new FormControl(formPernyataanRawValue.createdBy, {
        validators: [Validators.maxLength(50)],
      }),
      lastModifiedDate: new FormControl(formPernyataanRawValue.lastModifiedDate),
      lastModifiedBy: new FormControl(formPernyataanRawValue.lastModifiedBy, {
        validators: [Validators.maxLength(50)],
      }),
    });
  }

  getFormPernyataan(form: FormPernyataanFormGroup): IFormPernyataan | NewFormPernyataan {
    return this.convertFormPernyataanRawValueToFormPernyataan(
      form.getRawValue() as FormPernyataanFormRawValue | NewFormPernyataanFormRawValue
    );
  }

  resetForm(form: FormPernyataanFormGroup, formPernyataan: FormPernyataanFormGroupInput): void {
    const formPernyataanRawValue = this.convertFormPernyataanToFormPernyataanRawValue({ ...this.getFormDefaults(), ...formPernyataan });
    form.reset(
      {
        ...formPernyataanRawValue,
        id: { value: formPernyataanRawValue.id, disabled: formPernyataanRawValue.id !== null },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): FormPernyataanFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      active: false,
      createdDate: currentTime,
      lastModifiedDate: currentTime,
    };
  }

  private convertFormPernyataanRawValueToFormPernyataan(
    rawFormPernyataan: FormPernyataanFormRawValue | NewFormPernyataanFormRawValue
  ): IFormPernyataan | NewFormPernyataan {
    return {
      ...rawFormPernyataan,
      createdDate: dayjs(rawFormPernyataan.createdDate, DATE_TIME_FORMAT),
      lastModifiedDate: dayjs(rawFormPernyataan.lastModifiedDate, DATE_TIME_FORMAT),
    };
  }

  private convertFormPernyataanToFormPernyataanRawValue(
    formPernyataan: IFormPernyataan | (Partial<NewFormPernyataan> & FormPernyataanFormDefaults)
  ): FormPernyataanFormRawValue | PartialWithRequiredKeyOf<NewFormPernyataanFormRawValue> {
    return {
      ...formPernyataan,
      createdDate: formPernyataan.createdDate ? formPernyataan.createdDate.format(DATE_TIME_FORMAT) : undefined,
      lastModifiedDate: formPernyataan.lastModifiedDate ? formPernyataan.lastModifiedDate.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
