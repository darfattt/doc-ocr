import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IFormPengeluaranBarang } from '../form-pengeluaran-barang.model';
import { FormPengeluaranBarangService } from '../service/form-pengeluaran-barang.service';

import { FormPengeluaranBarangRoutingResolveService } from './form-pengeluaran-barang-routing-resolve.service';

describe('FormPengeluaranBarang routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: FormPengeluaranBarangRoutingResolveService;
  let service: FormPengeluaranBarangService;
  let resultFormPengeluaranBarang: IFormPengeluaranBarang | null | undefined;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              paramMap: convertToParamMap({}),
            },
          },
        },
      ],
    });
    mockRouter = TestBed.inject(Router);
    jest.spyOn(mockRouter, 'navigate').mockImplementation(() => Promise.resolve(true));
    mockActivatedRouteSnapshot = TestBed.inject(ActivatedRoute).snapshot;
    routingResolveService = TestBed.inject(FormPengeluaranBarangRoutingResolveService);
    service = TestBed.inject(FormPengeluaranBarangService);
    resultFormPengeluaranBarang = undefined;
  });

  describe('resolve', () => {
    it('should return IFormPengeluaranBarang returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 'ABC' };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultFormPengeluaranBarang = result;
      });

      // THEN
      expect(service.find).toBeCalledWith('ABC');
      expect(resultFormPengeluaranBarang).toEqual({ id: 'ABC' });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultFormPengeluaranBarang = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultFormPengeluaranBarang).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IFormPengeluaranBarang>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 'ABC' };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultFormPengeluaranBarang = result;
      });

      // THEN
      expect(service.find).toBeCalledWith('ABC');
      expect(resultFormPengeluaranBarang).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
