import dayjs from 'dayjs/esm';

export interface IFormPernyataan {
  id: string;
  status?: string | null;
  active?: boolean | null;
  remarks?: string | null;
  createdDate?: dayjs.Dayjs | null;
  createdBy?: string | null;
  lastModifiedDate?: dayjs.Dayjs | null;
  lastModifiedBy?: string | null;
}

export type NewFormPernyataan = Omit<IFormPernyataan, 'id'> & { id: null };
