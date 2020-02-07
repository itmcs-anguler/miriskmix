import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InherentRiskLikelihoodComponent } from './inherent-risk-likelihood.component';

describe('InherentRiskLikelihoodComponent', () => {
  let component: InherentRiskLikelihoodComponent;
  let fixture: ComponentFixture<InherentRiskLikelihoodComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InherentRiskLikelihoodComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InherentRiskLikelihoodComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
