import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FixStudentInfoComponent } from './fix-student-info.component';

describe('FixStudentInfoComponent', () => {
  let component: FixStudentInfoComponent;
  let fixture: ComponentFixture<FixStudentInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FixStudentInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FixStudentInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
