import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RiskCategoryLevelOneComponent } from './risk-category-level-one.component';

describe('RiskCategoryLevelOneComponent', () => {
  let component: RiskCategoryLevelOneComponent;
  let fixture: ComponentFixture<RiskCategoryLevelOneComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RiskCategoryLevelOneComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RiskCategoryLevelOneComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
