import {Receipt} from './receipt.interface';
import {Invoice} from './invoice.interface';
import {Document} from '../../../core/document.interface';

export interface ReceiptInvoice extends Document {
    invoice: Invoice;
    receipt: Receipt;

// selection
selected?: boolean;
}
