import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IFormBASTPBPP } from '../form-bastpbpp.model';
import { FormBASTPBPPService } from '../service/form-bastpbpp.service';

import { FormBASTPBPPRoutingResolveService } from './form-bastpbpp-routing-resolve.service';

describe('FormBASTPBPP routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: FormBASTPBPPRoutingResolveService;
  let service: FormBASTPBPPService;
  let resultFormBASTPBPP: IFormBASTPBPP | null | undefined;

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
    routingResolveService = TestBed.inject(FormBASTPBPPRoutingResolveService);
    service = TestBed.inject(FormBASTPBPPService);
    resultFormBASTPBPP = undefined;
  });

  describe('resolve', () => {
    it('should return IFormBASTPBPP returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 'ABC' };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultFormBASTPBPP = result;
      });

      // THEN
      expect(service.find).toBeCalledWith('ABC');
      expect(resultFormBASTPBPP).toEqual({ id: 'ABC' });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultFormBASTPBPP = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultFormBASTPBPP).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IFormBASTPBPP>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 'ABC' };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultFormBASTPBPP = result;
      });

      // THEN
      expect(service.find).toBeCalledWith('ABC');
      expect(resultFormBASTPBPP).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
