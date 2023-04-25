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
            contents: expect.any(Object),
            documentTitle: expect.any(Object),
            documentNumber: expect.any(Object),
            kelurahanDesa: expect.any(Object),
            kecamatan: expect.any(Object),
            kabupatenKota: expect.any(Object),
            provinsi: expect.any(Object),
            rtRw: expect.any(Object),
            kcu: expect.any(Object),
            kantorSerah: expect.any(Object),
            bastNumber: expect.any(Object),
            documentDescription: expect.any(Object),
            nama1: expect.any(Object),
            alamat1: expect.any(Object),
            nomor1: expect.any(Object),
            jumlah1: expect.any(Object),
            nama2: expect.any(Object),
            alamat2: expect.any(Object),
            nomor2: expect.any(Object),
            jumlah2: expect.any(Object),
            nama3: expect.any(Object),
            alamat3: expect.any(Object),
            nomor3: expect.any(Object),
            jumlah3: expect.any(Object),
            nama4: expect.any(Object),
            alamat4: expect.any(Object),
            nomor4: expect.any(Object),
            jumlah4: expect.any(Object),
            nama5: expect.any(Object),
            alamat5: expect.any(Object),
            nomor5: expect.any(Object),
            jumlah5: expect.any(Object),
            nama6: expect.any(Object),
            nama7: expect.any(Object),
            alamat7: expect.any(Object),
            nomor7: expect.any(Object),
            jumlah7: expect.any(Object),
            nama8: expect.any(Object),
            alamat8: expect.any(Object),
            nomor8: expect.any(Object),
            jumlah8: expect.any(Object),
            nama9: expect.any(Object),
            alamat9: expect.any(Object),
            nomor9: expect.any(Object),
            jumlah9: expect.any(Object),
            nama10: expect.any(Object),
            alamat10: expect.any(Object),
            nomor10: expect.any(Object),
            jumlah10: expect.any(Object),
            alamat6: expect.any(Object),
            nomor6: expect.any(Object),
            jumlah6: expect.any(Object),
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
            contents: expect.any(Object),
            documentTitle: expect.any(Object),
            documentNumber: expect.any(Object),
            kelurahanDesa: expect.any(Object),
            kecamatan: expect.any(Object),
            kabupatenKota: expect.any(Object),
            provinsi: expect.any(Object),
            rtRw: expect.any(Object),
            kcu: expect.any(Object),
            kantorSerah: expect.any(Object),
            bastNumber: expect.any(Object),
            documentDescription: expect.any(Object),
            nama1: expect.any(Object),
            alamat1: expect.any(Object),
            nomor1: expect.any(Object),
            jumlah1: expect.any(Object),
            nama2: expect.any(Object),
            alamat2: expect.any(Object),
            nomor2: expect.any(Object),
            jumlah2: expect.any(Object),
            nama3: expect.any(Object),
            alamat3: expect.any(Object),
            nomor3: expect.any(Object),
            jumlah3: expect.any(Object),
            nama4: expect.any(Object),
            alamat4: expect.any(Object),
            nomor4: expect.any(Object),
            jumlah4: expect.any(Object),
            nama5: expect.any(Object),
            alamat5: expect.any(Object),
            nomor5: expect.any(Object),
            jumlah5: expect.any(Object),
            nama6: expect.any(Object),
            nama7: expect.any(Object),
            alamat7: expect.any(Object),
            nomor7: expect.any(Object),
            jumlah7: expect.any(Object),
            nama8: expect.any(Object),
            alamat8: expect.any(Object),
            nomor8: expect.any(Object),
            jumlah8: expect.any(Object),
            nama9: expect.any(Object),
            alamat9: expect.any(Object),
            nomor9: expect.any(Object),
            jumlah9: expect.any(Object),
            nama10: expect.any(Object),
            alamat10: expect.any(Object),
            nomor10: expect.any(Object),
            jumlah10: expect.any(Object),
            alamat6: expect.any(Object),
            nomor6: expect.any(Object),
            jumlah6: expect.any(Object),
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
