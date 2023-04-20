import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFormSuratJalan } from '../form-surat-jalan.model';

@Component({
  selector: 'jhi-form-surat-jalan-detail',
  templateUrl: './form-surat-jalan-detail.component.html',
})
export class FormSuratJalanDetailComponent implements OnInit {
  formSuratJalan: IFormSuratJalan | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ formSuratJalan }) => {
      this.formSuratJalan = formSuratJalan;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
