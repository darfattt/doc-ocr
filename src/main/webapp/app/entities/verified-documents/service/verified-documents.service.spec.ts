import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IVerifiedDocuments } from '../verified-documents.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../verified-documents.test-samples';

import { VerifiedDocumentsService, RestVerifiedDocuments } from './verified-documents.service';

const requireRestSample: RestVerifiedDocuments = {
  ...sampleWithRequiredData,
  createdDate: sampleWithRequiredData.createdDate?.toJSON(),
  lastModifiedDate: sampleWithRequiredData.lastModifiedDate?.toJSON(),
};

describe('VerifiedDocuments Service', () => {
  let service: VerifiedDocumentsService;
  let httpMock: HttpTestingController;
  let expectedResult: IVerifiedDocuments | IVerifiedDocuments[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(VerifiedDocumentsService);
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

    it('should create a VerifiedDocuments', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const verifiedDocuments = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(verifiedDocuments).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a VerifiedDocuments', () => {
      const verifiedDocuments = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(verifiedDocuments).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a VerifiedDocuments', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of VerifiedDocuments', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a VerifiedDocuments', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addVerifiedDocumentsToCollectionIfMissing', () => {
      it('should add a VerifiedDocuments to an empty array', () => {
        const verifiedDocuments: IVerifiedDocuments = sampleWithRequiredData;
        expectedResult = service.addVerifiedDocumentsToCollectionIfMissing([], verifiedDocuments);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(verifiedDocuments);
      });

      it('should not add a VerifiedDocuments to an array that contains it', () => {
        const verifiedDocuments: IVerifiedDocuments = sampleWithRequiredData;
        const verifiedDocumentsCollection: IVerifiedDocuments[] = [
          {
            ...verifiedDocuments,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addVerifiedDocumentsToCollectionIfMissing(verifiedDocumentsCollection, verifiedDocuments);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a VerifiedDocuments to an array that doesn't contain it", () => {
        const verifiedDocuments: IVerifiedDocuments = sampleWithRequiredData;
        const verifiedDocumentsCollection: IVerifiedDocuments[] = [sampleWithPartialData];
        expectedResult = service.addVerifiedDocumentsToCollectionIfMissing(verifiedDocumentsCollection, verifiedDocuments);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(verifiedDocuments);
      });

      it('should add only unique VerifiedDocuments to an array', () => {
        const verifiedDocumentsArray: IVerifiedDocuments[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const verifiedDocumentsCollection: IVerifiedDocuments[] = [sampleWithRequiredData];
        expectedResult = service.addVerifiedDocumentsToCollectionIfMissing(verifiedDocumentsCollection, ...verifiedDocumentsArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const verifiedDocuments: IVerifiedDocuments = sampleWithRequiredData;
        const verifiedDocuments2: IVerifiedDocuments = sampleWithPartialData;
        expectedResult = service.addVerifiedDocumentsToCollectionIfMissing([], verifiedDocuments, verifiedDocuments2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(verifiedDocuments);
        expect(expectedResult).toContain(verifiedDocuments2);
      });

      it('should accept null and undefined values', () => {
        const verifiedDocuments: IVerifiedDocuments = sampleWithRequiredData;
        expectedResult = service.addVerifiedDocumentsToCollectionIfMissing([], null, verifiedDocuments, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(verifiedDocuments);
      });

      it('should return initial array if no VerifiedDocuments is added', () => {
        const verifiedDocumentsCollection: IVerifiedDocuments[] = [sampleWithRequiredData];
        expectedResult = service.addVerifiedDocumentsToCollectionIfMissing(verifiedDocumentsCollection, undefined, null);
        expect(expectedResult).toEqual(verifiedDocumentsCollection);
      });
    });

    describe('compareVerifiedDocuments', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareVerifiedDocuments(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareVerifiedDocuments(entity1, entity2);
        const compareResult2 = service.compareVerifiedDocuments(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareVerifiedDocuments(entity1, entity2);
        const compareResult2 = service.compareVerifiedDocuments(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareVerifiedDocuments(entity1, entity2);
        const compareResult2 = service.compareVerifiedDocuments(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
