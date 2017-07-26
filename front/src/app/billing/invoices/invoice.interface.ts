import {Account} from "../../account/accounts/account.interface";
import {MetaObject} from "../../core/meta-object.interface";
import {AcademicSession} from "../../account/academic-sessions/academic-session.interface";
export interface Invoice extends MetaObject {

  id: number;
  referenceNo: string;
  invoiceNo: string;
  sourceNo: string;
  description: string;
  paid: Boolean;
  issuedDate: Date;
  totalPretaxAmount: number;
  totalTaxAmount: number;
  totalAmount: number;
  balanceAmount: number;
  academicSession: AcademicSession;
  account: Account;
}
