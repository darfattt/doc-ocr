import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { FormPernyataanService } from '../service/form-pernyataan.service';

import { FormPernyataanComponent } from './form-pernyataan.component';

describe('FormPernyataan Management Component', () => {
  let comp: FormPernyataanComponent;
  let fixture: ComponentFixture<FormPernyataanComponent>;
  let service: FormPernyataanService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{ path: 'form-pernyataan', component: FormPernyataanComponent }]), HttpClientTestingModule],
      declarations: [FormPernyataanComponent],
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
      .overrideTemplate(FormPernyataanComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(FormPernyataanComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(FormPernyataanService);

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
    expect(comp.formPernyataans?.[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
  });

  describe('trackId', () => {
    it('Should forward to formPernyataanService', () => {
      const entity = { id: 'ABC' };
      jest.spyOn(service, 'getFormPernyataanIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getFormPernyataanIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
