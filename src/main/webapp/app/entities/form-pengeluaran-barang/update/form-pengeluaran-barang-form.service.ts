import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IFormPengeluaranBarang, NewFormPengeluaranBarang } from '../form-pengeluaran-barang.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IFormPengeluaranBarang for edit and NewFormPengeluaranBarangFormGroupInput for create.
 */
type FormPengeluaranBarangFormGroupInput = IFormPengeluaranBarang | PartialWithRequiredKeyOf<NewFormPengeluaranBarang>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IFormPengeluaranBarang | NewFormPengeluaranBarang> = Omit<T, 'createdDate' | 'lastModifiedDate'> & {
  createdDate?: string | null;
  lastModifiedDate?: string | null;
};

type FormPengeluaranBarangFormRawValue = FormValueOf<IFormPengeluaranBarang>;

type NewFormPengeluaranBarangFormRawValue = FormValueOf<NewFormPengeluaranBarang>;

type FormPengeluaranBarangFormDefaults = Pick<NewFormPengeluaranBarang, 'id' | 'active' | 'createdDate' | 'lastModifiedDate'>;

type FormPengeluaranBarangFormGroupContent = {
  id: FormControl<FormPengeluaranBarangFormRawValue['id'] | NewFormPengeluaranBarang['id']>;
  status: FormControl<FormPengeluaranBarangFormRawValue['status']>;
  active: FormControl<FormPengeluaranBarangFormRawValue['active']>;
  remarks: FormControl<FormPengeluaranBarangFormRawValue['remarks']>;
  createdDate: FormControl<FormPengeluaranBarangFormRawValue['createdDate']>;
  createdBy: FormControl<FormPengeluaranBarangFormRawValue['createdBy']>;
  lastModifiedDate: FormControl<FormPengeluaranBarangFormRawValue['lastModifiedDate']>;
  lastModifiedBy: FormControl<FormPengeluaranBarangFormRawValue['lastModifiedBy']>;
};

export type FormPengeluaranBarangFormGroup = FormGroup<FormPengeluaranBarangFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class FormPengeluaranBarangFormService {
  createFormPengeluaranBarangFormGroup(
    formPengeluaranBarang: FormPengeluaranBarangFormGroupInput = { id: null }
  ): FormPengeluaranBarangFormGroup {
    const formPengeluaranBarangRawValue = this.convertFormPengeluaranBarangToFormPengeluaranBarangRawValue({
      ...this.getFormDefaults(),
      ...formPengeluaranBarang,
    });
    return new FormGroup<FormPengeluaranBarangFormGroupContent>({
      id: new FormControl(
        { value: formPengeluaranBarangRawValue.id, disabled: formPengeluaranBarangRawValue.id !== null },
        {
          nonNullable: true,
          validators: [Validators.required, Validators.maxLength(50)],
        }
      ),
      status: new FormControl(formPengeluaranBarangRawValue.status, {
        validators: [Validators.maxLength(2)],
      }),
      active: new FormControl(formPengeluaranBarangRawValue.active, {
        validators: [Validators.required],
      }),
      remarks: new FormControl(formPengeluaranBarangRawValue.remarks, {
        validators: [Validators.maxLength(500)],
      }),
      createdDate: new FormControl(formPengeluaranBarangRawValue.createdDate, {
        validators: [Validators.required],
      }),
      createdBy: new FormControl(formPengeluaranBarangRawValue.createdBy, {
        validators: [Validators.maxLength(50)],
      }),
      lastModifiedDate: new FormControl(formPengeluaranBarangRawValue.lastModifiedDate),
      lastModifiedBy: new FormControl(formPengeluaranBarangRawValue.lastModifiedBy, {
        validators: [Validators.maxLength(50)],
      }),
    });
  }

  getFormPengeluaranBarang(form: FormPengeluaranBarangFormGroup): IFormPengeluaranBarang | NewFormPengeluaranBarang {
    return this.convertFormPengeluaranBarangRawValueToFormPengeluaranBarang(
      form.getRawValue() as FormPengeluaranBarangFormRawValue | NewFormPengeluaranBarangFormRawValue
    );
  }

  resetForm(form: FormPengeluaranBarangFormGroup, formPengeluaranBarang: FormPengeluaranBarangFormGroupInput): void {
    const formPengeluaranBarangRawValue = this.convertFormPengeluaranBarangToFormPengeluaranBarangRawValue({
      ...this.getFormDefaults(),
      ...formPengeluaranBarang,
    });
    form.reset(
      {
        ...formPengeluaranBarangRawValue,
        id: { value: formPengeluaranBarangRawValue.id, disabled: formPengeluaranBarangRawValue.id !== null },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): FormPengeluaranBarangFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      active: false,
      createdDate: currentTime,
      lastModifiedDate: currentTime,
    };
  }

  private convertFormPengeluaranBarangRawValueToFormPengeluaranBarang(
    rawFormPengeluaranBarang: FormPengeluaranBarangFormRawValue | NewFormPengeluaranBarangFormRawValue
  ): IFormPengeluaranBarang | NewFormPengeluaranBarang {
    return {
      ...rawFormPengeluaranBarang,
      createdDate: dayjs(rawFormPengeluaranBarang.createdDate, DATE_TIME_FORMAT),
      lastModifiedDate: dayjs(rawFormPengeluaranBarang.lastModifiedDate, DATE_TIME_FORMAT),
    };
  }

  private convertFormPengeluaranBarangToFormPengeluaranBarangRawValue(
    formPengeluaranBarang: IFormPengeluaranBarang | (Partial<NewFormPengeluaranBarang> & FormPengeluaranBarangFormDefaults)
  ): FormPengeluaranBarangFormRawValue | PartialWithRequiredKeyOf<NewFormPengeluaranBarangFormRawValue> {
    return {
      ...formPengeluaranBarang,
      createdDate: formPengeluaranBarang.createdDate ? formPengeluaranBarang.createdDate.format(DATE_TIME_FORMAT) : undefined,
      lastModifiedDate: formPengeluaranBarang.lastModifiedDate
        ? formPengeluaranBarang.lastModifiedDate.format(DATE_TIME_FORMAT)
        : undefined,
    };
  }
}
