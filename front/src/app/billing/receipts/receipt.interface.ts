import {Account} from "../../account/accounts/account.interface";
import {MetaObject} from "../../core/meta-object.interface";
export interface Receipt extends MetaObject {
  id: number;
  referenceNo: string;
  receiptNo: string;
  sourceNo: string;
  auditNo: string;
  description: string;
  totalApplied: number;
  totalReceived: number;
  totalAmount: number;
  balanceAmount: number;
  account: Account;
}
