import { Component, OnInit } from '@angular/core';
import {ClientService} from "../../clients/shared/client.service";
import {ActivatedRoute, Router} from "@angular/router";
import {WebdomainService} from "../shared/webdomain.service";

@Component({
  selector: 'app-webdomains-delete',
  templateUrl: './webdomains-delete.component.html',
  styleUrls: ['./webdomains-delete.component.css']
})
export class WebdomainsDeleteComponent implements OnInit {

  constructor(private webdomainService: WebdomainService,
              private route: ActivatedRoute,
              private router: Router
  ) { }

  ngOnInit(): void {
    this.deleteWebDomain();
  }

  private deleteWebDomain(): void{
    const routeParams = this.route.snapshot.paramMap;
    const webIdFromRoute = Number(routeParams.get('id'));
    this.webdomainService.deleteWebDomain(String(webIdFromRoute));
    this.router.navigate(['/']).then();
  }
}
