import {MetaObject} from "../../core/meta-object.interface";
import {ChargeCode} from "../../account/charge-codes/charge-code.interface";
export interface CreditNoteItem extends MetaObject {
  description: string;
  amount: number;
  chargeCode:ChargeCode;
  creditNoteItemDate : Date;

  // selection
  selected?:boolean;
}
