import {MetaObject} from "../../core/meta-object.interface";
export interface ReceiptItem extends MetaObject {
  description: string;
  amount: number;
  balanceAmount: number;
}
