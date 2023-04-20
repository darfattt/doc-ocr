import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../verified-documents.test-samples';

import { VerifiedDocumentsFormService } from './verified-documents-form.service';

describe('VerifiedDocuments Form Service', () => {
  let service: VerifiedDocumentsFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VerifiedDocumentsFormService);
  });

  describe('Service methods', () => {
    describe('createVerifiedDocumentsFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createVerifiedDocumentsFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            type: expect.any(Object),
            name: expect.any(Object),
            status: expect.any(Object),
            contentId: expect.any(Object),
            createdDate: expect.any(Object),
            createdBy: expect.any(Object),
            lastModifiedDate: expect.any(Object),
            lastModifiedBy: expect.any(Object),
          })
        );
      });

      it('passing IVerifiedDocuments should create a new form with FormGroup', () => {
        const formGroup = service.createVerifiedDocumentsFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            type: expect.any(Object),
            name: expect.any(Object),
            status: expect.any(Object),
            contentId: expect.any(Object),
            createdDate: expect.any(Object),
            createdBy: expect.any(Object),
            lastModifiedDate: expect.any(Object),
            lastModifiedBy: expect.any(Object),
          })
        );
      });
    });

    describe('getVerifiedDocuments', () => {
      it('should return NewVerifiedDocuments for default VerifiedDocuments initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createVerifiedDocumentsFormGroup(sampleWithNewData);

        const verifiedDocuments = service.getVerifiedDocuments(formGroup) as any;

        expect(verifiedDocuments).toMatchObject(sampleWithNewData);
      });

      it('should return NewVerifiedDocuments for empty VerifiedDocuments initial value', () => {
        const formGroup = service.createVerifiedDocumentsFormGroup();

        const verifiedDocuments = service.getVerifiedDocuments(formGroup) as any;

        expect(verifiedDocuments).toMatchObject({});
      });

      it('should return IVerifiedDocuments', () => {
        const formGroup = service.createVerifiedDocumentsFormGroup(sampleWithRequiredData);

        const verifiedDocuments = service.getVerifiedDocuments(formGroup) as any;

        expect(verifiedDocuments).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IVerifiedDocuments should not enable id FormControl', () => {
        const formGroup = service.createVerifiedDocumentsFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewVerifiedDocuments should disable id FormControl', () => {
        const formGroup = service.createVerifiedDocumentsFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
