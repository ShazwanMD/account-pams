import { DebitNote } from './debit-note.interface';
import {Receipt} from './receipt.interface';
import {Document} from '../../../core/document.interface';

export interface ReceiptDebitNote extends Document {
    id: number;
    debitNote: DebitNote;
    receipt: Receipt;

selected?: boolean;
}
