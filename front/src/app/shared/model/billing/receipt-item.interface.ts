import {MetaObject} from '../../../core/meta-object.interface';
import {Invoice} from './invoice.interface';
import {ChargeCode} from '../account/charge-code.interface';

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

  // selection
  selected?: boolean;
}
