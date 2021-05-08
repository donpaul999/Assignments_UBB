import { Component, OnInit } from '@angular/core';
import { WebdomainService } from '../shared/webdomain.service';
import {Router} from "@angular/router";
import {IWebDomain, IWebDomains} from "../shared/webdomain.model";
import {IClient} from "../../clients/shared/client.model";

@Component({
  selector: 'app-domains-list',
  templateUrl: './webdomains-list.component.html',
  styleUrls: ['./webdomains-list.component.css']
})
export class WebdomainsListComponent implements OnInit {
  domains: IWebDomain[];
  errorMessage: string;
  maxId: number;

  constructor(private domainService: WebdomainService,
              private router: Router) {
    this.domains = [];
    this.errorMessage = "";
    this.maxId = -1;
  }

  ngOnInit(): void {
    this.getDomains();
  }

  validateDomain(domain: IWebDomain) {
    if (domain.name.length < 3)
      this.errorMessage = "WebDomain Name is too short";
    if (!domain.name.includes("."))
      this.errorMessage = "Invalid name";
    if (domain.price == 0 || isNaN(domain.price) || domain.price == null)
      this.errorMessage = "Price can't be null";
  }

  getDomains() {
    this.domainService.getDomains()
      .subscribe(
        domains => {this.domains = domains.sort((a,b) => a.id - b.id);
          this.maxId = this.domains[this.domains.length - 1].id;
          console.log(this.domains); },
        error => this.errorMessage = <any>error
      );
  }

  updateDomain(id: string, name: string, price: string){
    this.errorMessage = "";
    var domain = new IWebDomain();
    domain.id = parseInt(id);
    domain.name = name;
    domain.price = parseInt(price);
    this.validateDomain(domain);
    if(this.errorMessage.length == 0)
      this.domainService.updateDomain(domain).subscribe(data => console.log(data));
  }

  addDomain(name: string, price: string){
    this.errorMessage = "";
    var domain = new IWebDomain();
    domain.id = this.maxId + 1;
    domain.name = name;
    domain.price = parseInt(price);
    this.validateDomain(domain);
    if(this.errorMessage.length == 0)
      this.domainService.addDomain(domain).subscribe(data => {this.maxId = domain.id; location.reload()});
  }

  filterDomainsByName(value: string) {
    this.domainService.filterDomainsByName(value)
      .subscribe(
        domains => {this.domains = domains.sort((a,b) => a.id - b.id);
          //console.log(this.clients);
        },
        error => this.errorMessage = <any>error
      );
  }
}
