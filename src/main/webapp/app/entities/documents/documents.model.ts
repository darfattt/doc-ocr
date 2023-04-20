import dayjs from 'dayjs/esm';

export interface IDocuments {
  id: string;
  type?: string | null;
  name?: string | null;
  status?: string | null;
  createdDate?: dayjs.Dayjs | null;
  createdBy?: string | null;
  lastModifiedDate?: dayjs.Dayjs | null;
  lastModifiedBy?: string | null;
}

export type NewDocuments = Omit<IDocuments, 'id'> & { id: null };
