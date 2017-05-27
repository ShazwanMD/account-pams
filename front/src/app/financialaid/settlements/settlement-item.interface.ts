import {Invoice} from "../../billing/invoices/invoice.interface";
import {Account} from "../../account/accounts/account.interface";
import {MetaObject} from "../../core/meta-object.interface";
export interface SettlementItem extends MetaObject {

  balanceAmount:number;
  account:Account;
  invoice:Invoice;

  // selection
  selected?:boolean;
}
