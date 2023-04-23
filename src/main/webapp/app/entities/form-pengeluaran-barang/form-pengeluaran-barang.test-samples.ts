import dayjs from 'dayjs/esm';

import { IFormPengeluaranBarang, NewFormPengeluaranBarang } from './form-pengeluaran-barang.model';

export const sampleWithRequiredData: IFormPengeluaranBarang = {
  id: '93996f82-8b98-4b16-977b-f5404969c153',
  active: true,
  createdDate: dayjs('2023-04-17T13:54'),
};

export const sampleWithPartialData: IFormPengeluaranBarang = {
  id: 'a6ca6e11-8781-4e6d-a1cc-0a5319c67e70',
  active: true,
  remarks: 'Rubber',
  contents: 'tan',
  documentTitle: 'out-of-the-box Ergonomic',
  recipientAddress: 'bandwidth uniform',
  reference: 'Chips',
  status: 'Frozen invoice bypass',
  lotNo: 'Books Frozen parse',
  quantity: 968,
  amount: 76272,
  armadaName: 'Intelligent',
  armadaNumber: 'online upward-trending',
  createdDate: dayjs('2023-04-17T20:09'),
  createdBy: 'Designer defect',
};

export const sampleWithFullData: IFormPengeluaranBarang = {
  id: '90295b87-db4d-4e26-82f0-f0eca5488c63',
  status: 'We',
  active: true,
  remarks: 'RSS needs-based',
  contents: 'Shirt payment e-services',
  branch: 'Pre-emptive clear-thinking',
  documentTitle: 'Estate Vermont Frozen',
  documentNumber: 'mobile Savings',
  recipientAddress: 'Fresh',
  npwp: 'New web-readiness',
  warehouseSource: 'Facilitator green innovate',
  documentSource: 'alarm Internal Indiana',
  reference: 'Soap SQL',
  status: 'innovate',
  date: 'e-commerce Architect',
  productDescription: 'HDD Metal Response',
  sourceLocation: 'Intranet Jordan Minnesota',
  lotNo: 'bluetooth',
  quantity: 85543,
  amount: 31455,
  sourceDestination: 'Licensed Home Argentina',
  armadaName: 'generating',
  armadaNumber: 'Creek',
  createdDate: dayjs('2023-04-18T07:50'),
  createdBy: 'green Orchestrator',
  lastModifiedDate: dayjs('2023-04-17T13:39'),
  lastModifiedBy: 'Customer',
};

export const sampleWithNewData: NewFormPengeluaranBarang = {
  active: true,
  createdDate: dayjs('2023-04-17T09:53'),
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
