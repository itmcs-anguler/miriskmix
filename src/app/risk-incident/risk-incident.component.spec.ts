import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RiskIncidentComponent } from './risk-incident.component';

describe('RiskIncidentComponent', () => {
  let component: RiskIncidentComponent;
  let fixture: ComponentFixture<RiskIncidentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RiskIncidentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RiskIncidentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
