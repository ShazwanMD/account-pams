import { MetaObject } from '../../../core/meta-object.interface';
import { Invoice } from './invoice.interface';
import { AdvancePayment } from './advance-payment.interface';

export interface Knockoff extends MetaObject {
    referenceNo: string;
    sourceNo: string;
    auditNo: string;
    description: string;
    amount: number;
    issuedDate: Date;
    invoice: Invoice;
    advancePayment: AdvancePayment;
}