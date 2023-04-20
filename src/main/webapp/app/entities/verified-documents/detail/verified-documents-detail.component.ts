import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IVerifiedDocuments } from '../verified-documents.model';

@Component({
  selector: 'jhi-verified-documents-detail',
  templateUrl: './verified-documents-detail.component.html',
})
export class VerifiedDocumentsDetailComponent implements OnInit {
  verifiedDocuments: IVerifiedDocuments | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ verifiedDocuments }) => {
      this.verifiedDocuments = verifiedDocuments;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
