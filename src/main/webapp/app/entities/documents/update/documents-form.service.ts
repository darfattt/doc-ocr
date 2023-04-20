import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IDocuments, NewDocuments } from '../documents.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IDocuments for edit and NewDocumentsFormGroupInput for create.
 */
type DocumentsFormGroupInput = IDocuments | PartialWithRequiredKeyOf<NewDocuments>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IDocuments | NewDocuments> = Omit<T, 'createdDate' | 'lastModifiedDate'> & {
  createdDate?: string | null;
  lastModifiedDate?: string | null;
};

type DocumentsFormRawValue = FormValueOf<IDocuments>;

type NewDocumentsFormRawValue = FormValueOf<NewDocuments>;

type DocumentsFormDefaults = Pick<NewDocuments, 'id' | 'createdDate' | 'lastModifiedDate'>;

type DocumentsFormGroupContent = {
  id: FormControl<DocumentsFormRawValue['id'] | NewDocuments['id']>;
  type: FormControl<DocumentsFormRawValue['type']>;
  name: FormControl<DocumentsFormRawValue['name']>;
  status: FormControl<DocumentsFormRawValue['status']>;
  createdDate: FormControl<DocumentsFormRawValue['createdDate']>;
  createdBy: FormControl<DocumentsFormRawValue['createdBy']>;
  lastModifiedDate: FormControl<DocumentsFormRawValue['lastModifiedDate']>;
  lastModifiedBy: FormControl<DocumentsFormRawValue['lastModifiedBy']>;
};

export type DocumentsFormGroup = FormGroup<DocumentsFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class DocumentsFormService {
  createDocumentsFormGroup(documents: DocumentsFormGroupInput = { id: null }): DocumentsFormGroup {
    const documentsRawValue = this.convertDocumentsToDocumentsRawValue({
      ...this.getFormDefaults(),
      ...documents,
    });
    return new FormGroup<DocumentsFormGroupContent>({
      id: new FormControl(
        { value: documentsRawValue.id, disabled: documentsRawValue.id !== null },
        {
          nonNullable: true,
          validators: [Validators.required, Validators.maxLength(50)],
        }
      ),
      type: new FormControl(documentsRawValue.type, {
        validators: [Validators.required, Validators.maxLength(12)],
      }),
      name: new FormControl(documentsRawValue.name, {
        validators: [Validators.required, Validators.maxLength(100)],
      }),
      status: new FormControl(documentsRawValue.status, {
        validators: [Validators.required, Validators.maxLength(12)],
      }),
      createdDate: new FormControl(documentsRawValue.createdDate, {
        validators: [Validators.required],
      }),
      createdBy: new FormControl(documentsRawValue.createdBy, {
        validators: [Validators.maxLength(50)],
      }),
      lastModifiedDate: new FormControl(documentsRawValue.lastModifiedDate),
      lastModifiedBy: new FormControl(documentsRawValue.lastModifiedBy, {
        validators: [Validators.maxLength(50)],
      }),
    });
  }

  getDocuments(form: DocumentsFormGroup): IDocuments | NewDocuments {
    return this.convertDocumentsRawValueToDocuments(form.getRawValue() as DocumentsFormRawValue | NewDocumentsFormRawValue);
  }

  resetForm(form: DocumentsFormGroup, documents: DocumentsFormGroupInput): void {
    const documentsRawValue = this.convertDocumentsToDocumentsRawValue({ ...this.getFormDefaults(), ...documents });
    form.reset(
      {
        ...documentsRawValue,
        id: { value: documentsRawValue.id, disabled: documentsRawValue.id !== null },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): DocumentsFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      createdDate: currentTime,
      lastModifiedDate: currentTime,
    };
  }

  private convertDocumentsRawValueToDocuments(rawDocuments: DocumentsFormRawValue | NewDocumentsFormRawValue): IDocuments | NewDocuments {
    return {
      ...rawDocuments,
      createdDate: dayjs(rawDocuments.createdDate, DATE_TIME_FORMAT),
      lastModifiedDate: dayjs(rawDocuments.lastModifiedDate, DATE_TIME_FORMAT),
    };
  }

  private convertDocumentsToDocumentsRawValue(
    documents: IDocuments | (Partial<NewDocuments> & DocumentsFormDefaults)
  ): DocumentsFormRawValue | PartialWithRequiredKeyOf<NewDocumentsFormRawValue> {
    return {
      ...documents,
      createdDate: documents.createdDate ? documents.createdDate.format(DATE_TIME_FORMAT) : undefined,
      lastModifiedDate: documents.lastModifiedDate ? documents.lastModifiedDate.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
