import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { FormPernyataanFormService } from './form-pernyataan-form.service';
import { FormPernyataanService } from '../service/form-pernyataan.service';
import { IFormPernyataan } from '../form-pernyataan.model';

import { FormPernyataanUpdateComponent } from './form-pernyataan-update.component';

describe('FormPernyataan Management Update Component', () => {
  let comp: FormPernyataanUpdateComponent;
  let fixture: ComponentFixture<FormPernyataanUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let formPernyataanFormService: FormPernyataanFormService;
  let formPernyataanService: FormPernyataanService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [FormPernyataanUpdateComponent],
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
      .overrideTemplate(FormPernyataanUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(FormPernyataanUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    formPernyataanFormService = TestBed.inject(FormPernyataanFormService);
    formPernyataanService = TestBed.inject(FormPernyataanService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const formPernyataan: IFormPernyataan = { id: 'CBA' };

      activatedRoute.data = of({ formPernyataan });
      comp.ngOnInit();

      expect(comp.formPernyataan).toEqual(formPernyataan);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFormPernyataan>>();
      const formPernyataan = { id: 'ABC' };
      jest.spyOn(formPernyataanFormService, 'getFormPernyataan').mockReturnValue(formPernyataan);
      jest.spyOn(formPernyataanService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ formPernyataan });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: formPernyataan }));
      saveSubject.complete();

      // THEN
      expect(formPernyataanFormService.getFormPernyataan).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(formPernyataanService.update).toHaveBeenCalledWith(expect.objectContaining(formPernyataan));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFormPernyataan>>();
      const formPernyataan = { id: 'ABC' };
      jest.spyOn(formPernyataanFormService, 'getFormPernyataan').mockReturnValue({ id: null });
      jest.spyOn(formPernyataanService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ formPernyataan: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: formPernyataan }));
      saveSubject.complete();

      // THEN
      expect(formPernyataanFormService.getFormPernyataan).toHaveBeenCalled();
      expect(formPernyataanService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFormPernyataan>>();
      const formPernyataan = { id: 'ABC' };
      jest.spyOn(formPernyataanService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ formPernyataan });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(formPernyataanService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
