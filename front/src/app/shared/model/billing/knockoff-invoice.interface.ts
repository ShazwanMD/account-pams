import {Invoice} from './invoice.interface';
import {Knockoff} from './knockoff.interface';
import {Document} from '../../../core/document.interface';

export interface KnockoffInvoice extends Document {
    invoice: Invoice;
    knockoff: Knockoff;
}