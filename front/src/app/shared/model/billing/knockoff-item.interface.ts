import { MetaObject } from './../../../core/meta-object.interface';
import { DebitNote } from './debit-note.interface';
import { Invoice } from './invoice.interface';
import { Knockoff } from './knockoff.interface';
import { Document } from '../../../core/document.interface';
import { ChargeCode } from "../account/charge-code.interface";
import { AccountCharge } from '../account/account-charge.interface';
import { KnockoffItemType } from './knockoff-item-type.enum';


export interface KnockoffItem extends MetaObject {
  description: string;
  dueAmount: number;
  totalAmount: number;
  appliedAmount: number;
  chargeCode: ChargeCode;
  invoice: Invoice;
  debitNote: DebitNote;
  knockoff: Knockoff;
  accountCharge: AccountCharge;
  knockoffItemType: KnockoffItemType;
  // selection
  selected?: boolean;
}
