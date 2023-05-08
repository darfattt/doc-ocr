import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IFormSuratJalan, NewFormSuratJalan } from '../form-surat-jalan.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IFormSuratJalan for edit and NewFormSuratJalanFormGroupInput for create.
 */
type FormSuratJalanFormGroupInput = IFormSuratJalan | PartialWithRequiredKeyOf<NewFormSuratJalan>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IFormSuratJalan | NewFormSuratJalan> = Omit<T, 'createdDate' | 'lastModifiedDate'> & {
  createdDate?: string | null;
  lastModifiedDate?: string | null;
};

type FormSuratJalanFormRawValue = FormValueOf<IFormSuratJalan>;

type NewFormSuratJalanFormRawValue = FormValueOf<NewFormSuratJalan>;

type FormSuratJalanFormDefaults = Pick<NewFormSuratJalan, 'id' | 'active' | 'createdDate' | 'lastModifiedDate'>;

type FormSuratJalanFormGroupContent = {
  id: FormControl<FormSuratJalanFormRawValue['id'] | NewFormSuratJalan['id']>;
  status: FormControl<FormSuratJalanFormRawValue['status']>;
  active: FormControl<FormSuratJalanFormRawValue['active']>;
  remarks: FormControl<FormSuratJalanFormRawValue['remarks']>;
  contents: FormControl<FormSuratJalanFormRawValue['contents']>;
  branch: FormControl<FormSuratJalanFormRawValue['branch']>;
  documentTitle: FormControl<FormSuratJalanFormRawValue['documentTitle']>;
  documentNumber: FormControl<FormSuratJalanFormRawValue['documentNumber']>;
  recipientAddress: FormControl<FormSuratJalanFormRawValue['recipientAddress']>;
  npwp: FormControl<FormSuratJalanFormRawValue['npwp']>;
  warehouseSource: FormControl<FormSuratJalanFormRawValue['warehouseSource']>;
  documentSource: FormControl<FormSuratJalanFormRawValue['documentSource']>;
  reference: FormControl<FormSuratJalanFormRawValue['reference']>;
  date: FormControl<FormSuratJalanFormRawValue['date']>;
  productDescription: FormControl<FormSuratJalanFormRawValue['productDescription']>;
  quantity: FormControl<FormSuratJalanFormRawValue['quantity']>;
  amount: FormControl<FormSuratJalanFormRawValue['amount']>;
  armadaNumber: FormControl<FormSuratJalanFormRawValue['armadaNumber']>;
  containerNumber: FormControl<FormSuratJalanFormRawValue['containerNumber']>;
  createdDate: FormControl<FormSuratJalanFormRawValue['createdDate']>;
  createdBy: FormControl<FormSuratJalanFormRawValue['createdBy']>;
  lastModifiedDate: FormControl<FormSuratJalanFormRawValue['lastModifiedDate']>;
  lastModifiedBy: FormControl<FormSuratJalanFormRawValue['lastModifiedBy']>;
};

export type FormSuratJalanFormGroup = FormGroup<FormSuratJalanFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class FormSuratJalanFormService {
  createFormSuratJalanFormGroup(formSuratJalan: FormSuratJalanFormGroupInput = { id: null }): FormSuratJalanFormGroup {
    const formSuratJalanRawValue = this.convertFormSuratJalanToFormSuratJalanRawValue({
      ...this.getFormDefaults(),
      ...formSuratJalan,
    });
    return new FormGroup<FormSuratJalanFormGroupContent>({
      id: new FormControl(
        { value: formSuratJalanRawValue.id, disabled: formSuratJalanRawValue.id !== null },
        {
          nonNullable: true,
          validators: [Validators.required, Validators.maxLength(50)],
        }
      ),
      status: new FormControl(formSuratJalanRawValue.status, {
        validators: [Validators.maxLength(2)],
      }),
      active: new FormControl(formSuratJalanRawValue.active, {
        validators: [Validators.required],
      }),
      remarks: new FormControl(formSuratJalanRawValue.remarks, {
        validators: [Validators.maxLength(500)],
      }),
      contents: new FormControl(formSuratJalanRawValue.contents, {
        validators: [Validators.maxLength(65353)],
      }),
      branch: new FormControl(formSuratJalanRawValue.branch, {
        validators: [Validators.maxLength(100)],
      }),
      documentTitle: new FormControl(formSuratJalanRawValue.documentTitle, {
        validators: [Validators.maxLength(500)],
      }),
      documentNumber: new FormControl(formSuratJalanRawValue.documentNumber, {
        validators: [Validators.maxLength(100)],
      }),
      recipientAddress: new FormControl(formSuratJalanRawValue.recipientAddress, {
        validators: [Validators.maxLength(500)],
      }),
      npwp: new FormControl(formSuratJalanRawValue.npwp, {
        validators: [Validators.maxLength(100)],
      }),
      warehouseSource: new FormControl(formSuratJalanRawValue.warehouseSource, {
        validators: [Validators.maxLength(100)],
      }),
      documentSource: new FormControl(formSuratJalanRawValue.documentSource, {
        validators: [Validators.maxLength(100)],
      }),
      reference: new FormControl(formSuratJalanRawValue.reference, {
        validators: [Validators.maxLength(100)],
      }),
      date: new FormControl(formSuratJalanRawValue.date, {
        validators: [Validators.maxLength(100)],
      }),
      productDescription: new FormControl(formSuratJalanRawValue.productDescription, {
        validators: [Validators.maxLength(100)],
      }),
      quantity: new FormControl(formSuratJalanRawValue.quantity),
      amount: new FormControl(formSuratJalanRawValue.amount),
      armadaNumber: new FormControl(formSuratJalanRawValue.armadaNumber, {
        validators: [Validators.maxLength(100)],
      }),
      containerNumber: new FormControl(formSuratJalanRawValue.containerNumber, {
        validators: [Validators.maxLength(100)],
      }),
      createdDate: new FormControl(formSuratJalanRawValue.createdDate, {
        validators: [Validators.required],
      }),
      createdBy: new FormControl(formSuratJalanRawValue.createdBy, {
        validators: [Validators.maxLength(50)],
      }),
      lastModifiedDate: new FormControl(formSuratJalanRawValue.lastModifiedDate),
      lastModifiedBy: new FormControl(formSuratJalanRawValue.lastModifiedBy, {
        validators: [Validators.maxLength(50)],
      }),
    });
  }

  getFormSuratJalan(form: FormSuratJalanFormGroup): IFormSuratJalan | NewFormSuratJalan {
    return this.convertFormSuratJalanRawValueToFormSuratJalan(
      form.getRawValue() as FormSuratJalanFormRawValue | NewFormSuratJalanFormRawValue
    );
  }

  resetForm(form: FormSuratJalanFormGroup, formSuratJalan: FormSuratJalanFormGroupInput): void {
    const formSuratJalanRawValue = this.convertFormSuratJalanToFormSuratJalanRawValue({ ...this.getFormDefaults(), ...formSuratJalan });
    form.reset(
      {
        ...formSuratJalanRawValue,
        id: { value: formSuratJalanRawValue.id, disabled: formSuratJalanRawValue.id !== null },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): FormSuratJalanFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      active: false,
      createdDate: currentTime,
      lastModifiedDate: currentTime,
    };
  }

  private convertFormSuratJalanRawValueToFormSuratJalan(
    rawFormSuratJalan: FormSuratJalanFormRawValue | NewFormSuratJalanFormRawValue
  ): IFormSuratJalan | NewFormSuratJalan {
    return {
      ...rawFormSuratJalan,
      createdDate: dayjs(rawFormSuratJalan.createdDate, DATE_TIME_FORMAT),
      lastModifiedDate: dayjs(rawFormSuratJalan.lastModifiedDate, DATE_TIME_FORMAT),
    };
  }

  private convertFormSuratJalanToFormSuratJalanRawValue(
    formSuratJalan: IFormSuratJalan | (Partial<NewFormSuratJalan> & FormSuratJalanFormDefaults)
  ): FormSuratJalanFormRawValue | PartialWithRequiredKeyOf<NewFormSuratJalanFormRawValue> {
    return {
      ...formSuratJalan,
      createdDate: formSuratJalan.createdDate ? formSuratJalan.createdDate.format(DATE_TIME_FORMAT) : undefined,
      lastModifiedDate: formSuratJalan.lastModifiedDate ? formSuratJalan.lastModifiedDate.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
