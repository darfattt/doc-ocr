export class AttachmentRequest {
  constructor(
    public attachmentGroupId?: string,
    public attachmentGroupParentId?: string,
    public name?: string,
    public description?: string,
    public basePath?: string,
    public className?: string,
    public attachments?: Attachment[],
    public docType?: string,
    public docNumber?: string
  ) {
    this.attachments = [];
  }
}

export class Attachment {
  constructor(
    public name?: string,
    public type?: string,
    public size?: number,
    public referenceNumber?: string,
    public description?: string,
    public blobFile?: any,
    public removeFlag?: boolean
  ) {}
}
