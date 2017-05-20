import {MetaObject} from "../../core/meta-object.interface";
import {ChargeCode} from "../../account/charge-codes/charge-code.interface";
export interface ReceiptItem extends MetaObject {
  description: string;
  dueAmount: number;
  totalAmount: number;
  adjustedAmount: number;
  appliedAmount: number;
  price: number;
  unit: number;
  chargeCode: ChargeCode;

  //selection
  selected?:boolean;
}
