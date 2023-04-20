import dayjs from 'dayjs/esm';

export interface IFormSuratJalan {
  id: string;
  status?: string | null;
  active?: boolean | null;
  remarks?: string | null;
  createdDate?: dayjs.Dayjs | null;
  createdBy?: string | null;
  lastModifiedDate?: dayjs.Dayjs | null;
  lastModifiedBy?: string | null;
}

export type NewFormSuratJalan = Omit<IFormSuratJalan, 'id'> & { id: null };
