export class IClient {
  id!: number;
  name!: string ;
  isBusiness!: boolean;
}
export interface IClients {
  clients: IClient[];
}
