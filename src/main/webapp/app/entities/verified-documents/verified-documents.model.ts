import dayjs from 'dayjs/esm';

export interface IVerifiedDocuments {
  id: string;
  type?: string | null;
  name?: string | null;
  status?: string | null;
  contentId?: string | null;
  createdDate?: dayjs.Dayjs | null;
  createdBy?: string | null;
  lastModifiedDate?: dayjs.Dayjs | null;
  lastModifiedBy?: string | null;
}

export type NewVerifiedDocuments = Omit<IVerifiedDocuments, 'id'> & { id: null };
