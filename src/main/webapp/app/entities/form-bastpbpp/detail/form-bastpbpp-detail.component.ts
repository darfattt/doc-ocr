import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFormBASTPBPP } from '../form-bastpbpp.model';

@Component({
  selector: 'jhi-form-bastpbpp-detail',
  templateUrl: './form-bastpbpp-detail.component.html',
})
export class FormBASTPBPPDetailComponent implements OnInit {
  formBASTPBPP: IFormBASTPBPP | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ formBASTPBPP }) => {
      this.formBASTPBPP = formBASTPBPP;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
