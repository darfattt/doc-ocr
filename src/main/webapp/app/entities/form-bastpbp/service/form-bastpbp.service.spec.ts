import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IFormBASTPBP } from '../form-bastpbp.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../form-bastpbp.test-samples';

import { FormBASTPBPService, RestFormBASTPBP } from './form-bastpbp.service';

const requireRestSample: RestFormBASTPBP = {
  ...sampleWithRequiredData,
  createdDate: sampleWithRequiredData.createdDate?.toJSON(),
  lastModifiedDate: sampleWithRequiredData.lastModifiedDate?.toJSON(),
};

describe('FormBASTPBP Service', () => {
  let service: FormBASTPBPService;
  let httpMock: HttpTestingController;
  let expectedResult: IFormBASTPBP | IFormBASTPBP[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(FormBASTPBPService);
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

    it('should create a FormBASTPBP', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const formBASTPBP = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(formBASTPBP).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a FormBASTPBP', () => {
      const formBASTPBP = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(formBASTPBP).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a FormBASTPBP', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of FormBASTPBP', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a FormBASTPBP', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addFormBASTPBPToCollectionIfMissing', () => {
      it('should add a FormBASTPBP to an empty array', () => {
        const formBASTPBP: IFormBASTPBP = sampleWithRequiredData;
        expectedResult = service.addFormBASTPBPToCollectionIfMissing([], formBASTPBP);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(formBASTPBP);
      });

      it('should not add a FormBASTPBP to an array that contains it', () => {
        const formBASTPBP: IFormBASTPBP = sampleWithRequiredData;
        const formBASTPBPCollection: IFormBASTPBP[] = [
          {
            ...formBASTPBP,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addFormBASTPBPToCollectionIfMissing(formBASTPBPCollection, formBASTPBP);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a FormBASTPBP to an array that doesn't contain it", () => {
        const formBASTPBP: IFormBASTPBP = sampleWithRequiredData;
        const formBASTPBPCollection: IFormBASTPBP[] = [sampleWithPartialData];
        expectedResult = service.addFormBASTPBPToCollectionIfMissing(formBASTPBPCollection, formBASTPBP);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(formBASTPBP);
      });

      it('should add only unique FormBASTPBP to an array', () => {
        const formBASTPBPArray: IFormBASTPBP[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const formBASTPBPCollection: IFormBASTPBP[] = [sampleWithRequiredData];
        expectedResult = service.addFormBASTPBPToCollectionIfMissing(formBASTPBPCollection, ...formBASTPBPArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const formBASTPBP: IFormBASTPBP = sampleWithRequiredData;
        const formBASTPBP2: IFormBASTPBP = sampleWithPartialData;
        expectedResult = service.addFormBASTPBPToCollectionIfMissing([], formBASTPBP, formBASTPBP2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(formBASTPBP);
        expect(expectedResult).toContain(formBASTPBP2);
      });

      it('should accept null and undefined values', () => {
        const formBASTPBP: IFormBASTPBP = sampleWithRequiredData;
        expectedResult = service.addFormBASTPBPToCollectionIfMissing([], null, formBASTPBP, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(formBASTPBP);
      });

      it('should return initial array if no FormBASTPBP is added', () => {
        const formBASTPBPCollection: IFormBASTPBP[] = [sampleWithRequiredData];
        expectedResult = service.addFormBASTPBPToCollectionIfMissing(formBASTPBPCollection, undefined, null);
        expect(expectedResult).toEqual(formBASTPBPCollection);
      });
    });

    describe('compareFormBASTPBP', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareFormBASTPBP(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareFormBASTPBP(entity1, entity2);
        const compareResult2 = service.compareFormBASTPBP(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareFormBASTPBP(entity1, entity2);
        const compareResult2 = service.compareFormBASTPBP(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareFormBASTPBP(entity1, entity2);
        const compareResult2 = service.compareFormBASTPBP(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
