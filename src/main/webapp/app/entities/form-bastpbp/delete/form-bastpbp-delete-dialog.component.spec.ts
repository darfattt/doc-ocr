jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { FormBASTPBPService } from '../service/form-bastpbp.service';

import { FormBASTPBPDeleteDialogComponent } from './form-bastpbp-delete-dialog.component';

describe('FormBASTPBP Management Delete Component', () => {
  let comp: FormBASTPBPDeleteDialogComponent;
  let fixture: ComponentFixture<FormBASTPBPDeleteDialogComponent>;
  let service: FormBASTPBPService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [FormBASTPBPDeleteDialogComponent],
      providers: [NgbActiveModal],
    })
      .overrideTemplate(FormBASTPBPDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(FormBASTPBPDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(FormBASTPBPService);
    mockActiveModal = TestBed.inject(NgbActiveModal);
  });

  describe('confirmDelete', () => {
    it('Should call delete service on confirmDelete', inject(
      [],
      fakeAsync(() => {
        // GIVEN
        jest.spyOn(service, 'delete').mockReturnValue(of(new HttpResponse({ body: {} })));

        // WHEN
        comp.confirmDelete('ABC');
        tick();

        // THEN
        expect(service.delete).toHaveBeenCalledWith('ABC');
        expect(mockActiveModal.close).toHaveBeenCalledWith('deleted');
      })
    ));

    it('Should not call delete service on clear', () => {
      // GIVEN
      jest.spyOn(service, 'delete');

      // WHEN
      comp.cancel();

      // THEN
      expect(service.delete).not.toHaveBeenCalled();
      expect(mockActiveModal.close).not.toHaveBeenCalled();
      expect(mockActiveModal.dismiss).toHaveBeenCalled();
    });
  });
});
