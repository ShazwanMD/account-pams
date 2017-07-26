import {MetaObject} from "../../core/meta-object.interface";
export interface SecurityChargesCode extends MetaObject{
  section:string;
  description:string;
  offense:string;
  offenseDescription:string;
  amount:number;
  amountDescription: string;
  active: boolean;
}