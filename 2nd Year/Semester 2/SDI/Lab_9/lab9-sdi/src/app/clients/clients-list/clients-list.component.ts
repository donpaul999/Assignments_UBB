import { Component, OnInit } from '@angular/core';
import { ClientService } from '../shared/client.service';
import {Router} from "@angular/router";
import {IClient, IClients} from "../shared/client.model";

@Component({
  selector: 'app-clients-list',
  templateUrl: './clients-list.component.html',
  styleUrls: ['./clients-list.component.css']
})
export class ClientsListComponent implements OnInit {
  clients: IClient[];
  errorMessage: string;

  constructor(private clientService: ClientService,
              private router: Router) {
    this.clients = [];
    this.errorMessage = "";

  }

  ngOnInit(): void {
    this.getClients();
  }

  getClients() {
    this.clientService.getClients()
      .subscribe(
        clients => {this.clients = clients;
          console.log(this.clients); },
        error => this.errorMessage = <any>error
      );
  }
}
