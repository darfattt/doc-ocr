import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { FormBASTPBPPService } from '../service/form-bastpbpp.service';

import { FormBASTPBPPComponent } from './form-bastpbpp.component';

describe('FormBASTPBPP Management Component', () => {
  let comp: FormBASTPBPPComponent;
  let fixture: ComponentFixture<FormBASTPBPPComponent>;
  let service: FormBASTPBPPService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{ path: 'form-bastpbpp', component: FormBASTPBPPComponent }]), HttpClientTestingModule],
      declarations: [FormBASTPBPPComponent],
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
      .overrideTemplate(FormBASTPBPPComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(FormBASTPBPPComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(FormBASTPBPPService);

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
    expect(comp.formBASTPBPPS?.[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
  });

  describe('trackId', () => {
    it('Should forward to formBASTPBPPService', () => {
      const entity = { id: 'ABC' };
      jest.spyOn(service, 'getFormBASTPBPPIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getFormBASTPBPPIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
