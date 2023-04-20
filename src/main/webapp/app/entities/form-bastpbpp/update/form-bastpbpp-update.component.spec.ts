import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { FormBASTPBPPFormService } from './form-bastpbpp-form.service';
import { FormBASTPBPPService } from '../service/form-bastpbpp.service';
import { IFormBASTPBPP } from '../form-bastpbpp.model';

import { FormBASTPBPPUpdateComponent } from './form-bastpbpp-update.component';

describe('FormBASTPBPP Management Update Component', () => {
  let comp: FormBASTPBPPUpdateComponent;
  let fixture: ComponentFixture<FormBASTPBPPUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let formBASTPBPPFormService: FormBASTPBPPFormService;
  let formBASTPBPPService: FormBASTPBPPService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [FormBASTPBPPUpdateComponent],
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
      .overrideTemplate(FormBASTPBPPUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(FormBASTPBPPUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    formBASTPBPPFormService = TestBed.inject(FormBASTPBPPFormService);
    formBASTPBPPService = TestBed.inject(FormBASTPBPPService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const formBASTPBPP: IFormBASTPBPP = { id: 'CBA' };

      activatedRoute.data = of({ formBASTPBPP });
      comp.ngOnInit();

      expect(comp.formBASTPBPP).toEqual(formBASTPBPP);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFormBASTPBPP>>();
      const formBASTPBPP = { id: 'ABC' };
      jest.spyOn(formBASTPBPPFormService, 'getFormBASTPBPP').mockReturnValue(formBASTPBPP);
      jest.spyOn(formBASTPBPPService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ formBASTPBPP });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: formBASTPBPP }));
      saveSubject.complete();

      // THEN
      expect(formBASTPBPPFormService.getFormBASTPBPP).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(formBASTPBPPService.update).toHaveBeenCalledWith(expect.objectContaining(formBASTPBPP));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFormBASTPBPP>>();
      const formBASTPBPP = { id: 'ABC' };
      jest.spyOn(formBASTPBPPFormService, 'getFormBASTPBPP').mockReturnValue({ id: null });
      jest.spyOn(formBASTPBPPService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ formBASTPBPP: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: formBASTPBPP }));
      saveSubject.complete();

      // THEN
      expect(formBASTPBPPFormService.getFormBASTPBPP).toHaveBeenCalled();
      expect(formBASTPBPPService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFormBASTPBPP>>();
      const formBASTPBPP = { id: 'ABC' };
      jest.spyOn(formBASTPBPPService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ formBASTPBPP });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(formBASTPBPPService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
