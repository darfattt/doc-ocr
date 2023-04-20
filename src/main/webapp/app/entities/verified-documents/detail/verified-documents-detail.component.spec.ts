import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { VerifiedDocumentsDetailComponent } from './verified-documents-detail.component';

describe('VerifiedDocuments Management Detail Component', () => {
  let comp: VerifiedDocumentsDetailComponent;
  let fixture: ComponentFixture<VerifiedDocumentsDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [VerifiedDocumentsDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ verifiedDocuments: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(VerifiedDocumentsDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(VerifiedDocumentsDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load verifiedDocuments on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.verifiedDocuments).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
