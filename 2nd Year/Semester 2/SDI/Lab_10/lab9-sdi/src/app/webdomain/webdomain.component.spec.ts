import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WebdomainComponent } from './webdomain.component';

describe('DomainComponent', () => {
  let component: WebdomainComponent;
  let fixture: ComponentFixture<WebdomainComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WebdomainComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WebdomainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
