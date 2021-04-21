import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ClientsListComponent } from './clients/clients-list/clients-list.component';
import { ClientsComponent } from './clients/clients.component';
import {HttpClientModule} from "@angular/common/http";
import {ClientService} from "./clients/shared/client.service";

@NgModule({
  declarations: [
    AppComponent,
    ClientsListComponent,
    ClientsComponent
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    AppRoutingModule
  ],
  providers: [ClientService],
  bootstrap: [AppComponent]
})
export class AppModule { }
