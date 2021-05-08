import { Component, OnInit } from '@angular/core';
import {ClientService} from "../shared/client.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-clients-delete',
  templateUrl: './clients-delete.component.html',
  styleUrls: ['./clients-delete.component.css']
})
export class ClientsDeleteComponent implements OnInit {

  constructor(private clientsService: ClientService,
              private route: ActivatedRoute,
              private router: Router
              ) { }

  ngOnInit(): void {
    this.deleteClient();
  }

  private deleteClient(): void{
    const routeParams = this.route.snapshot.paramMap;
    const userIdFromRoute = Number(routeParams.get('id'));
    this.clientsService.deleteClient(String(userIdFromRoute));
    this.router.navigate(['/']).then();
  }

}
