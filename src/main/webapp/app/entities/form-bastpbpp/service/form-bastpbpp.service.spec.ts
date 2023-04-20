import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IFormBASTPBPP } from '../form-bastpbpp.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../form-bastpbpp.test-samples';

import { FormBASTPBPPService, RestFormBASTPBPP } from './form-bastpbpp.service';

const requireRestSample: RestFormBASTPBPP = {
  ...sampleWithRequiredData,
  createdDate: sampleWithRequiredData.createdDate?.toJSON(),
  lastModifiedDate: sampleWithRequiredData.lastModifiedDate?.toJSON(),
};

describe('FormBASTPBPP Service', () => {
  let service: FormBASTPBPPService;
  let httpMock: HttpTestingController;
  let expectedResult: IFormBASTPBPP | IFormBASTPBPP[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(FormBASTPBPPService);
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

    it('should create a FormBASTPBPP', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const formBASTPBPP = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(formBASTPBPP).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a FormBASTPBPP', () => {
      const formBASTPBPP = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(formBASTPBPP).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a FormBASTPBPP', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of FormBASTPBPP', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a FormBASTPBPP', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addFormBASTPBPPToCollectionIfMissing', () => {
      it('should add a FormBASTPBPP to an empty array', () => {
        const formBASTPBPP: IFormBASTPBPP = sampleWithRequiredData;
        expectedResult = service.addFormBASTPBPPToCollectionIfMissing([], formBASTPBPP);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(formBASTPBPP);
      });

      it('should not add a FormBASTPBPP to an array that contains it', () => {
        const formBASTPBPP: IFormBASTPBPP = sampleWithRequiredData;
        const formBASTPBPPCollection: IFormBASTPBPP[] = [
          {
            ...formBASTPBPP,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addFormBASTPBPPToCollectionIfMissing(formBASTPBPPCollection, formBASTPBPP);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a FormBASTPBPP to an array that doesn't contain it", () => {
        const formBASTPBPP: IFormBASTPBPP = sampleWithRequiredData;
        const formBASTPBPPCollection: IFormBASTPBPP[] = [sampleWithPartialData];
        expectedResult = service.addFormBASTPBPPToCollectionIfMissing(formBASTPBPPCollection, formBASTPBPP);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(formBASTPBPP);
      });

      it('should add only unique FormBASTPBPP to an array', () => {
        const formBASTPBPPArray: IFormBASTPBPP[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const formBASTPBPPCollection: IFormBASTPBPP[] = [sampleWithRequiredData];
        expectedResult = service.addFormBASTPBPPToCollectionIfMissing(formBASTPBPPCollection, ...formBASTPBPPArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const formBASTPBPP: IFormBASTPBPP = sampleWithRequiredData;
        const formBASTPBPP2: IFormBASTPBPP = sampleWithPartialData;
        expectedResult = service.addFormBASTPBPPToCollectionIfMissing([], formBASTPBPP, formBASTPBPP2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(formBASTPBPP);
        expect(expectedResult).toContain(formBASTPBPP2);
      });

      it('should accept null and undefined values', () => {
        const formBASTPBPP: IFormBASTPBPP = sampleWithRequiredData;
        expectedResult = service.addFormBASTPBPPToCollectionIfMissing([], null, formBASTPBPP, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(formBASTPBPP);
      });

      it('should return initial array if no FormBASTPBPP is added', () => {
        const formBASTPBPPCollection: IFormBASTPBPP[] = [sampleWithRequiredData];
        expectedResult = service.addFormBASTPBPPToCollectionIfMissing(formBASTPBPPCollection, undefined, null);
        expect(expectedResult).toEqual(formBASTPBPPCollection);
      });
    });

    describe('compareFormBASTPBPP', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareFormBASTPBPP(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareFormBASTPBPP(entity1, entity2);
        const compareResult2 = service.compareFormBASTPBPP(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareFormBASTPBPP(entity1, entity2);
        const compareResult2 = service.compareFormBASTPBPP(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareFormBASTPBPP(entity1, entity2);
        const compareResult2 = service.compareFormBASTPBPP(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
