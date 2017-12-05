import {MetaObject} from '../../../core/meta-object.interface';
import {ChargeCode} from '../account/charge-code.interface';
import { CreditNote } from "./credit-note.interface";
export interface CreditNoteItem extends MetaObject {
  description: string;
  amount: number;
  balanceAmount: number;
  chargeCode: ChargeCode;
  creditNoteItemDate: Date;
  creditNote: CreditNote;

  // selection
  selected?: boolean;
}
