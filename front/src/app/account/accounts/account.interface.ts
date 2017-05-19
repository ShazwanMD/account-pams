import {Actor} from "../../identity/actor.interface";
import {MetaObject} from "../../core/meta-object.interface";
export  interface Account extends MetaObject{
  code:string;
  description:string;
  name:string;
  email:string;
  actor?: Actor;

  // transient
  balance?:number;
  effectiveBalance?:number;
}
