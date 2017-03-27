import {MetaObject} from "../../core/meta-object.interface";
export interface InvoiceItem extends MetaObject {
  description: string;
  amount: number;
  balanceAmount: number;
}
