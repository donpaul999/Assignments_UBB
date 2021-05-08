import { Component, OnInit } from '@angular/core';
import { ClientService } from '../shared/client.service';
import {Router} from "@angular/router";
import {IClient, IClients} from "../shared/client.model";
import {HttpParams} from "@angular/common/http";

@Component({
  selector: 'app-clients-list',
  templateUrl: './clients-list.component.html',
  styleUrls: ['./clients-list.component.css']
})
export class ClientsListComponent implements OnInit {
  clients: IClient[];
  errorMessage: string;
  maxId: number;

  constructor(private clientService: ClientService,
              private router: Router) {
    this.clients = [];
    this.errorMessage = "";
    this.maxId = -1;
  }

  ngOnInit(): void {
    this.getClients();
  }

  validateClient(client: IClient) {
    if (client.name.length < 3)
      this.errorMessage = "Name is too short";
  }

  getClients() {
    this.clientService.getClients()
      .subscribe(
        clients => {this.clients = clients.sort((a,b) => a.id - b.id);
          this.maxId = this.clients[this.clients.length - 1].id;
          //console.log(this.clients);
        },
        error => this.errorMessage = <any>error
      );
  }

  updateClient(id: string, name: string, business: boolean){
    this.errorMessage = "";
    var client = new IClient();
    client.id = parseInt(id);
    client.name = name;
    client.isBusiness = business;
    this.validateClient(client);
    if(this.errorMessage.length == 0)
      this.clientService.updateClient(client).subscribe(data => console.log(data));
  }

  addClient(name: string, business: boolean){
    this.errorMessage = "";
    var client = new IClient();
    client.id = this.maxId + 1;
    client.name = name;
    client.isBusiness = business;
    this.validateClient(client);
    if(this.errorMessage.length == 0)
      this.clientService.addClient(client).subscribe(data => {this.maxId = client.id; location.reload()});
  }

  filterClientsByName(value: string) {
    this.clientService.filterClientsByName(value)
      .subscribe(
        clients => {this.clients = clients.sort((a,b) => a.id - b.id);
          //console.log(this.clients);
        },
        error => this.errorMessage = <any>error
      );
  }
}
