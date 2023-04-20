import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { FormBASTPBPFormService } from './form-bastpbp-form.service';
import { FormBASTPBPService } from '../service/form-bastpbp.service';
import { IFormBASTPBP } from '../form-bastpbp.model';

import { FormBASTPBPUpdateComponent } from './form-bastpbp-update.component';

describe('FormBASTPBP Management Update Component', () => {
  let comp: FormBASTPBPUpdateComponent;
  let fixture: ComponentFixture<FormBASTPBPUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let formBASTPBPFormService: FormBASTPBPFormService;
  let formBASTPBPService: FormBASTPBPService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [FormBASTPBPUpdateComponent],
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
      .overrideTemplate(FormBASTPBPUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(FormBASTPBPUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    formBASTPBPFormService = TestBed.inject(FormBASTPBPFormService);
    formBASTPBPService = TestBed.inject(FormBASTPBPService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const formBASTPBP: IFormBASTPBP = { id: 'CBA' };

      activatedRoute.data = of({ formBASTPBP });
      comp.ngOnInit();

      expect(comp.formBASTPBP).toEqual(formBASTPBP);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFormBASTPBP>>();
      const formBASTPBP = { id: 'ABC' };
      jest.spyOn(formBASTPBPFormService, 'getFormBASTPBP').mockReturnValue(formBASTPBP);
      jest.spyOn(formBASTPBPService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ formBASTPBP });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: formBASTPBP }));
      saveSubject.complete();

      // THEN
      expect(formBASTPBPFormService.getFormBASTPBP).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(formBASTPBPService.update).toHaveBeenCalledWith(expect.objectContaining(formBASTPBP));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFormBASTPBP>>();
      const formBASTPBP = { id: 'ABC' };
      jest.spyOn(formBASTPBPFormService, 'getFormBASTPBP').mockReturnValue({ id: null });
      jest.spyOn(formBASTPBPService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ formBASTPBP: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: formBASTPBP }));
      saveSubject.complete();

      // THEN
      expect(formBASTPBPFormService.getFormBASTPBP).toHaveBeenCalled();
      expect(formBASTPBPService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFormBASTPBP>>();
      const formBASTPBP = { id: 'ABC' };
      jest.spyOn(formBASTPBPService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ formBASTPBP });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(formBASTPBPService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
