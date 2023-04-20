import dayjs from 'dayjs/esm';

import { IFormPernyataan, NewFormPernyataan } from './form-pernyataan.model';

export const sampleWithRequiredData: IFormPernyataan = {
  id: '9d0e1fcc-c48c-4a12-b924-22a68cf893fb',
  active: true,
  createdDate: dayjs('2023-04-17T20:39'),
};

export const sampleWithPartialData: IFormPernyataan = {
  id: '850a2bb1-c30d-482e-9063-235f736b76d0',
  active: true,
  remarks: 'overriding enhance Kids',
  createdDate: dayjs('2023-04-18T00:39'),
};

export const sampleWithFullData: IFormPernyataan = {
  id: '7fdf2b0f-392a-4a90-a91e-f2a677e6b6cd',
  status: 'Le',
  active: true,
  remarks: 'value-added National',
  createdDate: dayjs('2023-04-18T08:04'),
  createdBy: 'e-commerce',
  lastModifiedDate: dayjs('2023-04-18T00:50'),
  lastModifiedBy: 'Infrastructure',
};

export const sampleWithNewData: NewFormPernyataan = {
  active: true,
  createdDate: dayjs('2023-04-17T16:11'),
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
