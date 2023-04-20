import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { FormSuratJalanService } from '../service/form-surat-jalan.service';

import { FormSuratJalanComponent } from './form-surat-jalan.component';

describe('FormSuratJalan Management Component', () => {
  let comp: FormSuratJalanComponent;
  let fixture: ComponentFixture<FormSuratJalanComponent>;
  let service: FormSuratJalanService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule.withRoutes([{ path: 'form-surat-jalan', component: FormSuratJalanComponent }]),
        HttpClientTestingModule,
      ],
      declarations: [FormSuratJalanComponent],
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
      .overrideTemplate(FormSuratJalanComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(FormSuratJalanComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(FormSuratJalanService);

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
    expect(comp.formSuratJalans?.[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
  });

  describe('trackId', () => {
    it('Should forward to formSuratJalanService', () => {
      const entity = { id: 'ABC' };
      jest.spyOn(service, 'getFormSuratJalanIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getFormSuratJalanIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
