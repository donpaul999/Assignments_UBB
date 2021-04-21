
import {Injectable} from '@angular/core';

import {HttpClient} from "@angular/common/http";

import {IClient, IClients} from "./client.model";

import {Observable} from "rxjs";
import {map} from "rxjs/operators";


@Injectable()
export class ClientService {
  public clientsUrl = 'http://localhost:8080/api/clients';

  constructor(private httpClient: HttpClient) {
  }

  getClients(): Observable<IClient[]> {
    return this.httpClient
      .get<IClients>(this.clientsUrl).pipe(map(data => data.clients));
    ;
  }

}
