import dayjs from 'dayjs/esm';

import { IDocuments, NewDocuments } from './documents.model';

export const sampleWithRequiredData: IDocuments = {
  id: '1429af99-c0a9-4b91-ac95-7ccef2858945',
  type: 'dynamic',
  name: 'coherent',
  status: 'RAM Berkshir',
  createdDate: dayjs('2023-04-17T14:52'),
};

export const sampleWithPartialData: IDocuments = {
  id: '3b2c9bdd-377e-489b-9641-13f516f3f0eb',
  type: 'Concrete Boo',
  name: 'Avon Clothing multi-state',
  status: 'Liaison',
  createdDate: dayjs('2023-04-17T18:14'),
  createdBy: 'primary feed',
};

export const sampleWithFullData: IDocuments = {
  id: '28d1a63d-792d-4be3-83e7-6e0dcf763783',
  type: 'Connecticut ',
  name: 'white Licensed',
  status: 'Fish invoice',
  createdDate: dayjs('2023-04-17T21:07'),
  createdBy: 'platforms bus Metal',
  lastModifiedDate: dayjs('2023-04-17T16:01'),
  lastModifiedBy: 'Music deposit',
};

export const sampleWithNewData: NewDocuments = {
  type: 'matrix Mall',
  name: 'Ergonomic repurpose',
  status: 'brand Tasty',
  createdDate: dayjs('2023-04-17T12:20'),
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
