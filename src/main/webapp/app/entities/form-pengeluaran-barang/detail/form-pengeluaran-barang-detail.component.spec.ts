import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FormPengeluaranBarangDetailComponent } from './form-pengeluaran-barang-detail.component';

describe('FormPengeluaranBarang Management Detail Component', () => {
  let comp: FormPengeluaranBarangDetailComponent;
  let fixture: ComponentFixture<FormPengeluaranBarangDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FormPengeluaranBarangDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ formPengeluaranBarang: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(FormPengeluaranBarangDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(FormPengeluaranBarangDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load formPengeluaranBarang on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.formPengeluaranBarang).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
