import {MetaObject} from "../../core/meta-object.interface";
import {ChargeCode} from "../../account/charge-codes/charge-code.interface";
export interface DebitNoteItem extends MetaObject {
  description: string;
  amount: number;
  chargeCode:ChargeCode;

  // selection
  selected?:boolean;
}
