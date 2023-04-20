import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFormPengeluaranBarang } from '../form-pengeluaran-barang.model';

@Component({
  selector: 'jhi-form-pengeluaran-barang-detail',
  templateUrl: './form-pengeluaran-barang-detail.component.html',
})
export class FormPengeluaranBarangDetailComponent implements OnInit {
  formPengeluaranBarang: IFormPengeluaranBarang | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ formPengeluaranBarang }) => {
      this.formPengeluaranBarang = formPengeluaranBarang;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
