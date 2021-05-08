import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ClientsListComponent } from './clients/clients-list/clients-list.component';
import { ClientsComponent } from './clients/clients.component';
import {HttpClientModule} from "@angular/common/http";
import {ClientService} from "./clients/shared/client.service";
import { WebdomainComponent } from './webdomain/webdomain.component';
import {WebdomainService} from "./webdomain/shared/webdomain.service";
import { WebdomainsListComponent } from './webdomain/webdomains-list/webdomains-list.component';
import { ClientsDeleteComponent } from './clients/clients-delete/clients-delete.component';
import { WebdomainsDeleteComponent } from './webdomain/webdomains-delete/webdomains-delete.component';
import { ClientsUpdateComponent } from './clients/clients-update/clients-update.component';

@NgModule({
  declarations: [
    AppComponent,
    ClientsListComponent,
    ClientsComponent,
    WebdomainComponent,
    WebdomainsListComponent,
    ClientsDeleteComponent,
    WebdomainsDeleteComponent,
    ClientsUpdateComponent
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    AppRoutingModule
  ],
  providers: [ClientService, WebdomainService],
  bootstrap: [AppComponent]
})
export class AppModule { }
