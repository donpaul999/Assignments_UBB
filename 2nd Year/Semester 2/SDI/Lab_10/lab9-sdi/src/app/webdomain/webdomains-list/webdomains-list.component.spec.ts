import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WebdomainsListComponent } from './webdomains-list.component';

describe('DomainsListComponent', () => {
  let component: WebdomainsListComponent;
  let fixture: ComponentFixture<WebdomainsListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WebdomainsListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WebdomainsListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
