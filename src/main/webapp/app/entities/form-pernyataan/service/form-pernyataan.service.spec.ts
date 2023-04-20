import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IFormPernyataan } from '../form-pernyataan.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../form-pernyataan.test-samples';

import { FormPernyataanService, RestFormPernyataan } from './form-pernyataan.service';

const requireRestSample: RestFormPernyataan = {
  ...sampleWithRequiredData,
  createdDate: sampleWithRequiredData.createdDate?.toJSON(),
  lastModifiedDate: sampleWithRequiredData.lastModifiedDate?.toJSON(),
};

describe('FormPernyataan Service', () => {
  let service: FormPernyataanService;
  let httpMock: HttpTestingController;
  let expectedResult: IFormPernyataan | IFormPernyataan[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(FormPernyataanService);
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

    it('should create a FormPernyataan', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const formPernyataan = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(formPernyataan).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a FormPernyataan', () => {
      const formPernyataan = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(formPernyataan).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a FormPernyataan', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of FormPernyataan', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a FormPernyataan', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addFormPernyataanToCollectionIfMissing', () => {
      it('should add a FormPernyataan to an empty array', () => {
        const formPernyataan: IFormPernyataan = sampleWithRequiredData;
        expectedResult = service.addFormPernyataanToCollectionIfMissing([], formPernyataan);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(formPernyataan);
      });

      it('should not add a FormPernyataan to an array that contains it', () => {
        const formPernyataan: IFormPernyataan = sampleWithRequiredData;
        const formPernyataanCollection: IFormPernyataan[] = [
          {
            ...formPernyataan,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addFormPernyataanToCollectionIfMissing(formPernyataanCollection, formPernyataan);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a FormPernyataan to an array that doesn't contain it", () => {
        const formPernyataan: IFormPernyataan = sampleWithRequiredData;
        const formPernyataanCollection: IFormPernyataan[] = [sampleWithPartialData];
        expectedResult = service.addFormPernyataanToCollectionIfMissing(formPernyataanCollection, formPernyataan);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(formPernyataan);
      });

      it('should add only unique FormPernyataan to an array', () => {
        const formPernyataanArray: IFormPernyataan[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const formPernyataanCollection: IFormPernyataan[] = [sampleWithRequiredData];
        expectedResult = service.addFormPernyataanToCollectionIfMissing(formPernyataanCollection, ...formPernyataanArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const formPernyataan: IFormPernyataan = sampleWithRequiredData;
        const formPernyataan2: IFormPernyataan = sampleWithPartialData;
        expectedResult = service.addFormPernyataanToCollectionIfMissing([], formPernyataan, formPernyataan2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(formPernyataan);
        expect(expectedResult).toContain(formPernyataan2);
      });

      it('should accept null and undefined values', () => {
        const formPernyataan: IFormPernyataan = sampleWithRequiredData;
        expectedResult = service.addFormPernyataanToCollectionIfMissing([], null, formPernyataan, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(formPernyataan);
      });

      it('should return initial array if no FormPernyataan is added', () => {
        const formPernyataanCollection: IFormPernyataan[] = [sampleWithRequiredData];
        expectedResult = service.addFormPernyataanToCollectionIfMissing(formPernyataanCollection, undefined, null);
        expect(expectedResult).toEqual(formPernyataanCollection);
      });
    });

    describe('compareFormPernyataan', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareFormPernyataan(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareFormPernyataan(entity1, entity2);
        const compareResult2 = service.compareFormPernyataan(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareFormPernyataan(entity1, entity2);
        const compareResult2 = service.compareFormPernyataan(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareFormPernyataan(entity1, entity2);
        const compareResult2 = service.compareFormPernyataan(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
