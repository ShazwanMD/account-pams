import {Actor} from "../../identity/actor.interface";
import {MetaObject} from "../../core/meta-object.interface";
export  interface Account extends MetaObject{
  code:string;
  name:string;
  email:string;
  actor?: Actor;
  balanceAmount:number;
}
