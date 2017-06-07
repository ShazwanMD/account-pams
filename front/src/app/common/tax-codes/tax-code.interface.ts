import {MetaObject} from "../../core/meta-object.interface";
export interface TaxCode extends MetaObject{
  code:string;
  description:string;
  accrualType:string;
  purposeType:string;
  rate:string;
  taxRate:string;
  taxType:string;
}