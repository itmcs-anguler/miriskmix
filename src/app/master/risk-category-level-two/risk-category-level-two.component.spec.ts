import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RiskCategoryLevelTwoComponent } from './risk-category-level-two.component';

describe('RiskCategoryLevelTwoComponent', () => {
  let component: RiskCategoryLevelTwoComponent;
  let fixture: ComponentFixture<RiskCategoryLevelTwoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RiskCategoryLevelTwoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RiskCategoryLevelTwoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
