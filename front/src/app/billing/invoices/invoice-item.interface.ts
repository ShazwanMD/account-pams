import {MetaObject} from "../../core/meta-object.interface";
import {ChargeCode} from "../../account/charge-codes/charge-code.interface";
export interface InvoiceItem extends MetaObject {
  description: string;
  amount: number;
  balanceAmount: number;
  chargeCode:ChargeCode;
}
