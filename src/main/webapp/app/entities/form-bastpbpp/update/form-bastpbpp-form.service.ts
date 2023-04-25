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
  contents: FormControl<FormBASTPBPPFormRawValue['contents']>;
  documentTitle: FormControl<FormBASTPBPPFormRawValue['documentTitle']>;
  kelurahanDesa: FormControl<FormBASTPBPPFormRawValue['kelurahanDesa']>;
  kecamatan: FormControl<FormBASTPBPPFormRawValue['kecamatan']>;
  kabupatenKota: FormControl<FormBASTPBPPFormRawValue['kabupatenKota']>;
  provinsi: FormControl<FormBASTPBPPFormRawValue['provinsi']>;
  pbpTidakDitemukan1: FormControl<FormBASTPBPPFormRawValue['pbpTidakDitemukan1']>;
  sebabPenggantian1: FormControl<FormBASTPBPPFormRawValue['sebabPenggantian1']>;
  pbpPengganti1: FormControl<FormBASTPBPPFormRawValue['pbpPengganti1']>;
  alamatPbpPengganti1: FormControl<FormBASTPBPPFormRawValue['alamatPbpPengganti1']>;
  pbpTidakDitemukan2: FormControl<FormBASTPBPPFormRawValue['pbpTidakDitemukan2']>;
  sebabPenggantian2: FormControl<FormBASTPBPPFormRawValue['sebabPenggantian2']>;
  pbpPengganti2: FormControl<FormBASTPBPPFormRawValue['pbpPengganti2']>;
  alamatPbpPengganti2: FormControl<FormBASTPBPPFormRawValue['alamatPbpPengganti2']>;
  pbpTidakDitemukan3: FormControl<FormBASTPBPPFormRawValue['pbpTidakDitemukan3']>;
  sebabPenggantian3: FormControl<FormBASTPBPPFormRawValue['sebabPenggantian3']>;
  pbpPengganti3: FormControl<FormBASTPBPPFormRawValue['pbpPengganti3']>;
  alamatPbpPengganti3: FormControl<FormBASTPBPPFormRawValue['alamatPbpPengganti3']>;
  pbpTidakDitemukan4: FormControl<FormBASTPBPPFormRawValue['pbpTidakDitemukan4']>;
  sebabPenggantian4: FormControl<FormBASTPBPPFormRawValue['sebabPenggantian4']>;
  pbpPengganti4: FormControl<FormBASTPBPPFormRawValue['pbpPengganti4']>;
  alamatPbpPengganti4: FormControl<FormBASTPBPPFormRawValue['alamatPbpPengganti4']>;
  pbpTidakDitemukan5: FormControl<FormBASTPBPPFormRawValue['pbpTidakDitemukan5']>;
  sebabPenggantian5: FormControl<FormBASTPBPPFormRawValue['sebabPenggantian5']>;
  pbpPengganti5: FormControl<FormBASTPBPPFormRawValue['pbpPengganti5']>;
  alamatPbpPengganti5: FormControl<FormBASTPBPPFormRawValue['alamatPbpPengganti5']>;
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
      contents: new FormControl(formBASTPBPPRawValue.contents, {
        validators: [Validators.maxLength(65353)],
      }),
      documentTitle: new FormControl(formBASTPBPPRawValue.documentTitle, {
        validators: [Validators.maxLength(500)],
      }),
      kelurahanDesa: new FormControl(formBASTPBPPRawValue.kelurahanDesa, {
        validators: [Validators.maxLength(100)],
      }),
      kecamatan: new FormControl(formBASTPBPPRawValue.kecamatan, {
        validators: [Validators.maxLength(100)],
      }),
      kabupatenKota: new FormControl(formBASTPBPPRawValue.kabupatenKota, {
        validators: [Validators.maxLength(100)],
      }),
      provinsi: new FormControl(formBASTPBPPRawValue.provinsi, {
        validators: [Validators.maxLength(100)],
      }),
      pbpTidakDitemukan1: new FormControl(formBASTPBPPRawValue.pbpTidakDitemukan1, {
        validators: [Validators.maxLength(100)],
      }),
      sebabPenggantian1: new FormControl(formBASTPBPPRawValue.sebabPenggantian1, {
        validators: [Validators.maxLength(100)],
      }),
      pbpPengganti1: new FormControl(formBASTPBPPRawValue.pbpPengganti1, {
        validators: [Validators.maxLength(100)],
      }),
      alamatPbpPengganti1: new FormControl(formBASTPBPPRawValue.alamatPbpPengganti1, {
        validators: [Validators.maxLength(100)],
      }),
      pbpTidakDitemukan2: new FormControl(formBASTPBPPRawValue.pbpTidakDitemukan2, {
        validators: [Validators.maxLength(100)],
      }),
      sebabPenggantian2: new FormControl(formBASTPBPPRawValue.sebabPenggantian2, {
        validators: [Validators.maxLength(100)],
      }),
      pbpPengganti2: new FormControl(formBASTPBPPRawValue.pbpPengganti2, {
        validators: [Validators.maxLength(100)],
      }),
      alamatPbpPengganti2: new FormControl(formBASTPBPPRawValue.alamatPbpPengganti2, {
        validators: [Validators.maxLength(100)],
      }),
      pbpTidakDitemukan3: new FormControl(formBASTPBPPRawValue.pbpTidakDitemukan3, {
        validators: [Validators.maxLength(100)],
      }),
      sebabPenggantian3: new FormControl(formBASTPBPPRawValue.sebabPenggantian3, {
        validators: [Validators.maxLength(100)],
      }),
      pbpPengganti3: new FormControl(formBASTPBPPRawValue.pbpPengganti3, {
        validators: [Validators.maxLength(100)],
      }),
      alamatPbpPengganti3: new FormControl(formBASTPBPPRawValue.alamatPbpPengganti3, {
        validators: [Validators.maxLength(100)],
      }),
      pbpTidakDitemukan4: new FormControl(formBASTPBPPRawValue.pbpTidakDitemukan4, {
        validators: [Validators.maxLength(100)],
      }),
      sebabPenggantian4: new FormControl(formBASTPBPPRawValue.sebabPenggantian4, {
        validators: [Validators.maxLength(100)],
      }),
      pbpPengganti4: new FormControl(formBASTPBPPRawValue.pbpPengganti4, {
        validators: [Validators.maxLength(100)],
      }),
      alamatPbpPengganti4: new FormControl(formBASTPBPPRawValue.alamatPbpPengganti4, {
        validators: [Validators.maxLength(100)],
      }),
      pbpTidakDitemukan5: new FormControl(formBASTPBPPRawValue.pbpTidakDitemukan5, {
        validators: [Validators.maxLength(100)],
      }),
      sebabPenggantian5: new FormControl(formBASTPBPPRawValue.sebabPenggantian5, {
        validators: [Validators.maxLength(100)],
      }),
      pbpPengganti5: new FormControl(formBASTPBPPRawValue.pbpPengganti5, {
        validators: [Validators.maxLength(100)],
      }),
      alamatPbpPengganti5: new FormControl(formBASTPBPPRawValue.alamatPbpPengganti5, {
        validators: [Validators.maxLength(100)],
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
