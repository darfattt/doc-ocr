import dayjs from 'dayjs/esm';

import { IFormPengeluaranBarang, NewFormPengeluaranBarang } from './form-pengeluaran-barang.model';

export const sampleWithRequiredData: IFormPengeluaranBarang = {
  id: '93996f82-8b98-4b16-977b-f5404969c153',
  active: true,
  createdDate: dayjs('2023-04-17T13:54'),
};

export const sampleWithPartialData: IFormPengeluaranBarang = {
  id: '3b172ec3-35ae-4f7f-8f76-a6ca6e118781',
  active: true,
  remarks: 'JSON transmit',
  createdDate: dayjs('2023-04-17T16:11'),
  createdBy: 'Centralized',
  lastModifiedBy: 'grow paradigms Rustic',
};

export const sampleWithFullData: IFormPengeluaranBarang = {
  id: '38f85c01-1d9b-41d4-ab22-f9e2e5af2c11',
  status: 'Bo',
  active: false,
  remarks: 'Centralized Mississippi Mountains',
  createdDate: dayjs('2023-04-17T10:08'),
  createdBy: 'Borders Christmas Representative',
  lastModifiedDate: dayjs('2023-04-18T07:27'),
  lastModifiedBy: 'Kentucky',
};

export const sampleWithNewData: NewFormPengeluaranBarang = {
  active: true,
  createdDate: dayjs('2023-04-17T20:58'),
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
