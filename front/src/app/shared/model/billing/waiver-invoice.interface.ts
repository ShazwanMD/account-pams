import { WaiverFinanceApplication } from './waiver-finance-application.interface';
import { Invoice } from './invoice.interface';
import { Document } from '../../../core/document.interface';

export interface WaiverInvoice extends Document {
    id: number;
    invoice: Invoice;
    waiverApplication: WaiverFinanceApplication;

    // selection
    selected?: boolean;
}
