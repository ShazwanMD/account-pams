import { MetaObject } from "../../core/meta-object.interface";
import { Invoice } from "../invoices/invoice.interface";
export interface DebitNote extends MetaObject {
  code:string;
  description:string;
  totalAmount:number;
  invoice?:Invoice;
  

}
