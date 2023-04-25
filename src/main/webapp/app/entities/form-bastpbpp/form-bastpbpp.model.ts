import dayjs from 'dayjs/esm';

export interface IFormBASTPBPP {
  id: string;
  status?: string | null;
  active?: boolean | null;
  remarks?: string | null;
  contents?: string | null;
  documentTitle?: string | null;
  kelurahanDesa?: string | null;
  kecamatan?: string | null;
  kabupatenKota?: string | null;
  provinsi?: string | null;
  pbpTidakDitemukan1?: string | null;
  sebabPenggantian1?: string | null;
  pbpPengganti1?: string | null;
  alamatPbpPengganti1?: string | null;
  pbpTidakDitemukan2?: string | null;
  sebabPenggantian2?: string | null;
  pbpPengganti2?: string | null;
  alamatPbpPengganti2?: string | null;
  pbpTidakDitemukan3?: string | null;
  sebabPenggantian3?: string | null;
  pbpPengganti3?: string | null;
  alamatPbpPengganti3?: string | null;
  pbpTidakDitemukan4?: string | null;
  sebabPenggantian4?: string | null;
  pbpPengganti4?: string | null;
  alamatPbpPengganti4?: string | null;
  pbpTidakDitemukan5?: string | null;
  sebabPenggantian5?: string | null;
  pbpPengganti5?: string | null;
  alamatPbpPengganti5?: string | null;
  createdDate?: dayjs.Dayjs | null;
  createdBy?: string | null;
  lastModifiedDate?: dayjs.Dayjs | null;
  lastModifiedBy?: string | null;
}

export type NewFormBASTPBPP = Omit<IFormBASTPBPP, 'id'> & { id: null };
