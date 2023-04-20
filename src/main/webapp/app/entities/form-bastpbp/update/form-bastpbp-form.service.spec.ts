import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../form-bastpbp.test-samples';

import { FormBASTPBPFormService } from './form-bastpbp-form.service';

describe('FormBASTPBP Form Service', () => {
  let service: FormBASTPBPFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FormBASTPBPFormService);
  });

  describe('Service methods', () => {
    describe('createFormBASTPBPFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createFormBASTPBPFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            status: expect.any(Object),
            active: expect.any(Object),
            remarks: expect.any(Object),
            createdDate: expect.any(Object),
            createdBy: expect.any(Object),
            lastModifiedDate: expect.any(Object),
            lastModifiedBy: expect.any(Object),
          })
        );
      });

      it('passing IFormBASTPBP should create a new form with FormGroup', () => {
        const formGroup = service.createFormBASTPBPFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            status: expect.any(Object),
            active: expect.any(Object),
            remarks: expect.any(Object),
            createdDate: expect.any(Object),
            createdBy: expect.any(Object),
            lastModifiedDate: expect.any(Object),
            lastModifiedBy: expect.any(Object),
          })
        );
      });
    });

    describe('getFormBASTPBP', () => {
      it('should return NewFormBASTPBP for default FormBASTPBP initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createFormBASTPBPFormGroup(sampleWithNewData);

        const formBASTPBP = service.getFormBASTPBP(formGroup) as any;

        expect(formBASTPBP).toMatchObject(sampleWithNewData);
      });

      it('should return NewFormBASTPBP for empty FormBASTPBP initial value', () => {
        const formGroup = service.createFormBASTPBPFormGroup();

        const formBASTPBP = service.getFormBASTPBP(formGroup) as any;

        expect(formBASTPBP).toMatchObject({});
      });

      it('should return IFormBASTPBP', () => {
        const formGroup = service.createFormBASTPBPFormGroup(sampleWithRequiredData);

        const formBASTPBP = service.getFormBASTPBP(formGroup) as any;

        expect(formBASTPBP).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IFormBASTPBP should not enable id FormControl', () => {
        const formGroup = service.createFormBASTPBPFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewFormBASTPBP should disable id FormControl', () => {
        const formGroup = service.createFormBASTPBPFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
