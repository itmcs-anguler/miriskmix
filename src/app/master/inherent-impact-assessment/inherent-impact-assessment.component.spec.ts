import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InherentImpactAssessmentComponent } from './inherent-impact-assessment.component';

describe('InherentImpactAssessmentComponent', () => {
  let component: InherentImpactAssessmentComponent;
  let fixture: ComponentFixture<InherentImpactAssessmentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InherentImpactAssessmentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InherentImpactAssessmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
