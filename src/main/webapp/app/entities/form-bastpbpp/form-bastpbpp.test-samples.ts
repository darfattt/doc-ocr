import dayjs from 'dayjs/esm';

import { IFormBASTPBPP, NewFormBASTPBPP } from './form-bastpbpp.model';

export const sampleWithRequiredData: IFormBASTPBPP = {
  id: '95e86297-76f3-4453-a655-6b8e86d04e4e',
  active: true,
  createdDate: dayjs('2023-04-17T15:24'),
};

export const sampleWithPartialData: IFormBASTPBPP = {
  id: 'd11b86e9-5918-4b45-b5e0-cdb19672fd0c',
  status: 'pr',
  active: true,
  contents: 'non-volatile',
  documentTitle: 'invoice',
  kabupatenKota: 'open-source',
  provinsi: 'transmitting Dakota digital',
  pbpTidakDitemukan2: 'up',
  pbpPengganti2: 'Sharable Loan',
  alamatPbpPengganti2: 'back',
  pbpTidakDitemukan3: 'Yuan Secured Pizza',
  pbpPengganti3: 'Creative Ports harness',
  alamatPbpPengganti3: 'Agent RSS withdrawal',
  pbpPengganti5: 'bluetooth',
  alamatPbpPengganti5: 'Quality bypass Concrete',
  createdDate: dayjs('2023-04-17T14:54'),
  lastModifiedDate: dayjs('2023-04-18T00:21'),
  lastModifiedBy: 'Focused',
};

export const sampleWithFullData: IFormBASTPBPP = {
  id: 'b77f9c4b-c8b6-4047-b1a9-c37fe8ae6d89',
  status: 'de',
  active: false,
  remarks: 'Loan back-end',
  contents: 'red Savings',
  documentTitle: 'deposit',
  kelurahanDesa: 'bleeding-edge',
  kecamatan: 'Usability Iraq parse',
  kabupatenKota: 'Avon',
  provinsi: 'face Avon transparent',
  pbpTidakDitemukan1: 'generating haptic Accounts',
  sebabPenggantian1: 'Regional Berkshire',
  pbpPengganti1: 'lavender Berkshire invoice',
  alamatPbpPengganti1: 'Armenia Games Nebraska',
  pbpTidakDitemukan2: 'Senior synthesizing Haiti',
  sebabPenggantian2: 'middleware Granite',
  pbpPengganti2: 'Incredible',
  alamatPbpPengganti2: 'Fall',
  pbpTidakDitemukan3: 'Tajikistan',
  sebabPenggantian3: 'Iraq e-enable Money',
  pbpPengganti3: 'Junctions Dollar',
  alamatPbpPengganti3: 'copying dedicated',
  pbpTidakDitemukan4: 'index',
  sebabPenggantian4: 'programming Steel Antillian',
  pbpPengganti4: 'Avon',
  alamatPbpPengganti4: 'invoice Human',
  pbpTidakDitemukan5: 'Utah Rand',
  sebabPenggantian5: 'back Cambridgeshire repurpose',
  pbpPengganti5: 'RSS',
  alamatPbpPengganti5: 'redundant',
  createdDate: dayjs('2023-04-18T01:58'),
  createdBy: 'Right-sized',
  lastModifiedDate: dayjs('2023-04-17T15:52'),
  lastModifiedBy: 'Mobility',
};

export const sampleWithNewData: NewFormBASTPBPP = {
  active: true,
  createdDate: dayjs('2023-04-17T09:35'),
  id: null,
};

Object.freeze(sampleWithNewData);
Object.freeze(sampleWithRequiredData);
Object.freeze(sampleWithPartialData);
Object.freeze(sampleWithFullData);
