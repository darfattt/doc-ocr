import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFormPernyataan } from '../form-pernyataan.model';

@Component({
  selector: 'jhi-form-pernyataan-detail',
  templateUrl: './form-pernyataan-detail.component.html',
})
export class FormPernyataanDetailComponent implements OnInit {
  formPernyataan: IFormPernyataan | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ formPernyataan }) => {
      this.formPernyataan = formPernyataan;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
