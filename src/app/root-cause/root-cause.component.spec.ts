import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RootCauseComponent } from './root-cause.component';

describe('RootCauseComponent', () => {
  let component: RootCauseComponent;
  let fixture: ComponentFixture<RootCauseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RootCauseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RootCauseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
