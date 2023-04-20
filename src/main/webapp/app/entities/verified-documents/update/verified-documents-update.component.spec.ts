import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { VerifiedDocumentsFormService } from './verified-documents-form.service';
import { VerifiedDocumentsService } from '../service/verified-documents.service';
import { IVerifiedDocuments } from '../verified-documents.model';

import { VerifiedDocumentsUpdateComponent } from './verified-documents-update.component';

describe('VerifiedDocuments Management Update Component', () => {
  let comp: VerifiedDocumentsUpdateComponent;
  let fixture: ComponentFixture<VerifiedDocumentsUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let verifiedDocumentsFormService: VerifiedDocumentsFormService;
  let verifiedDocumentsService: VerifiedDocumentsService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [VerifiedDocumentsUpdateComponent],
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
      .overrideTemplate(VerifiedDocumentsUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(VerifiedDocumentsUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    verifiedDocumentsFormService = TestBed.inject(VerifiedDocumentsFormService);
    verifiedDocumentsService = TestBed.inject(VerifiedDocumentsService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const verifiedDocuments: IVerifiedDocuments = { id: 'CBA' };

      activatedRoute.data = of({ verifiedDocuments });
      comp.ngOnInit();

      expect(comp.verifiedDocuments).toEqual(verifiedDocuments);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IVerifiedDocuments>>();
      const verifiedDocuments = { id: 'ABC' };
      jest.spyOn(verifiedDocumentsFormService, 'getVerifiedDocuments').mockReturnValue(verifiedDocuments);
      jest.spyOn(verifiedDocumentsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ verifiedDocuments });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: verifiedDocuments }));
      saveSubject.complete();

      // THEN
      expect(verifiedDocumentsFormService.getVerifiedDocuments).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(verifiedDocumentsService.update).toHaveBeenCalledWith(expect.objectContaining(verifiedDocuments));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IVerifiedDocuments>>();
      const verifiedDocuments = { id: 'ABC' };
      jest.spyOn(verifiedDocumentsFormService, 'getVerifiedDocuments').mockReturnValue({ id: null });
      jest.spyOn(verifiedDocumentsService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ verifiedDocuments: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: verifiedDocuments }));
      saveSubject.complete();

      // THEN
      expect(verifiedDocumentsFormService.getVerifiedDocuments).toHaveBeenCalled();
      expect(verifiedDocumentsService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IVerifiedDocuments>>();
      const verifiedDocuments = { id: 'ABC' };
      jest.spyOn(verifiedDocumentsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ verifiedDocuments });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(verifiedDocumentsService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
