import {Invoice} from "../../billing/invoices/invoice.interface";
import {Account} from "../../account/accounts/account.interface";
import {MetaObject} from "../../core/meta-object.interface";
export interface PromoCodeItem extends MetaObject{

  code:string;
  applied:number;
  sourceNo:number;
  account:Account;
}
