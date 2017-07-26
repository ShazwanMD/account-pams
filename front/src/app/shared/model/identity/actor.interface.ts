import {ActorType} from './actor-type.enum';
export interface Actor {
  id: number;
  identityNo: string;
  name: string;
  email: string;
  phone: string;
  mobile: string;
  fax: string;
  actorType: ActorType;
}
