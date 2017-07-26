import {MetaObject} from '../../../core/meta-object.interface';
import {Invoice} from './invoice.interface';
export interface CreditNote extends MetaObject {
  id: number;
  referenceNo: string;
  sourceNo: string;
  description: string;
  totalAmount: number;
  accountCode: string;
  accountName: string;
  creditNoteDate: Date;
  invoice?: Invoice;
}
