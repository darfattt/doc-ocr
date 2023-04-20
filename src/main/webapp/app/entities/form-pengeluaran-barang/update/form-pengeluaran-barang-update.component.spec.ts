import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { FormPengeluaranBarangFormService } from './form-pengeluaran-barang-form.service';
import { FormPengeluaranBarangService } from '../service/form-pengeluaran-barang.service';
import { IFormPengeluaranBarang } from '../form-pengeluaran-barang.model';

import { FormPengeluaranBarangUpdateComponent } from './form-pengeluaran-barang-update.component';

describe('FormPengeluaranBarang Management Update Component', () => {
  let comp: FormPengeluaranBarangUpdateComponent;
  let fixture: ComponentFixture<FormPengeluaranBarangUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let formPengeluaranBarangFormService: FormPengeluaranBarangFormService;
  let formPengeluaranBarangService: FormPengeluaranBarangService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [FormPengeluaranBarangUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(FormPengeluaranBarangUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(FormPengeluaranBarangUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    formPengeluaranBarangFormService = TestBed.inject(FormPengeluaranBarangFormService);
    formPengeluaranBarangService = TestBed.inject(FormPengeluaranBarangService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const formPengeluaranBarang: IFormPengeluaranBarang = { id: 'CBA' };

      activatedRoute.data = of({ formPengeluaranBarang });
      comp.ngOnInit();

      expect(comp.formPengeluaranBarang).toEqual(formPengeluaranBarang);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFormPengeluaranBarang>>();
      const formPengeluaranBarang = { id: 'ABC' };
      jest.spyOn(formPengeluaranBarangFormService, 'getFormPengeluaranBarang').mockReturnValue(formPengeluaranBarang);
      jest.spyOn(formPengeluaranBarangService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ formPengeluaranBarang });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: formPengeluaranBarang }));
      saveSubject.complete();

      // THEN
      expect(formPengeluaranBarangFormService.getFormPengeluaranBarang).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(formPengeluaranBarangService.update).toHaveBeenCalledWith(expect.objectContaining(formPengeluaranBarang));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFormPengeluaranBarang>>();
      const formPengeluaranBarang = { id: 'ABC' };
      jest.spyOn(formPengeluaranBarangFormService, 'getFormPengeluaranBarang').mockReturnValue({ id: null });
      jest.spyOn(formPengeluaranBarangService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ formPengeluaranBarang: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: formPengeluaranBarang }));
      saveSubject.complete();

      // THEN
      expect(formPengeluaranBarangFormService.getFormPengeluaranBarang).toHaveBeenCalled();
      expect(formPengeluaranBarangService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFormPengeluaranBarang>>();
      const formPengeluaranBarang = { id: 'ABC' };
      jest.spyOn(formPengeluaranBarangService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ formPengeluaranBarang });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(formPengeluaranBarangService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
