import dayjs from 'dayjs/esm';

import { IFormBASTPBP, NewFormBASTPBP } from './form-bastpbp.model';

export const sampleWithRequiredData: IFormBASTPBP = {
  id: '7d167fef-3c3e-4077-94b9-ce5cce56dbb8',
  active: true,
  createdDate: dayjs('2023-04-17T16:17'),
};

export const sampleWithPartialData: IFormBASTPBP = {
  id: '92320945-4262-4c0b-9378-fcbb6eb2f1ac',
  status: 'Lo',
  active: false,
  remarks: 'connecting responsive maroon',
  createdDate: dayjs('2023-04-17T13:25'),
  lastModifiedDate: dayjs('2023-04-18T08:03'),
  lastModifiedBy: 'Armenia motivating',
};

export const sampleWithFullData: IFormBASTPBP = {
  id: '4c4af6ef-21a3-4ad2-a336-7a3b998d2a1f',
  status: 'Ru',
  active: true,
  remarks: 'Well Malagasy indexing',
  createdDate: dayjs('2023-04-17T19:08'),
  createdBy: 'haptic Plastic',
  lastModifiedDate: dayjs('2023-04-17T21:23'),
  lastModifiedBy: 'withdrawal deposit Loan',
};

export const sampleWithNewData: NewFormBASTPBP = {
  active: true,
  createdDate: dayjs('2023-04-18T01:15'),
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
