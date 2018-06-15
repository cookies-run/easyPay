import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AliBackComponent } from './ali-back.component';

describe('AliBackComponent', () => {
  let component: AliBackComponent;
  let fixture: ComponentFixture<AliBackComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AliBackComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AliBackComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
