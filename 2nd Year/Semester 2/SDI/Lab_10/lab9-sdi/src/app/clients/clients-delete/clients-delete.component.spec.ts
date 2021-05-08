import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientsDeleteComponent } from './clients-delete.component';

describe('ClientsDeleteComponent', () => {
  let component: ClientsDeleteComponent;
  let fixture: ComponentFixture<ClientsDeleteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClientsDeleteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClientsDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
