import dayjs from 'dayjs/esm';

import { IFormSuratJalan, NewFormSuratJalan } from './form-surat-jalan.model';

export const sampleWithRequiredData: IFormSuratJalan = {
  id: '4d315892-c366-4335-9765-33ff073c1d0e',
  active: true,
  createdDate: dayjs('2023-04-17T21:38'),
};

export const sampleWithPartialData: IFormSuratJalan = {
  id: '72816842-4108-4293-b702-a927a0263aff',
  status: 'Sp',
  active: false,
  documentNumber: 'Sports Wooden',
  recipientAddress: 'Rustic Bouvet Front-line',
  warehouseSource: 'Metal up',
  documentSource: 'Facilitator installation haptic',
  reference: 'architecture metrics',
  date: 'SAS',
  productDescription: 'vortals CSS infomediaries',
  quantity: 9722,
  armadaNumber: 'Investment',
  containerNumber: 'bypass payment Rubber',
  createdDate: dayjs('2023-04-18T02:23'),
  lastModifiedDate: dayjs('2023-04-17T21:34'),
  lastModifiedBy: 'budgetary',
};

export const sampleWithFullData: IFormSuratJalan = {
  id: 'c906dae5-6d96-4166-839f-0395063ce7e8',
  status: 'Ta',
  active: false,
  remarks: 'hardware Jersey',
  contents: 'Lakes',
  branch: 'calculating bypassing',
  documentTitle: 'Fantastic',
  documentNumber: 'Parks',
  recipientAddress: 'synthesize action-items online',
  npwp: 'port',
  warehouseSource: 'Administrator Quality Legacy',
  documentSource: 'application Small Georgia',
  reference: 'RAM reboot',
  date: 'static hacking Lead',
  productDescription: 'national Towels Personal',
  quantity: 51804,
  amount: 50729,
  armadaNumber: 'Supervisor Administrator invoice',
  containerNumber: 'olive compress',
  createdDate: dayjs('2023-04-18T06:54'),
  createdBy: 'Motorway Open-source Guinea',
  lastModifiedDate: dayjs('2023-04-17T10:15'),
  lastModifiedBy: 'yellow',
};

export const sampleWithNewData: NewFormSuratJalan = {
  active: true,
  createdDate: dayjs('2023-04-17T14:52'),
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
