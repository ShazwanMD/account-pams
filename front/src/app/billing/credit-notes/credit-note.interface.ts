import { MetaObject } from "../../core/meta-object.interface";
import { Invoice } from "../invoices/invoice.interface";
export interface CreditNote extends MetaObject {
  id: number;
  referenceNo: string;
  sourceNo: string;
  description:string;
  totalAmount:number;
  invoice?:Invoice;
}
