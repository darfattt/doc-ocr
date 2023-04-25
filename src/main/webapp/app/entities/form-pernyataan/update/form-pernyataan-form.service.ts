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
  contents: FormControl<FormPernyataanFormRawValue['contents']>;
  documentTitle: FormControl<FormPernyataanFormRawValue['documentTitle']>;
  officerName: FormControl<FormPernyataanFormRawValue['officerName']>;
  officerPhoneNumber: FormControl<FormPernyataanFormRawValue['officerPhoneNumber']>;
  officerPosition: FormControl<FormPernyataanFormRawValue['officerPosition']>;
  officerDepartment: FormControl<FormPernyataanFormRawValue['officerDepartment']>;
  kelurahanDesa: FormControl<FormPernyataanFormRawValue['kelurahanDesa']>;
  kecamatan: FormControl<FormPernyataanFormRawValue['kecamatan']>;
  kabupatenKota: FormControl<FormPernyataanFormRawValue['kabupatenKota']>;
  provinsi: FormControl<FormPernyataanFormRawValue['provinsi']>;
  pbpTidakDitemukan1: FormControl<FormPernyataanFormRawValue['pbpTidakDitemukan1']>;
  alamatPbpTidakDitemukan1: FormControl<FormPernyataanFormRawValue['alamatPbpTidakDitemukan1']>;
  pbpPengganti1: FormControl<FormPernyataanFormRawValue['pbpPengganti1']>;
  alamatPbpPengganti1: FormControl<FormPernyataanFormRawValue['alamatPbpPengganti1']>;
  pbpTidakDitemukan2: FormControl<FormPernyataanFormRawValue['pbpTidakDitemukan2']>;
  alamatPbpTidakDitemukan2: FormControl<FormPernyataanFormRawValue['alamatPbpTidakDitemukan2']>;
  pbpPengganti2: FormControl<FormPernyataanFormRawValue['pbpPengganti2']>;
  alamatPbpPengganti2: FormControl<FormPernyataanFormRawValue['alamatPbpPengganti2']>;
  pbpTidakDitemukan3: FormControl<FormPernyataanFormRawValue['pbpTidakDitemukan3']>;
  alamatPbpTidakDitemukan3: FormControl<FormPernyataanFormRawValue['alamatPbpTidakDitemukan3']>;
  pbpPengganti3: FormControl<FormPernyataanFormRawValue['pbpPengganti3']>;
  alamatPbpPengganti3: FormControl<FormPernyataanFormRawValue['alamatPbpPengganti3']>;
  pbpTidakDitemukan4: FormControl<FormPernyataanFormRawValue['pbpTidakDitemukan4']>;
  alamatPbpTidakDitemukan4: FormControl<FormPernyataanFormRawValue['alamatPbpTidakDitemukan4']>;
  pbpPengganti4: FormControl<FormPernyataanFormRawValue['pbpPengganti4']>;
  alamatPbpPengganti4: FormControl<FormPernyataanFormRawValue['alamatPbpPengganti4']>;
  pbpTidakDitemukan5: FormControl<FormPernyataanFormRawValue['pbpTidakDitemukan5']>;
  alamatPbpTidakDitemukan5: FormControl<FormPernyataanFormRawValue['alamatPbpTidakDitemukan5']>;
  pbpPengganti5: FormControl<FormPernyataanFormRawValue['pbpPengganti5']>;
  alamatPbpPengganti5: FormControl<FormPernyataanFormRawValue['alamatPbpPengganti5']>;
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
      contents: new FormControl(formPernyataanRawValue.contents, {
        validators: [Validators.maxLength(65353)],
      }),
      documentTitle: new FormControl(formPernyataanRawValue.documentTitle, {
        validators: [Validators.maxLength(500)],
      }),
      officerName: new FormControl(formPernyataanRawValue.officerName, {
        validators: [Validators.maxLength(100)],
      }),
      officerPhoneNumber: new FormControl(formPernyataanRawValue.officerPhoneNumber, {
        validators: [Validators.maxLength(50)],
      }),
      officerPosition: new FormControl(formPernyataanRawValue.officerPosition, {
        validators: [Validators.maxLength(100)],
      }),
      officerDepartment: new FormControl(formPernyataanRawValue.officerDepartment, {
        validators: [Validators.maxLength(100)],
      }),
      kelurahanDesa: new FormControl(formPernyataanRawValue.kelurahanDesa, {
        validators: [Validators.maxLength(100)],
      }),
      kecamatan: new FormControl(formPernyataanRawValue.kecamatan, {
        validators: [Validators.maxLength(100)],
      }),
      kabupatenKota: new FormControl(formPernyataanRawValue.kabupatenKota, {
        validators: [Validators.maxLength(100)],
      }),
      provinsi: new FormControl(formPernyataanRawValue.provinsi, {
        validators: [Validators.maxLength(100)],
      }),
      pbpTidakDitemukan1: new FormControl(formPernyataanRawValue.pbpTidakDitemukan1, {
        validators: [Validators.maxLength(100)],
      }),
      alamatPbpTidakDitemukan1: new FormControl(formPernyataanRawValue.alamatPbpTidakDitemukan1, {
        validators: [Validators.maxLength(100)],
      }),
      pbpPengganti1: new FormControl(formPernyataanRawValue.pbpPengganti1, {
        validators: [Validators.maxLength(100)],
      }),
      alamatPbpPengganti1: new FormControl(formPernyataanRawValue.alamatPbpPengganti1, {
        validators: [Validators.maxLength(100)],
      }),
      pbpTidakDitemukan2: new FormControl(formPernyataanRawValue.pbpTidakDitemukan2, {
        validators: [Validators.maxLength(100)],
      }),
      alamatPbpTidakDitemukan2: new FormControl(formPernyataanRawValue.alamatPbpTidakDitemukan2, {
        validators: [Validators.maxLength(100)],
      }),
      pbpPengganti2: new FormControl(formPernyataanRawValue.pbpPengganti2, {
        validators: [Validators.maxLength(100)],
      }),
      alamatPbpPengganti2: new FormControl(formPernyataanRawValue.alamatPbpPengganti2, {
        validators: [Validators.maxLength(100)],
      }),
      pbpTidakDitemukan3: new FormControl(formPernyataanRawValue.pbpTidakDitemukan3, {
        validators: [Validators.maxLength(100)],
      }),
      alamatPbpTidakDitemukan3: new FormControl(formPernyataanRawValue.alamatPbpTidakDitemukan3, {
        validators: [Validators.maxLength(100)],
      }),
      pbpPengganti3: new FormControl(formPernyataanRawValue.pbpPengganti3, {
        validators: [Validators.maxLength(100)],
      }),
      alamatPbpPengganti3: new FormControl(formPernyataanRawValue.alamatPbpPengganti3, {
        validators: [Validators.maxLength(100)],
      }),
      pbpTidakDitemukan4: new FormControl(formPernyataanRawValue.pbpTidakDitemukan4, {
        validators: [Validators.maxLength(100)],
      }),
      alamatPbpTidakDitemukan4: new FormControl(formPernyataanRawValue.alamatPbpTidakDitemukan4, {
        validators: [Validators.maxLength(100)],
      }),
      pbpPengganti4: new FormControl(formPernyataanRawValue.pbpPengganti4, {
        validators: [Validators.maxLength(100)],
      }),
      alamatPbpPengganti4: new FormControl(formPernyataanRawValue.alamatPbpPengganti4, {
        validators: [Validators.maxLength(100)],
      }),
      pbpTidakDitemukan5: new FormControl(formPernyataanRawValue.pbpTidakDitemukan5, {
        validators: [Validators.maxLength(100)],
      }),
      alamatPbpTidakDitemukan5: new FormControl(formPernyataanRawValue.alamatPbpTidakDitemukan5, {
        validators: [Validators.maxLength(100)],
      }),
      pbpPengganti5: new FormControl(formPernyataanRawValue.pbpPengganti5, {
        validators: [Validators.maxLength(100)],
      }),
      alamatPbpPengganti5: new FormControl(formPernyataanRawValue.alamatPbpPengganti5, {
        validators: [Validators.maxLength(100)],
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
