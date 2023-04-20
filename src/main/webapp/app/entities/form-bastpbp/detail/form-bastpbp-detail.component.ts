import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFormBASTPBP } from '../form-bastpbp.model';

@Component({
  selector: 'jhi-form-bastpbp-detail',
  templateUrl: './form-bastpbp-detail.component.html',
})
export class FormBASTPBPDetailComponent implements OnInit {
  formBASTPBP: IFormBASTPBP | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ formBASTPBP }) => {
      this.formBASTPBP = formBASTPBP;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
