import {MetaObject} from "../../core/meta-object.interface";
export  interface AccountCharge extends MetaObject{
  referenceNo:string;
  sourceNo:string;
  description:string;
  amount:string;

}
