import dayjs from 'dayjs/esm';

import { IVerifiedDocuments, NewVerifiedDocuments } from './verified-documents.model';

export const sampleWithRequiredData: IVerifiedDocuments = {
  id: 'c85bff26-4bd2-4f98-9b41-232bac9c46be',
  type: 'indigo Malta',
  name: 'bandwidth Infrastructure',
  status: 'Facilitator ',
  contentId: 'BCEAO online',
  createdDate: dayjs('2023-04-17T11:00'),
};

export const sampleWithPartialData: IVerifiedDocuments = {
  id: 'bac41afe-0fa2-4e6a-8806-e1da8be2e53a',
  type: 'Frozen',
  name: 'Legacy',
  status: 'core',
  contentId: 'Metal',
  createdDate: dayjs('2023-04-17T17:15'),
  lastModifiedDate: dayjs('2023-04-18T06:56'),
};

export const sampleWithFullData: IVerifiedDocuments = {
  id: '6e6e24b6-fc6b-47d5-8a30-d08dd4ff7e37',
  type: 'tan Money',
  name: 'line web-enabled',
  status: 'compress',
  contentId: 'maroon streamline',
  createdDate: dayjs('2023-04-17T22:26'),
  createdBy: 'gold mobile firewall',
  lastModifiedDate: dayjs('2023-04-17T19:45'),
  lastModifiedBy: 'Seychelles Morocco Small',
};

export const sampleWithNewData: NewVerifiedDocuments = {
  type: 'mission-crit',
  name: 'Ball withdrawal',
  status: 'TCP',
  contentId: 'encryption Officer',
  createdDate: dayjs('2023-04-17T08:38'),
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
