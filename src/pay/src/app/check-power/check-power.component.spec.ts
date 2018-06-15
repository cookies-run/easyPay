import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckPowerComponent } from './check-power.component';

describe('CheckPowerComponent', () => {
  let component: CheckPowerComponent;
  let fixture: ComponentFixture<CheckPowerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CheckPowerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CheckPowerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
