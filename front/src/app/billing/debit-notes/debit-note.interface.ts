import { MetaObject } from "../../core/meta-object.interface";
import { Invoice } from "../invoices/invoice.interface";
import { ChargeCode } from "../../account/charge-codes/charge-code.interface";
export interface DebitNote extends MetaObject {
  id: number;
  referenceNo: string;
  sourceNo: string;
  description:string;
  totalAmount:number;
  accountCode: string;
  debitNoteDate : Date;
  accountName: string;
  chargeCode:ChargeCode;
  invoice?:Invoice;
}
