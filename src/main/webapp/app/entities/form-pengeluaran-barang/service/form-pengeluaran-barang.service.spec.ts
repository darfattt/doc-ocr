import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IFormPengeluaranBarang } from '../form-pengeluaran-barang.model';
import {
  sampleWithRequiredData,
  sampleWithNewData,
  sampleWithPartialData,
  sampleWithFullData,
} from '../form-pengeluaran-barang.test-samples';

import { FormPengeluaranBarangService, RestFormPengeluaranBarang } from './form-pengeluaran-barang.service';

const requireRestSample: RestFormPengeluaranBarang = {
  ...sampleWithRequiredData,
  createdDate: sampleWithRequiredData.createdDate?.toJSON(),
  lastModifiedDate: sampleWithRequiredData.lastModifiedDate?.toJSON(),
};

describe('FormPengeluaranBarang Service', () => {
  let service: FormPengeluaranBarangService;
  let httpMock: HttpTestingController;
  let expectedResult: IFormPengeluaranBarang | IFormPengeluaranBarang[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(FormPengeluaranBarangService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find('ABC').subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a FormPengeluaranBarang', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const formPengeluaranBarang = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(formPengeluaranBarang).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a FormPengeluaranBarang', () => {
      const formPengeluaranBarang = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(formPengeluaranBarang).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a FormPengeluaranBarang', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of FormPengeluaranBarang', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a FormPengeluaranBarang', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addFormPengeluaranBarangToCollectionIfMissing', () => {
      it('should add a FormPengeluaranBarang to an empty array', () => {
        const formPengeluaranBarang: IFormPengeluaranBarang = sampleWithRequiredData;
        expectedResult = service.addFormPengeluaranBarangToCollectionIfMissing([], formPengeluaranBarang);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(formPengeluaranBarang);
      });

      it('should not add a FormPengeluaranBarang to an array that contains it', () => {
        const formPengeluaranBarang: IFormPengeluaranBarang = sampleWithRequiredData;
        const formPengeluaranBarangCollection: IFormPengeluaranBarang[] = [
          {
            ...formPengeluaranBarang,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addFormPengeluaranBarangToCollectionIfMissing(formPengeluaranBarangCollection, formPengeluaranBarang);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a FormPengeluaranBarang to an array that doesn't contain it", () => {
        const formPengeluaranBarang: IFormPengeluaranBarang = sampleWithRequiredData;
        const formPengeluaranBarangCollection: IFormPengeluaranBarang[] = [sampleWithPartialData];
        expectedResult = service.addFormPengeluaranBarangToCollectionIfMissing(formPengeluaranBarangCollection, formPengeluaranBarang);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(formPengeluaranBarang);
      });

      it('should add only unique FormPengeluaranBarang to an array', () => {
        const formPengeluaranBarangArray: IFormPengeluaranBarang[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const formPengeluaranBarangCollection: IFormPengeluaranBarang[] = [sampleWithRequiredData];
        expectedResult = service.addFormPengeluaranBarangToCollectionIfMissing(
          formPengeluaranBarangCollection,
          ...formPengeluaranBarangArray
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const formPengeluaranBarang: IFormPengeluaranBarang = sampleWithRequiredData;
        const formPengeluaranBarang2: IFormPengeluaranBarang = sampleWithPartialData;
        expectedResult = service.addFormPengeluaranBarangToCollectionIfMissing([], formPengeluaranBarang, formPengeluaranBarang2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(formPengeluaranBarang);
        expect(expectedResult).toContain(formPengeluaranBarang2);
      });

      it('should accept null and undefined values', () => {
        const formPengeluaranBarang: IFormPengeluaranBarang = sampleWithRequiredData;
        expectedResult = service.addFormPengeluaranBarangToCollectionIfMissing([], null, formPengeluaranBarang, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(formPengeluaranBarang);
      });

      it('should return initial array if no FormPengeluaranBarang is added', () => {
        const formPengeluaranBarangCollection: IFormPengeluaranBarang[] = [sampleWithRequiredData];
        expectedResult = service.addFormPengeluaranBarangToCollectionIfMissing(formPengeluaranBarangCollection, undefined, null);
        expect(expectedResult).toEqual(formPengeluaranBarangCollection);
      });
    });

    describe('compareFormPengeluaranBarang', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareFormPengeluaranBarang(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareFormPengeluaranBarang(entity1, entity2);
        const compareResult2 = service.compareFormPengeluaranBarang(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareFormPengeluaranBarang(entity1, entity2);
        const compareResult2 = service.compareFormPengeluaranBarang(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareFormPengeluaranBarang(entity1, entity2);
        const compareResult2 = service.compareFormPengeluaranBarang(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
