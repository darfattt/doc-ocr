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
  contents: FormControl<FormBASTPBPFormRawValue['contents']>;
  documentTitle: FormControl<FormBASTPBPFormRawValue['documentTitle']>;
  documentNumber: FormControl<FormBASTPBPFormRawValue['documentNumber']>;
  kelurahanDesa: FormControl<FormBASTPBPFormRawValue['kelurahanDesa']>;
  kecamatan: FormControl<FormBASTPBPFormRawValue['kecamatan']>;
  kabupatenKota: FormControl<FormBASTPBPFormRawValue['kabupatenKota']>;
  provinsi: FormControl<FormBASTPBPFormRawValue['provinsi']>;
  rtRw: FormControl<FormBASTPBPFormRawValue['rtRw']>;
  kcu: FormControl<FormBASTPBPFormRawValue['kcu']>;
  kantorSerah: FormControl<FormBASTPBPFormRawValue['kantorSerah']>;
  bastNumber: FormControl<FormBASTPBPFormRawValue['bastNumber']>;
  documentDescription: FormControl<FormBASTPBPFormRawValue['documentDescription']>;
  nama1: FormControl<FormBASTPBPFormRawValue['nama1']>;
  alamat1: FormControl<FormBASTPBPFormRawValue['alamat1']>;
  nomor1: FormControl<FormBASTPBPFormRawValue['nomor1']>;
  jumlah1: FormControl<FormBASTPBPFormRawValue['jumlah1']>;
  nama2: FormControl<FormBASTPBPFormRawValue['nama2']>;
  alamat2: FormControl<FormBASTPBPFormRawValue['alamat2']>;
  nomor2: FormControl<FormBASTPBPFormRawValue['nomor2']>;
  jumlah2: FormControl<FormBASTPBPFormRawValue['jumlah2']>;
  nama3: FormControl<FormBASTPBPFormRawValue['nama3']>;
  alamat3: FormControl<FormBASTPBPFormRawValue['alamat3']>;
  nomor3: FormControl<FormBASTPBPFormRawValue['nomor3']>;
  jumlah3: FormControl<FormBASTPBPFormRawValue['jumlah3']>;
  nama4: FormControl<FormBASTPBPFormRawValue['nama4']>;
  alamat4: FormControl<FormBASTPBPFormRawValue['alamat4']>;
  nomor4: FormControl<FormBASTPBPFormRawValue['nomor4']>;
  jumlah4: FormControl<FormBASTPBPFormRawValue['jumlah4']>;
  nama5: FormControl<FormBASTPBPFormRawValue['nama5']>;
  alamat5: FormControl<FormBASTPBPFormRawValue['alamat5']>;
  nomor5: FormControl<FormBASTPBPFormRawValue['nomor5']>;
  jumlah5: FormControl<FormBASTPBPFormRawValue['jumlah5']>;
  nama6: FormControl<FormBASTPBPFormRawValue['nama6']>;
  nama7: FormControl<FormBASTPBPFormRawValue['nama7']>;
  alamat7: FormControl<FormBASTPBPFormRawValue['alamat7']>;
  nomor7: FormControl<FormBASTPBPFormRawValue['nomor7']>;
  jumlah7: FormControl<FormBASTPBPFormRawValue['jumlah7']>;
  nama8: FormControl<FormBASTPBPFormRawValue['nama8']>;
  alamat8: FormControl<FormBASTPBPFormRawValue['alamat8']>;
  nomor8: FormControl<FormBASTPBPFormRawValue['nomor8']>;
  jumlah8: FormControl<FormBASTPBPFormRawValue['jumlah8']>;
  nama9: FormControl<FormBASTPBPFormRawValue['nama9']>;
  alamat9: FormControl<FormBASTPBPFormRawValue['alamat9']>;
  nomor9: FormControl<FormBASTPBPFormRawValue['nomor9']>;
  jumlah9: FormControl<FormBASTPBPFormRawValue['jumlah9']>;
  nama10: FormControl<FormBASTPBPFormRawValue['nama10']>;
  alamat10: FormControl<FormBASTPBPFormRawValue['alamat10']>;
  nomor10: FormControl<FormBASTPBPFormRawValue['nomor10']>;
  jumlah10: FormControl<FormBASTPBPFormRawValue['jumlah10']>;
  alamat6: FormControl<FormBASTPBPFormRawValue['alamat6']>;
  nomor6: FormControl<FormBASTPBPFormRawValue['nomor6']>;
  jumlah6: FormControl<FormBASTPBPFormRawValue['jumlah6']>;
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
      contents: new FormControl(formBASTPBPRawValue.contents, {
        validators: [Validators.maxLength(65353)],
      }),
      documentTitle: new FormControl(formBASTPBPRawValue.documentTitle, {
        validators: [Validators.maxLength(500)],
      }),
      documentNumber: new FormControl(formBASTPBPRawValue.documentNumber, {
        validators: [Validators.maxLength(50)],
      }),
      kelurahanDesa: new FormControl(formBASTPBPRawValue.kelurahanDesa, {
        validators: [Validators.maxLength(100)],
      }),
      kecamatan: new FormControl(formBASTPBPRawValue.kecamatan, {
        validators: [Validators.maxLength(100)],
      }),
      kabupatenKota: new FormControl(formBASTPBPRawValue.kabupatenKota, {
        validators: [Validators.maxLength(100)],
      }),
      provinsi: new FormControl(formBASTPBPRawValue.provinsi, {
        validators: [Validators.maxLength(100)],
      }),
      rtRw: new FormControl(formBASTPBPRawValue.rtRw, {
        validators: [Validators.maxLength(10)],
      }),
      kcu: new FormControl(formBASTPBPRawValue.kcu, {
        validators: [Validators.maxLength(50)],
      }),
      kantorSerah: new FormControl(formBASTPBPRawValue.kantorSerah, {
        validators: [Validators.maxLength(50)],
      }),
      bastNumber: new FormControl(formBASTPBPRawValue.bastNumber, {
        validators: [Validators.maxLength(50)],
      }),
      documentDescription: new FormControl(formBASTPBPRawValue.documentDescription, {
        validators: [Validators.maxLength(500)],
      }),
      nama1: new FormControl(formBASTPBPRawValue.nama1, {
        validators: [Validators.maxLength(100)],
      }),
      alamat1: new FormControl(formBASTPBPRawValue.alamat1, {
        validators: [Validators.maxLength(100)],
      }),
      nomor1: new FormControl(formBASTPBPRawValue.nomor1, {
        validators: [Validators.maxLength(50)],
      }),
      jumlah1: new FormControl(formBASTPBPRawValue.jumlah1, {
        validators: [Validators.maxLength(3)],
      }),
      nama2: new FormControl(formBASTPBPRawValue.nama2, {
        validators: [Validators.maxLength(100)],
      }),
      alamat2: new FormControl(formBASTPBPRawValue.alamat2, {
        validators: [Validators.maxLength(100)],
      }),
      nomor2: new FormControl(formBASTPBPRawValue.nomor2, {
        validators: [Validators.maxLength(50)],
      }),
      jumlah2: new FormControl(formBASTPBPRawValue.jumlah2, {
        validators: [Validators.maxLength(3)],
      }),
      nama3: new FormControl(formBASTPBPRawValue.nama3, {
        validators: [Validators.maxLength(100)],
      }),
      alamat3: new FormControl(formBASTPBPRawValue.alamat3, {
        validators: [Validators.maxLength(100)],
      }),
      nomor3: new FormControl(formBASTPBPRawValue.nomor3, {
        validators: [Validators.maxLength(50)],
      }),
      jumlah3: new FormControl(formBASTPBPRawValue.jumlah3, {
        validators: [Validators.maxLength(3)],
      }),
      nama4: new FormControl(formBASTPBPRawValue.nama4, {
        validators: [Validators.maxLength(100)],
      }),
      alamat4: new FormControl(formBASTPBPRawValue.alamat4, {
        validators: [Validators.maxLength(100)],
      }),
      nomor4: new FormControl(formBASTPBPRawValue.nomor4, {
        validators: [Validators.maxLength(50)],
      }),
      jumlah4: new FormControl(formBASTPBPRawValue.jumlah4, {
        validators: [Validators.maxLength(3)],
      }),
      nama5: new FormControl(formBASTPBPRawValue.nama5, {
        validators: [Validators.maxLength(100)],
      }),
      alamat5: new FormControl(formBASTPBPRawValue.alamat5, {
        validators: [Validators.maxLength(100)],
      }),
      nomor5: new FormControl(formBASTPBPRawValue.nomor5, {
        validators: [Validators.maxLength(50)],
      }),
      jumlah5: new FormControl(formBASTPBPRawValue.jumlah5, {
        validators: [Validators.maxLength(3)],
      }),
      nama6: new FormControl(formBASTPBPRawValue.nama6, {
        validators: [Validators.maxLength(100)],
      }),
      nama7: new FormControl(formBASTPBPRawValue.nama7, {
        validators: [Validators.maxLength(100)],
      }),
      alamat7: new FormControl(formBASTPBPRawValue.alamat7, {
        validators: [Validators.maxLength(100)],
      }),
      nomor7: new FormControl(formBASTPBPRawValue.nomor7, {
        validators: [Validators.maxLength(50)],
      }),
      jumlah7: new FormControl(formBASTPBPRawValue.jumlah7, {
        validators: [Validators.maxLength(3)],
      }),
      nama8: new FormControl(formBASTPBPRawValue.nama8, {
        validators: [Validators.maxLength(100)],
      }),
      alamat8: new FormControl(formBASTPBPRawValue.alamat8, {
        validators: [Validators.maxLength(100)],
      }),
      nomor8: new FormControl(formBASTPBPRawValue.nomor8, {
        validators: [Validators.maxLength(50)],
      }),
      jumlah8: new FormControl(formBASTPBPRawValue.jumlah8, {
        validators: [Validators.maxLength(3)],
      }),
      nama9: new FormControl(formBASTPBPRawValue.nama9, {
        validators: [Validators.maxLength(100)],
      }),
      alamat9: new FormControl(formBASTPBPRawValue.alamat9, {
        validators: [Validators.maxLength(100)],
      }),
      nomor9: new FormControl(formBASTPBPRawValue.nomor9, {
        validators: [Validators.maxLength(50)],
      }),
      jumlah9: new FormControl(formBASTPBPRawValue.jumlah9, {
        validators: [Validators.maxLength(3)],
      }),
      nama10: new FormControl(formBASTPBPRawValue.nama10, {
        validators: [Validators.maxLength(100)],
      }),
      alamat10: new FormControl(formBASTPBPRawValue.alamat10, {
        validators: [Validators.maxLength(100)],
      }),
      nomor10: new FormControl(formBASTPBPRawValue.nomor10, {
        validators: [Validators.maxLength(50)],
      }),
      jumlah10: new FormControl(formBASTPBPRawValue.jumlah10, {
        validators: [Validators.maxLength(3)],
      }),
      alamat6: new FormControl(formBASTPBPRawValue.alamat6, {
        validators: [Validators.maxLength(100)],
      }),
      nomor6: new FormControl(formBASTPBPRawValue.nomor6, {
        validators: [Validators.maxLength(50)],
      }),
      jumlah6: new FormControl(formBASTPBPRawValue.jumlah6, {
        validators: [Validators.maxLength(3)],
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
