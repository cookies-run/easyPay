import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IncreasebillComponent } from './increasebill.component';

describe('IncreasebillComponent', () => {
  let component: IncreasebillComponent;
  let fixture: ComponentFixture<IncreasebillComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IncreasebillComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IncreasebillComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
