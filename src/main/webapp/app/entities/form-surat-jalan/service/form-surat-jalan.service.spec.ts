import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IFormSuratJalan } from '../form-surat-jalan.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../form-surat-jalan.test-samples';

import { FormSuratJalanService, RestFormSuratJalan } from './form-surat-jalan.service';

const requireRestSample: RestFormSuratJalan = {
  ...sampleWithRequiredData,
  createdDate: sampleWithRequiredData.createdDate?.toJSON(),
  lastModifiedDate: sampleWithRequiredData.lastModifiedDate?.toJSON(),
};

describe('FormSuratJalan Service', () => {
  let service: FormSuratJalanService;
  let httpMock: HttpTestingController;
  let expectedResult: IFormSuratJalan | IFormSuratJalan[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(FormSuratJalanService);
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

    it('should create a FormSuratJalan', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const formSuratJalan = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(formSuratJalan).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a FormSuratJalan', () => {
      const formSuratJalan = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(formSuratJalan).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a FormSuratJalan', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of FormSuratJalan', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a FormSuratJalan', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addFormSuratJalanToCollectionIfMissing', () => {
      it('should add a FormSuratJalan to an empty array', () => {
        const formSuratJalan: IFormSuratJalan = sampleWithRequiredData;
        expectedResult = service.addFormSuratJalanToCollectionIfMissing([], formSuratJalan);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(formSuratJalan);
      });

      it('should not add a FormSuratJalan to an array that contains it', () => {
        const formSuratJalan: IFormSuratJalan = sampleWithRequiredData;
        const formSuratJalanCollection: IFormSuratJalan[] = [
          {
            ...formSuratJalan,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addFormSuratJalanToCollectionIfMissing(formSuratJalanCollection, formSuratJalan);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a FormSuratJalan to an array that doesn't contain it", () => {
        const formSuratJalan: IFormSuratJalan = sampleWithRequiredData;
        const formSuratJalanCollection: IFormSuratJalan[] = [sampleWithPartialData];
        expectedResult = service.addFormSuratJalanToCollectionIfMissing(formSuratJalanCollection, formSuratJalan);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(formSuratJalan);
      });

      it('should add only unique FormSuratJalan to an array', () => {
        const formSuratJalanArray: IFormSuratJalan[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const formSuratJalanCollection: IFormSuratJalan[] = [sampleWithRequiredData];
        expectedResult = service.addFormSuratJalanToCollectionIfMissing(formSuratJalanCollection, ...formSuratJalanArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const formSuratJalan: IFormSuratJalan = sampleWithRequiredData;
        const formSuratJalan2: IFormSuratJalan = sampleWithPartialData;
        expectedResult = service.addFormSuratJalanToCollectionIfMissing([], formSuratJalan, formSuratJalan2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(formSuratJalan);
        expect(expectedResult).toContain(formSuratJalan2);
      });

      it('should accept null and undefined values', () => {
        const formSuratJalan: IFormSuratJalan = sampleWithRequiredData;
        expectedResult = service.addFormSuratJalanToCollectionIfMissing([], null, formSuratJalan, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(formSuratJalan);
      });

      it('should return initial array if no FormSuratJalan is added', () => {
        const formSuratJalanCollection: IFormSuratJalan[] = [sampleWithRequiredData];
        expectedResult = service.addFormSuratJalanToCollectionIfMissing(formSuratJalanCollection, undefined, null);
        expect(expectedResult).toEqual(formSuratJalanCollection);
      });
    });

    describe('compareFormSuratJalan', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareFormSuratJalan(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareFormSuratJalan(entity1, entity2);
        const compareResult2 = service.compareFormSuratJalan(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareFormSuratJalan(entity1, entity2);
        const compareResult2 = service.compareFormSuratJalan(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareFormSuratJalan(entity1, entity2);
        const compareResult2 = service.compareFormSuratJalan(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
