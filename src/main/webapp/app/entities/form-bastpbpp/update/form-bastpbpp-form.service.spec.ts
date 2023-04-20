import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../form-bastpbpp.test-samples';

import { FormBASTPBPPFormService } from './form-bastpbpp-form.service';

describe('FormBASTPBPP Form Service', () => {
  let service: FormBASTPBPPFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FormBASTPBPPFormService);
  });

  describe('Service methods', () => {
    describe('createFormBASTPBPPFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createFormBASTPBPPFormGroup();

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

      it('passing IFormBASTPBPP should create a new form with FormGroup', () => {
        const formGroup = service.createFormBASTPBPPFormGroup(sampleWithRequiredData);

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

    describe('getFormBASTPBPP', () => {
      it('should return NewFormBASTPBPP for default FormBASTPBPP initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createFormBASTPBPPFormGroup(sampleWithNewData);

        const formBASTPBPP = service.getFormBASTPBPP(formGroup) as any;

        expect(formBASTPBPP).toMatchObject(sampleWithNewData);
      });

      it('should return NewFormBASTPBPP for empty FormBASTPBPP initial value', () => {
        const formGroup = service.createFormBASTPBPPFormGroup();

        const formBASTPBPP = service.getFormBASTPBPP(formGroup) as any;

        expect(formBASTPBPP).toMatchObject({});
      });

      it('should return IFormBASTPBPP', () => {
        const formGroup = service.createFormBASTPBPPFormGroup(sampleWithRequiredData);

        const formBASTPBPP = service.getFormBASTPBPP(formGroup) as any;

        expect(formBASTPBPP).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IFormBASTPBPP should not enable id FormControl', () => {
        const formGroup = service.createFormBASTPBPPFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewFormBASTPBPP should disable id FormControl', () => {
        const formGroup = service.createFormBASTPBPPFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
