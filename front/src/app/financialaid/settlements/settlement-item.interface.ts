import {Invoice} from "../../billing/invoices/invoice.interface";
import {Account} from "../../account/accounts/account.interface";
export interface SettlementItem {

  balanceAmount:number;
  account:Account;
  invoice:Invoice;

  // selection
  selected?:boolean;
}
