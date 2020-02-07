import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ResidualRiskLikelihoodComponent } from './residual-risk-likelihood.component';

describe('ResidualRiskLikelihoodComponent', () => {
  let component: ResidualRiskLikelihoodComponent;
  let fixture: ComponentFixture<ResidualRiskLikelihoodComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ResidualRiskLikelihoodComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ResidualRiskLikelihoodComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
