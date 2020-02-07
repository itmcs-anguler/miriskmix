import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GrossImpactComponent } from './gross-impact.component';

describe('GrossImpactComponent', () => {
  let component: GrossImpactComponent;
  let fixture: ComponentFixture<GrossImpactComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GrossImpactComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GrossImpactComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
