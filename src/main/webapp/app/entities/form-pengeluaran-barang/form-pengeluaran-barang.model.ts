import dayjs from 'dayjs/esm';

export interface IFormPengeluaranBarang {
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
  orderStatus?: string | null;
  date?: string | null;
  productDescription?: string | null;
  sourceLocation?: string | null;
  lotNo?: string | null;
  quantity?: number | null;
  amount?: number | null;
  sourceDestination?: string | null;
  armadaName?: string | null;
  armadaNumber?: string | null;
  createdDate?: dayjs.Dayjs | null;
  createdBy?: string | null;
  lastModifiedDate?: dayjs.Dayjs | null;
  lastModifiedBy?: string | null;
}

export type NewFormPengeluaranBarang = Omit<IFormPengeluaranBarang, 'id'> & { id: null };
