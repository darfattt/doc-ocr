import dayjs from 'dayjs/esm';

export interface IFormSuratJalan {
  id: string;
  status?: string | null;
  active?: boolean | null;
  remarks?: string | null;
  contents?: string | null;
  branch?: string | null;
  documentTitle?: string | null;
  documentNumber?: string | null;
  recipientAddress?: string | null;
  npwp?: string | null;
  warehouseSource?: string | null;
  documentSource?: string | null;
  reference?: string | null;
  date?: string | null;
  productDescription?: string | null;
  quantity?: number | null;
  amount?: number | null;
  armadaNumber?: string | null;
  containerNumber?: string | null;
  createdDate?: dayjs.Dayjs | null;
  createdBy?: string | null;
  lastModifiedDate?: dayjs.Dayjs | null;
  lastModifiedBy?: string | null;
}

export type NewFormSuratJalan = Omit<IFormSuratJalan, 'id'> & { id: null };
