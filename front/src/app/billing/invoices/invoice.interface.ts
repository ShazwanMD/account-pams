import {Account} from "../../account/accounts/account.interface";
import {MetaObject} from "../../core/meta-object.interface";
export interface Invoice extends MetaObject{

  id: number;
  referenceNo: string;
  invoiceNo: string;
  sourceNo: string;
  description: string;
  paid: Boolean;
  totalPretaxAmount: number;
  totalTaxAmount: number;
  totalAmount: number;
  balanceAmount: number;
  account: Account;
  // period: Period;
  // requester: CostCenterCode;
}
