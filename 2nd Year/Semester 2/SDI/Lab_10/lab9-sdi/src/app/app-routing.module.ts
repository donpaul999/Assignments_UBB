import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientsListComponent } from './clients/clients-list/clients-list.component';
import { WebdomainsListComponent } from "./webdomain/webdomains-list/webdomains-list.component";
import {ClientsDeleteComponent} from "./clients/clients-delete/clients-delete.component";
import {ClientsUpdateComponent} from "./clients/clients-update/clients-update.component";
import {WebdomainsDeleteComponent} from "./webdomain/webdomains-delete/webdomains-delete.component";

const routes: Routes = [
  { path: 'clients-list', component: ClientsListComponent },
  { path: 'webdomains-list', component: WebdomainsListComponent },
  { path: 'webdomains-delete/:id', component: WebdomainsDeleteComponent },
  { path: 'clients-delete/:id', component: ClientsDeleteComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
