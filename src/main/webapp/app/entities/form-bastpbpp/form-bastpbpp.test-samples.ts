import dayjs from 'dayjs/esm';

import { IFormBASTPBPP, NewFormBASTPBPP } from './form-bastpbpp.model';

export const sampleWithRequiredData: IFormBASTPBPP = {
  id: '95e86297-76f3-4453-a655-6b8e86d04e4e',
  active: true,
  createdDate: dayjs('2023-04-17T15:24'),
};

export const sampleWithPartialData: IFormBASTPBPP = {
  id: '5f95102f-6cea-46af-b071-23bd5ffd11b8',
  status: 'HD',
  active: true,
  createdDate: dayjs('2023-04-18T01:32'),
  createdBy: 'Tactics Handmade',
  lastModifiedDate: dayjs('2023-04-17T17:34'),
};

export const sampleWithFullData: IFormBASTPBPP = {
  id: '672fd0c8-b1fd-423d-a4b8-3ad30c30addf',
  status: 'Di',
  active: true,
  remarks: 'withdrawal',
  createdDate: dayjs('2023-04-17T12:41'),
  createdBy: 'Loan Soft',
  lastModifiedDate: dayjs('2023-04-18T08:05'),
  lastModifiedBy: 'Yuan Secured Pizza',
};

export const sampleWithNewData: NewFormBASTPBPP = {
  active: true,
  createdDate: dayjs('2023-04-17T09:53'),
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
