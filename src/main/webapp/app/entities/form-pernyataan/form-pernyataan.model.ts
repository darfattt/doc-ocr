import dayjs from 'dayjs/esm';

export interface IFormPernyataan {
  id: string;
  status?: string | null;
  active?: boolean | null;
  remarks?: string | null;
  contents?: string | null;
  documentTitle?: string | null;
  officerName?: string | null;
  officerPhoneNumber?: string | null;
  officerPosition?: string | null;
  officerDepartment?: string | null;
  kelurahanDesa?: string | null;
  kecamatan?: string | null;
  kabupatenKota?: string | null;
  provinsi?: string | null;
  pbpTidakDitemukan1?: string | null;
  alamatPbpTidakDitemukan1?: string | null;
  pbpPengganti1?: string | null;
  alamatPbpPengganti1?: string | null;
  pbpTidakDitemukan2?: string | null;
  alamatPbpTidakDitemukan2?: string | null;
  pbpPengganti2?: string | null;
  alamatPbpPengganti2?: string | null;
  pbpTidakDitemukan3?: string | null;
  alamatPbpTidakDitemukan3?: string | null;
  pbpPengganti3?: string | null;
  alamatPbpPengganti3?: string | null;
  pbpTidakDitemukan4?: string | null;
  alamatPbpTidakDitemukan4?: string | null;
  pbpPengganti4?: string | null;
  alamatPbpPengganti4?: string | null;
  pbpTidakDitemukan5?: string | null;
  alamatPbpTidakDitemukan5?: string | null;
  pbpPengganti5?: string | null;
  alamatPbpPengganti5?: string | null;
  createdDate?: dayjs.Dayjs | null;
  createdBy?: string | null;
  lastModifiedDate?: dayjs.Dayjs | null;
  lastModifiedBy?: string | null;
}

export type NewFormPernyataan = Omit<IFormPernyataan, 'id'> & { id: null };
