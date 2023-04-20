import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FormBASTPBPDetailComponent } from './form-bastpbp-detail.component';

describe('FormBASTPBP Management Detail Component', () => {
  let comp: FormBASTPBPDetailComponent;
  let fixture: ComponentFixture<FormBASTPBPDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormBASTPBPDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ formBASTPBP: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(FormBASTPBPDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(FormBASTPBPDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load formBASTPBP on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.formBASTPBP).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
