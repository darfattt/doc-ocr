import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FormPernyataanDetailComponent } from './form-pernyataan-detail.component';

describe('FormPernyataan Management Detail Component', () => {
  let comp: FormPernyataanDetailComponent;
  let fixture: ComponentFixture<FormPernyataanDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormPernyataanDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ formPernyataan: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(FormPernyataanDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(FormPernyataanDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load formPernyataan on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.formPernyataan).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
