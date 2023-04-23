import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../form-pengeluaran-barang.test-samples';

import { FormPengeluaranBarangFormService } from './form-pengeluaran-barang-form.service';

describe('FormPengeluaranBarang Form Service', () => {
  let service: FormPengeluaranBarangFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FormPengeluaranBarangFormService);
  });

  describe('Service methods', () => {
    describe('createFormPengeluaranBarangFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createFormPengeluaranBarangFormGroup();

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
            status: expect.any(Object),
            date: expect.any(Object),
            productDescription: expect.any(Object),
            sourceLocation: expect.any(Object),
            lotNo: expect.any(Object),
            quantity: expect.any(Object),
            amount: expect.any(Object),
            sourceDestination: expect.any(Object),
            armadaName: expect.any(Object),
            armadaNumber: expect.any(Object),
            createdDate: expect.any(Object),
            createdBy: expect.any(Object),
            lastModifiedDate: expect.any(Object),
            lastModifiedBy: expect.any(Object),
          })
        );
      });

      it('passing IFormPengeluaranBarang should create a new form with FormGroup', () => {
        const formGroup = service.createFormPengeluaranBarangFormGroup(sampleWithRequiredData);

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
            status: expect.any(Object),
            date: expect.any(Object),
            productDescription: expect.any(Object),
            sourceLocation: expect.any(Object),
            lotNo: expect.any(Object),
            quantity: expect.any(Object),
            amount: expect.any(Object),
            sourceDestination: expect.any(Object),
            armadaName: expect.any(Object),
            armadaNumber: expect.any(Object),
            createdDate: expect.any(Object),
            createdBy: expect.any(Object),
            lastModifiedDate: expect.any(Object),
            lastModifiedBy: expect.any(Object),
          })
        );
      });
    });

    describe('getFormPengeluaranBarang', () => {
      it('should return NewFormPengeluaranBarang for default FormPengeluaranBarang initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createFormPengeluaranBarangFormGroup(sampleWithNewData);

        const formPengeluaranBarang = service.getFormPengeluaranBarang(formGroup) as any;

        expect(formPengeluaranBarang).toMatchObject(sampleWithNewData);
      });

      it('should return NewFormPengeluaranBarang for empty FormPengeluaranBarang initial value', () => {
        const formGroup = service.createFormPengeluaranBarangFormGroup();

        const formPengeluaranBarang = service.getFormPengeluaranBarang(formGroup) as any;

        expect(formPengeluaranBarang).toMatchObject({});
      });

      it('should return IFormPengeluaranBarang', () => {
        const formGroup = service.createFormPengeluaranBarangFormGroup(sampleWithRequiredData);

        const formPengeluaranBarang = service.getFormPengeluaranBarang(formGroup) as any;

        expect(formPengeluaranBarang).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IFormPengeluaranBarang should not enable id FormControl', () => {
        const formGroup = service.createFormPengeluaranBarangFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewFormPengeluaranBarang should disable id FormControl', () => {
        const formGroup = service.createFormPengeluaranBarangFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
