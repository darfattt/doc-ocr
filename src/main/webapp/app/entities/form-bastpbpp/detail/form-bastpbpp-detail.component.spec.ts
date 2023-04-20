import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FormBASTPBPPDetailComponent } from './form-bastpbpp-detail.component';

describe('FormBASTPBPP Management Detail Component', () => {
  let comp: FormBASTPBPPDetailComponent;
  let fixture: ComponentFixture<FormBASTPBPPDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormBASTPBPPDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ formBASTPBPP: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(FormBASTPBPPDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(FormBASTPBPPDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load formBASTPBPP on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.formBASTPBPP).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
