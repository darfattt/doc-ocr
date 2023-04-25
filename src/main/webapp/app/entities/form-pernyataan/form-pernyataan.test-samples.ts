import dayjs from 'dayjs/esm';

import { IFormPernyataan, NewFormPernyataan } from './form-pernyataan.model';

export const sampleWithRequiredData: IFormPernyataan = {
  id: '9d0e1fcc-c48c-4a12-b924-22a68cf893fb',
  active: true,
  createdDate: dayjs('2023-04-17T20:39'),
};

export const sampleWithPartialData: IFormPernyataan = {
  id: '0bcd3163-c0a5-457f-9f2b-0f392aa90691',
  active: true,
  remarks: 'Tuna Berkshire visualize',
  officerPhoneNumber: 'HDD Metical navigating',
  kelurahanDesa: 'value-added National',
  kabupatenKota: 'Cotton',
  provinsi: 'approach',
  alamatPbpTidakDitemukan1: 'reboot monitor',
  pbpTidakDitemukan2: 'Table Home',
  alamatPbpTidakDitemukan2: 'Product',
  alamatPbpPengganti2: 'projection enterprise withdrawal',
  pbpTidakDitemukan3: 'compress quantifying',
  alamatPbpPengganti4: 'Technician',
  alamatPbpPengganti5: 'Roads payment',
  createdDate: dayjs('2023-04-17T13:22'),
  lastModifiedBy: 'primary JSON',
};

export const sampleWithFullData: IFormPernyataan = {
  id: '05337951-809e-44ec-beac-dc7310f73ae9',
  status: 'To',
  active: true,
  remarks: 'Division programming Central',
  contents: 'index cohesive Assistant',
  documentTitle: 'niches Senior',
  officerName: 'Integration',
  officerPhoneNumber: 'Plastic Supervisor user-facing',
  officerPosition: 'Dinar Facilitator',
  officerDepartment: 'copying Lock',
  kelurahanDesa: 'parse grey empowering',
  kecamatan: 'protocol',
  kabupatenKota: 'overriding',
  provinsi: 'parsing',
  pbpTidakDitemukan1: 'card Dollar',
  alamatPbpTidakDitemukan1: 'Concrete',
  pbpPengganti1: 'red ADP Borders',
  alamatPbpPengganti1: 'index',
  pbpTidakDitemukan2: 'Home',
  alamatPbpTidakDitemukan2: 'Auto',
  pbpPengganti2: 'generating',
  alamatPbpPengganti2: 'Krone',
  pbpTidakDitemukan3: 'Fish',
  alamatPbpTidakDitemukan3: 'quantify',
  pbpPengganti3: 'Island Communications quantify',
  alamatPbpPengganti3: 'PNG Chips white',
  pbpTidakDitemukan4: 'Operations fuchsia invoice',
  alamatPbpTidakDitemukan4: 'morph XML',
  pbpPengganti4: 'generation circuit',
  alamatPbpPengganti4: 'Table Ergonomic',
  pbpTidakDitemukan5: 'parsing',
  alamatPbpTidakDitemukan5: 'compress',
  pbpPengganti5: 'action-items',
  alamatPbpPengganti5: 'connecting Metal',
  createdDate: dayjs('2023-04-18T05:14'),
  createdBy: 'Assistant azure',
  lastModifiedDate: dayjs('2023-04-18T03:37'),
  lastModifiedBy: 'reboot Buckinghamshire transmitting',
};

export const sampleWithNewData: NewFormPernyataan = {
  active: false,
  createdDate: dayjs('2023-04-17T08:35'),
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
