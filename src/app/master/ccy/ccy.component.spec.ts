import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CcyComponent } from './ccy.component';

describe('CcyComponent', () => {
  let component: CcyComponent;
  let fixture: ComponentFixture<CcyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CcyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CcyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
