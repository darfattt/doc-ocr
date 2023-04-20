jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { FormPengeluaranBarangService } from '../service/form-pengeluaran-barang.service';

import { FormPengeluaranBarangDeleteDialogComponent } from './form-pengeluaran-barang-delete-dialog.component';

describe('FormPengeluaranBarang Management Delete Component', () => {
  let comp: FormPengeluaranBarangDeleteDialogComponent;
  let fixture: ComponentFixture<FormPengeluaranBarangDeleteDialogComponent>;
  let service: FormPengeluaranBarangService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [FormPengeluaranBarangDeleteDialogComponent],
      providers: [NgbActiveModal],
    })
      .overrideTemplate(FormPengeluaranBarangDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(FormPengeluaranBarangDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(FormPengeluaranBarangService);
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
