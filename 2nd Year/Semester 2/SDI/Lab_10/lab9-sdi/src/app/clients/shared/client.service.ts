
import {Injectable} from '@angular/core';

import {HttpClient, HttpParams} from "@angular/common/http";

import {IClient, IClients} from "./client.model";

import {Observable} from "rxjs";
import {map} from "rxjs/operators";


@Injectable()
export class ClientService {
  public clientsUrl = 'http://localhost:8080/api/clients';
  public removeUrl = 'http://localhost:8080/api/clients';

  constructor(private httpClient: HttpClient) {
  }

  getClients(): Observable<IClient[]> {
    return this.httpClient
      .get<IClients>(this.clientsUrl).pipe(map(data => data.clients));
  }

  deleteClient(id: string): void {
    this.httpClient.delete<IClient>(this.clientsUrl + '/' + id)
      .subscribe({ error: e => console.error(e) });
  }

  updateClient(client: IClient) {
    return this.httpClient.put<IClient>(this.clientsUrl, client);
  }

  addClient(client: IClient) {
    return this.httpClient.post<IClient>(this.clientsUrl, client);
  }

  filterClientsByName(value: string) {
    return this.httpClient
      .get<IClients>(this.clientsUrl + '/filter/' + value).pipe(map(data => data.clients));
  }
}
