import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FormSuratJalanDetailComponent } from './form-surat-jalan-detail.component';

describe('FormSuratJalan Management Detail Component', () => {
  let comp: FormSuratJalanDetailComponent;
  let fixture: ComponentFixture<FormSuratJalanDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormSuratJalanDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ formSuratJalan: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(FormSuratJalanDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(FormSuratJalanDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load formSuratJalan on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.formSuratJalan).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
