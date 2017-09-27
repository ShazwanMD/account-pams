import {MetaObject} from '../../../core/meta-object.interface';
import {Invoice} from './invoice.interface';
import {ChargeCode} from '../account/charge-code.interface';
import { WaiverFinanceApplication } from './waiver-finance-application.interface';
import { DebitNote } from "./debit-note.interface";

export interface WaiverItem extends MetaObject {
  description: string;
  dueAmount: number;
  totalAmount: number;
  appliedAmount: number;
  chargeCode: ChargeCode;
  invoice: Invoice;
  debitNote: DebitNote;
  waiverApplication: WaiverFinanceApplication;
  // selection
  selected?: boolean;
}
