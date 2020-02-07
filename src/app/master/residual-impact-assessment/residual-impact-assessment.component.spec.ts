import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ResidualImpactAssessmentComponent } from './residual-impact-assessment.component';

describe('ResidualImpactAssessmentComponent', () => {
  let component: ResidualImpactAssessmentComponent;
  let fixture: ComponentFixture<ResidualImpactAssessmentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ResidualImpactAssessmentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ResidualImpactAssessmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
