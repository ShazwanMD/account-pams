import {Invoice} from "../../billing/invoices/invoice.interface";
import {Account} from "../../account/accounts/account.interface";
export interface PromoCodeItem {

  code:string;
  amount:number;
  account:Account;
}
