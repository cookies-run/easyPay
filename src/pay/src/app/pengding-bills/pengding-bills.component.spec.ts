import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PengdingBillsComponent } from './pengding-bills.component';

describe('PengdingBillsComponent', () => {
  let component: PengdingBillsComponent;
  let fixture: ComponentFixture<PengdingBillsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PengdingBillsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PengdingBillsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
