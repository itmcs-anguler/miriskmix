import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RiskEventComponent } from './risk-event.component';

describe('RiskEventComponent', () => {
  let component: RiskEventComponent;
  let fixture: ComponentFixture<RiskEventComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RiskEventComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RiskEventComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
