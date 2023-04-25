import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../form-surat-jalan.test-samples';

import { FormSuratJalanFormService } from './form-surat-jalan-form.service';

describe('FormSuratJalan Form Service', () => {
  let service: FormSuratJalanFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FormSuratJalanFormService);
  });

  describe('Service methods', () => {
    describe('createFormSuratJalanFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createFormSuratJalanFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            status: expect.any(Object),
            active: expect.any(Object),
            remarks: expect.any(Object),
            contents: expect.any(Object),
            branch: expect.any(Object),
            documentTitle: expect.any(Object),
            documentNumber: expect.any(Object),
            recipientAddress: expect.any(Object),
            npwp: expect.any(Object),
            warehouseSource: expect.any(Object),
            documentSource: expect.any(Object),
            reference: expect.any(Object),
            date: expect.any(Object),
            productDescription: expect.any(Object),
            quantity: expect.any(Object),
            amount: expect.any(Object),
            armadaNumber: expect.any(Object),
            containerNumber: expect.any(Object),
            createdDate: expect.any(Object),
            createdBy: expect.any(Object),
            lastModifiedDate: expect.any(Object),
            lastModifiedBy: expect.any(Object),
          })
        );
      });

      it('passing IFormSuratJalan should create a new form with FormGroup', () => {
        const formGroup = service.createFormSuratJalanFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            status: expect.any(Object),
            active: expect.any(Object),
            remarks: expect.any(Object),
            contents: expect.any(Object),
            branch: expect.any(Object),
            documentTitle: expect.any(Object),
            documentNumber: expect.any(Object),
            recipientAddress: expect.any(Object),
            npwp: expect.any(Object),
            warehouseSource: expect.any(Object),
            documentSource: expect.any(Object),
            reference: expect.any(Object),
            date: expect.any(Object),
            productDescription: expect.any(Object),
            quantity: expect.any(Object),
            amount: expect.any(Object),
            armadaNumber: expect.any(Object),
            containerNumber: expect.any(Object),
            createdDate: expect.any(Object),
            createdBy: expect.any(Object),
            lastModifiedDate: expect.any(Object),
            lastModifiedBy: expect.any(Object),
          })
        );
      });
    });

    describe('getFormSuratJalan', () => {
      it('should return NewFormSuratJalan for default FormSuratJalan initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createFormSuratJalanFormGroup(sampleWithNewData);

        const formSuratJalan = service.getFormSuratJalan(formGroup) as any;

        expect(formSuratJalan).toMatchObject(sampleWithNewData);
      });

      it('should return NewFormSuratJalan for empty FormSuratJalan initial value', () => {
        const formGroup = service.createFormSuratJalanFormGroup();

        const formSuratJalan = service.getFormSuratJalan(formGroup) as any;

        expect(formSuratJalan).toMatchObject({});
      });

      it('should return IFormSuratJalan', () => {
        const formGroup = service.createFormSuratJalanFormGroup(sampleWithRequiredData);

        const formSuratJalan = service.getFormSuratJalan(formGroup) as any;

        expect(formSuratJalan).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IFormSuratJalan should not enable id FormControl', () => {
        const formGroup = service.createFormSuratJalanFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewFormSuratJalan should disable id FormControl', () => {
        const formGroup = service.createFormSuratJalanFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
