import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { DocumentsFormService } from './documents-form.service';
import { DocumentsService } from '../service/documents.service';
import { IDocuments } from '../documents.model';

import { DocumentsUpdateComponent } from './documents-update.component';

describe('Documents Management Update Component', () => {
  let comp: DocumentsUpdateComponent;
  let fixture: ComponentFixture<DocumentsUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let documentsFormService: DocumentsFormService;
  let documentsService: DocumentsService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [DocumentsUpdateComponent],
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
      .overrideTemplate(DocumentsUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DocumentsUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    documentsFormService = TestBed.inject(DocumentsFormService);
    documentsService = TestBed.inject(DocumentsService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const documents: IDocuments = { id: 'CBA' };

      activatedRoute.data = of({ documents });
      comp.ngOnInit();

      expect(comp.documents).toEqual(documents);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDocuments>>();
      const documents = { id: 'ABC' };
      jest.spyOn(documentsFormService, 'getDocuments').mockReturnValue(documents);
      jest.spyOn(documentsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ documents });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: documents }));
      saveSubject.complete();

      // THEN
      expect(documentsFormService.getDocuments).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(documentsService.update).toHaveBeenCalledWith(expect.objectContaining(documents));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDocuments>>();
      const documents = { id: 'ABC' };
      jest.spyOn(documentsFormService, 'getDocuments').mockReturnValue({ id: null });
      jest.spyOn(documentsService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ documents: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: documents }));
      saveSubject.complete();

      // THEN
      expect(documentsFormService.getDocuments).toHaveBeenCalled();
      expect(documentsService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDocuments>>();
      const documents = { id: 'ABC' };
      jest.spyOn(documentsService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ documents });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(documentsService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
