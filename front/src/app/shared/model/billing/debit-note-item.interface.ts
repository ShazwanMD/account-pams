import {MetaObject} from '../../../core/meta-object.interface';
import {ChargeCode} from '../account/charge-code.interface';
export interface DebitNoteItem extends MetaObject {
  description: string;
  amount: number;
  balanceAmount: number;
  chargeCode: ChargeCode;
  debitNoteItemDate: Date;

  // selection
  selected?: boolean;
}
