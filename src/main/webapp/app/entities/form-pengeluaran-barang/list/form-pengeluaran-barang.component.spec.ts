import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { FormPengeluaranBarangService } from '../service/form-pengeluaran-barang.service';

import { FormPengeluaranBarangComponent } from './form-pengeluaran-barang.component';

describe('FormPengeluaranBarang Management Component', () => {
  let comp: FormPengeluaranBarangComponent;
  let fixture: ComponentFixture<FormPengeluaranBarangComponent>;
  let service: FormPengeluaranBarangService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'form-pengeluaran-barang', component: FormPengeluaranBarangComponent }]),
        HttpClientTestingModule,
      ],
      declarations: [FormPengeluaranBarangComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            data: of({
              defaultSort: 'id,asc',
            }),
            queryParamMap: of(
              jest.requireActual('@angular/router').convertToParamMap({
                page: '1',
                size: '1',
                sort: 'id,desc',
              })
            ),
            snapshot: { queryParams: {} },
          },
        },
      ],
    })
      .overrideTemplate(FormPengeluaranBarangComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(FormPengeluaranBarangComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(FormPengeluaranBarangService);

    const headers = new HttpHeaders();
    jest.spyOn(service, 'query').mockReturnValue(
      of(
        new HttpResponse({
          body: [{ id: 'ABC' }],
          headers,
        })
      )
    );
  });

  it('Should call load all on init', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenCalled();
    expect(comp.formPengeluaranBarangs?.[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
  });

  describe('trackId', () => {
    it('Should forward to formPengeluaranBarangService', () => {
      const entity = { id: 'ABC' };
      jest.spyOn(service, 'getFormPengeluaranBarangIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getFormPengeluaranBarangIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
