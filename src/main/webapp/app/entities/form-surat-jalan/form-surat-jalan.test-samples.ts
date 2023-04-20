import dayjs from 'dayjs/esm';

import { IFormSuratJalan, NewFormSuratJalan } from './form-surat-jalan.model';

export const sampleWithRequiredData: IFormSuratJalan = {
  id: '4d315892-c366-4335-9765-33ff073c1d0e',
  active: true,
  createdDate: dayjs('2023-04-17T21:38'),
};

export const sampleWithPartialData: IFormSuratJalan = {
  id: 'fc6beb89-d7af-459a-b281-684241082937',
  status: 'Ga',
  active: true,
  createdDate: dayjs('2023-04-18T08:01'),
};

export const sampleWithFullData: IFormSuratJalan = {
  id: '263aff67-c7e1-4c18-8d71-2bf02c048124',
  status: 'na',
  active: false,
  remarks: 'Car Principal empowering',
  createdDate: dayjs('2023-04-17T12:36'),
  createdBy: 'Kitts architecture metrics',
  lastModifiedDate: dayjs('2023-04-18T04:39'),
  lastModifiedBy: 'neural vortals CSS',
};

export const sampleWithNewData: NewFormSuratJalan = {
  active: false,
  createdDate: dayjs('2023-04-17T15:57'),
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
