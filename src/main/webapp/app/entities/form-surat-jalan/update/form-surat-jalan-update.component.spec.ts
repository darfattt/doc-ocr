import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { FormSuratJalanFormService } from './form-surat-jalan-form.service';
import { FormSuratJalanService } from '../service/form-surat-jalan.service';
import { IFormSuratJalan } from '../form-surat-jalan.model';

import { FormSuratJalanUpdateComponent } from './form-surat-jalan-update.component';

describe('FormSuratJalan Management Update Component', () => {
  let comp: FormSuratJalanUpdateComponent;
  let fixture: ComponentFixture<FormSuratJalanUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let formSuratJalanFormService: FormSuratJalanFormService;
  let formSuratJalanService: FormSuratJalanService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [FormSuratJalanUpdateComponent],
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
      .overrideTemplate(FormSuratJalanUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(FormSuratJalanUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    formSuratJalanFormService = TestBed.inject(FormSuratJalanFormService);
    formSuratJalanService = TestBed.inject(FormSuratJalanService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const formSuratJalan: IFormSuratJalan = { id: 'CBA' };

      activatedRoute.data = of({ formSuratJalan });
      comp.ngOnInit();

      expect(comp.formSuratJalan).toEqual(formSuratJalan);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFormSuratJalan>>();
      const formSuratJalan = { id: 'ABC' };
      jest.spyOn(formSuratJalanFormService, 'getFormSuratJalan').mockReturnValue(formSuratJalan);
      jest.spyOn(formSuratJalanService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ formSuratJalan });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: formSuratJalan }));
      saveSubject.complete();

      // THEN
      expect(formSuratJalanFormService.getFormSuratJalan).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(formSuratJalanService.update).toHaveBeenCalledWith(expect.objectContaining(formSuratJalan));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFormSuratJalan>>();
      const formSuratJalan = { id: 'ABC' };
      jest.spyOn(formSuratJalanFormService, 'getFormSuratJalan').mockReturnValue({ id: null });
      jest.spyOn(formSuratJalanService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ formSuratJalan: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: formSuratJalan }));
      saveSubject.complete();

      // THEN
      expect(formSuratJalanFormService.getFormSuratJalan).toHaveBeenCalled();
      expect(formSuratJalanService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFormSuratJalan>>();
      const formSuratJalan = { id: 'ABC' };
      jest.spyOn(formSuratJalanService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ formSuratJalan });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(formSuratJalanService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
