import {Invoice} from "../../billing/invoices/invoice.interface";
import {Account} from "../../account/accounts/account.interface";
import {MetaObject} from "../../core/meta-object.interface";
export interface PromoCodeItem extends MetaObject{
  code:string;
  applied:boolean;
  sourceNo:number;
  account?:Account;
  
//selection
  selected?:boolean;
}
