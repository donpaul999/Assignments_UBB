
import {Injectable} from '@angular/core';

import {HttpClient, HttpParams} from "@angular/common/http";

import {IWebDomain, IWebDomains} from "./webdomain.model";

import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {IClient, IClients} from "../../clients/shared/client.model";


@Injectable()
export class WebdomainService {
  public webdomainsUrl = 'http://localhost:8080/api/domains';

  constructor(private httpClient: HttpClient) {
  }

  getDomains(): Observable<IWebDomain[]> {
    return this.httpClient
      .get<IWebDomains>(this.webdomainsUrl).pipe(map(data => data.webdomains));
  }

  deleteWebDomain(id: string): void {
    this.httpClient.delete<IWebDomain>(this.webdomainsUrl + '/' + id)
      .subscribe({ error: e => console.error(e) });
  }

  updateDomain(domain: IWebDomain) {
    return this.httpClient.put<IWebDomain>(this.webdomainsUrl, domain);
  }

  addDomain(domain: IWebDomain) {
    return this.httpClient.post<IWebDomain>(this.webdomainsUrl, domain);
  }

  filterDomainsByName(value: string) {
    return this.httpClient
      .get<IWebDomains>(this.webdomainsUrl + '/filter/' + value).pipe(map(data => data.webdomains));
  }
}
