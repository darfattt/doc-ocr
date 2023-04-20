import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IVerifiedDocuments, NewVerifiedDocuments } from '../verified-documents.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IVerifiedDocuments for edit and NewVerifiedDocumentsFormGroupInput for create.
 */
type VerifiedDocumentsFormGroupInput = IVerifiedDocuments | PartialWithRequiredKeyOf<NewVerifiedDocuments>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IVerifiedDocuments | NewVerifiedDocuments> = Omit<T, 'createdDate' | 'lastModifiedDate'> & {
  createdDate?: string | null;
  lastModifiedDate?: string | null;
};

type VerifiedDocumentsFormRawValue = FormValueOf<IVerifiedDocuments>;

type NewVerifiedDocumentsFormRawValue = FormValueOf<NewVerifiedDocuments>;

type VerifiedDocumentsFormDefaults = Pick<NewVerifiedDocuments, 'id' | 'createdDate' | 'lastModifiedDate'>;

type VerifiedDocumentsFormGroupContent = {
  id: FormControl<VerifiedDocumentsFormRawValue['id'] | NewVerifiedDocuments['id']>;
  type: FormControl<VerifiedDocumentsFormRawValue['type']>;
  name: FormControl<VerifiedDocumentsFormRawValue['name']>;
  status: FormControl<VerifiedDocumentsFormRawValue['status']>;
  contentId: FormControl<VerifiedDocumentsFormRawValue['contentId']>;
  createdDate: FormControl<VerifiedDocumentsFormRawValue['createdDate']>;
  createdBy: FormControl<VerifiedDocumentsFormRawValue['createdBy']>;
  lastModifiedDate: FormControl<VerifiedDocumentsFormRawValue['lastModifiedDate']>;
  lastModifiedBy: FormControl<VerifiedDocumentsFormRawValue['lastModifiedBy']>;
};

export type VerifiedDocumentsFormGroup = FormGroup<VerifiedDocumentsFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class VerifiedDocumentsFormService {
  createVerifiedDocumentsFormGroup(verifiedDocuments: VerifiedDocumentsFormGroupInput = { id: null }): VerifiedDocumentsFormGroup {
    const verifiedDocumentsRawValue = this.convertVerifiedDocumentsToVerifiedDocumentsRawValue({
      ...this.getFormDefaults(),
      ...verifiedDocuments,
    });
    return new FormGroup<VerifiedDocumentsFormGroupContent>({
      id: new FormControl(
        { value: verifiedDocumentsRawValue.id, disabled: verifiedDocumentsRawValue.id !== null },
        {
          nonNullable: true,
          validators: [Validators.required, Validators.maxLength(50)],
        }
      ),
      type: new FormControl(verifiedDocumentsRawValue.type, {
        validators: [Validators.required, Validators.maxLength(12)],
      }),
      name: new FormControl(verifiedDocumentsRawValue.name, {
        validators: [Validators.required, Validators.maxLength(100)],
      }),
      status: new FormControl(verifiedDocumentsRawValue.status, {
        validators: [Validators.required, Validators.maxLength(12)],
      }),
      contentId: new FormControl(verifiedDocumentsRawValue.contentId, {
        validators: [Validators.required, Validators.maxLength(50)],
      }),
      createdDate: new FormControl(verifiedDocumentsRawValue.createdDate, {
        validators: [Validators.required],
      }),
      createdBy: new FormControl(verifiedDocumentsRawValue.createdBy, {
        validators: [Validators.maxLength(50)],
      }),
      lastModifiedDate: new FormControl(verifiedDocumentsRawValue.lastModifiedDate),
      lastModifiedBy: new FormControl(verifiedDocumentsRawValue.lastModifiedBy, {
        validators: [Validators.maxLength(50)],
      }),
    });
  }

  getVerifiedDocuments(form: VerifiedDocumentsFormGroup): IVerifiedDocuments | NewVerifiedDocuments {
    return this.convertVerifiedDocumentsRawValueToVerifiedDocuments(
      form.getRawValue() as VerifiedDocumentsFormRawValue | NewVerifiedDocumentsFormRawValue
    );
  }

  resetForm(form: VerifiedDocumentsFormGroup, verifiedDocuments: VerifiedDocumentsFormGroupInput): void {
    const verifiedDocumentsRawValue = this.convertVerifiedDocumentsToVerifiedDocumentsRawValue({
      ...this.getFormDefaults(),
      ...verifiedDocuments,
    });
    form.reset(
      {
        ...verifiedDocumentsRawValue,
        id: { value: verifiedDocumentsRawValue.id, disabled: verifiedDocumentsRawValue.id !== null },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): VerifiedDocumentsFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      createdDate: currentTime,
      lastModifiedDate: currentTime,
    };
  }

  private convertVerifiedDocumentsRawValueToVerifiedDocuments(
    rawVerifiedDocuments: VerifiedDocumentsFormRawValue | NewVerifiedDocumentsFormRawValue
  ): IVerifiedDocuments | NewVerifiedDocuments {
    return {
      ...rawVerifiedDocuments,
      createdDate: dayjs(rawVerifiedDocuments.createdDate, DATE_TIME_FORMAT),
      lastModifiedDate: dayjs(rawVerifiedDocuments.lastModifiedDate, DATE_TIME_FORMAT),
    };
  }

  private convertVerifiedDocumentsToVerifiedDocumentsRawValue(
    verifiedDocuments: IVerifiedDocuments | (Partial<NewVerifiedDocuments> & VerifiedDocumentsFormDefaults)
  ): VerifiedDocumentsFormRawValue | PartialWithRequiredKeyOf<NewVerifiedDocumentsFormRawValue> {
    return {
      ...verifiedDocuments,
      createdDate: verifiedDocuments.createdDate ? verifiedDocuments.createdDate.format(DATE_TIME_FORMAT) : undefined,
      lastModifiedDate: verifiedDocuments.lastModifiedDate ? verifiedDocuments.lastModifiedDate.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
