import {MetaObject} from "../../core/meta-object.interface";
export interface ReceiptItem extends MetaObject {
  description: string;
  dueAmount: number;
  totalAmount: number;
  adjustedAmount: number;
  appliedAmount: number;
  price: number;
  unit: number;

  // selection
  selected?:boolean;
}
