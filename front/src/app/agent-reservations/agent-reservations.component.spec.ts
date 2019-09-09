import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AgentReservationsComponent } from './agent-reservations.component';

describe('AgentReservationsComponent', () => {
  let component: AgentReservationsComponent;
  let fixture: ComponentFixture<AgentReservationsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AgentReservationsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AgentReservationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
