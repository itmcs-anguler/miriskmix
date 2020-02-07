import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RiskIncidentMenuComponent } from './risk-incident-menu.component';

describe('RiskIncidentMenuComponent', () => {
  let component: RiskIncidentMenuComponent;
  let fixture: ComponentFixture<RiskIncidentMenuComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RiskIncidentMenuComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RiskIncidentMenuComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
