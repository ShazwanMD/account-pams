import { DebitNote } from './debit-note.interface';
import {MetaObject} from '../../../core/meta-object.interface';
import {Invoice} from './invoice.interface';
import {ChargeCode} from '../account/charge-code.interface';
import { AccountCharge } from "../account/account-charge.interface";
import {ReceiptItemType} from './receipt-item-type.enum';

export interface ReceiptItem extends MetaObject {
  description: string;
  dueAmount: number;
  totalAmount: number;
  adjustedAmount: number;
  appliedAmount: number;
  price: number;
  unit: number;
  chargeCode: ChargeCode;
  invoice: Invoice;
  accountCharge: AccountCharge;
  debitNote: DebitNote;
  receiptItemType: ReceiptItemType;

  // selection
  selected?: boolean;
}
