import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AgentAccommodationsComponent } from './agent-accommodations.component';

describe('AgentAccommodationsComponent', () => {
  let component: AgentAccommodationsComponent;
  let fixture: ComponentFixture<AgentAccommodationsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AgentAccommodationsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AgentAccommodationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
