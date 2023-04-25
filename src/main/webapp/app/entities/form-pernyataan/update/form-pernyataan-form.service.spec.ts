import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../form-pernyataan.test-samples';

import { FormPernyataanFormService } from './form-pernyataan-form.service';

describe('FormPernyataan Form Service', () => {
  let service: FormPernyataanFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FormPernyataanFormService);
  });

  describe('Service methods', () => {
    describe('createFormPernyataanFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createFormPernyataanFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            status: expect.any(Object),
            active: expect.any(Object),
            remarks: expect.any(Object),
            contents: expect.any(Object),
            documentTitle: expect.any(Object),
            officerName: expect.any(Object),
            officerPhoneNumber: expect.any(Object),
            officerPosition: expect.any(Object),
            officerDepartment: expect.any(Object),
            kelurahanDesa: expect.any(Object),
            kecamatan: expect.any(Object),
            kabupatenKota: expect.any(Object),
            provinsi: expect.any(Object),
            pbpTidakDitemukan1: expect.any(Object),
            alamatPbpTidakDitemukan1: expect.any(Object),
            pbpPengganti1: expect.any(Object),
            alamatPbpPengganti1: expect.any(Object),
            pbpTidakDitemukan2: expect.any(Object),
            alamatPbpTidakDitemukan2: expect.any(Object),
            pbpPengganti2: expect.any(Object),
            alamatPbpPengganti2: expect.any(Object),
            pbpTidakDitemukan3: expect.any(Object),
            alamatPbpTidakDitemukan3: expect.any(Object),
            pbpPengganti3: expect.any(Object),
            alamatPbpPengganti3: expect.any(Object),
            pbpTidakDitemukan4: expect.any(Object),
            alamatPbpTidakDitemukan4: expect.any(Object),
            pbpPengganti4: expect.any(Object),
            alamatPbpPengganti4: expect.any(Object),
            pbpTidakDitemukan5: expect.any(Object),
            alamatPbpTidakDitemukan5: expect.any(Object),
            pbpPengganti5: expect.any(Object),
            alamatPbpPengganti5: expect.any(Object),
            createdDate: expect.any(Object),
            createdBy: expect.any(Object),
            lastModifiedDate: expect.any(Object),
            lastModifiedBy: expect.any(Object),
          })
        );
      });

      it('passing IFormPernyataan should create a new form with FormGroup', () => {
        const formGroup = service.createFormPernyataanFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            status: expect.any(Object),
            active: expect.any(Object),
            remarks: expect.any(Object),
            contents: expect.any(Object),
            documentTitle: expect.any(Object),
            officerName: expect.any(Object),
            officerPhoneNumber: expect.any(Object),
            officerPosition: expect.any(Object),
            officerDepartment: expect.any(Object),
            kelurahanDesa: expect.any(Object),
            kecamatan: expect.any(Object),
            kabupatenKota: expect.any(Object),
            provinsi: expect.any(Object),
            pbpTidakDitemukan1: expect.any(Object),
            alamatPbpTidakDitemukan1: expect.any(Object),
            pbpPengganti1: expect.any(Object),
            alamatPbpPengganti1: expect.any(Object),
            pbpTidakDitemukan2: expect.any(Object),
            alamatPbpTidakDitemukan2: expect.any(Object),
            pbpPengganti2: expect.any(Object),
            alamatPbpPengganti2: expect.any(Object),
            pbpTidakDitemukan3: expect.any(Object),
            alamatPbpTidakDitemukan3: expect.any(Object),
            pbpPengganti3: expect.any(Object),
            alamatPbpPengganti3: expect.any(Object),
            pbpTidakDitemukan4: expect.any(Object),
            alamatPbpTidakDitemukan4: expect.any(Object),
            pbpPengganti4: expect.any(Object),
            alamatPbpPengganti4: expect.any(Object),
            pbpTidakDitemukan5: expect.any(Object),
            alamatPbpTidakDitemukan5: expect.any(Object),
            pbpPengganti5: expect.any(Object),
            alamatPbpPengganti5: expect.any(Object),
            createdDate: expect.any(Object),
            createdBy: expect.any(Object),
            lastModifiedDate: expect.any(Object),
            lastModifiedBy: expect.any(Object),
          })
        );
      });
    });

    describe('getFormPernyataan', () => {
      it('should return NewFormPernyataan for default FormPernyataan initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createFormPernyataanFormGroup(sampleWithNewData);

        const formPernyataan = service.getFormPernyataan(formGroup) as any;

        expect(formPernyataan).toMatchObject(sampleWithNewData);
      });

      it('should return NewFormPernyataan for empty FormPernyataan initial value', () => {
        const formGroup = service.createFormPernyataanFormGroup();

        const formPernyataan = service.getFormPernyataan(formGroup) as any;

        expect(formPernyataan).toMatchObject({});
      });

      it('should return IFormPernyataan', () => {
        const formGroup = service.createFormPernyataanFormGroup(sampleWithRequiredData);

        const formPernyataan = service.getFormPernyataan(formGroup) as any;

        expect(formPernyataan).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IFormPernyataan should not enable id FormControl', () => {
        const formGroup = service.createFormPernyataanFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewFormPernyataan should disable id FormControl', () => {
        const formGroup = service.createFormPernyataanFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
