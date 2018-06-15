import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HistorybillComponent } from './historybill.component';

describe('HistorybillComponent', () => {
  let component: HistorybillComponent;
  let fixture: ComponentFixture<HistorybillComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HistorybillComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HistorybillComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
