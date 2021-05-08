import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WebdomainsDeleteComponent } from './webdomains-delete.component';

describe('WebdomainsDeleteComponent', () => {
  let component: WebdomainsDeleteComponent;
  let fixture: ComponentFixture<WebdomainsDeleteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WebdomainsDeleteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WebdomainsDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
