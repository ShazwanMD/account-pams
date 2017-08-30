import { MetaObject } from '../../../core/meta-object.interface';
import { Invoice } from './invoice.interface';
import { AdvancePayment } from './advance-payment.interface';

export interface RefundPayment extends MetaObject {
    referenceNo: string;
    sourceNo: string;
    auditNo: string;
    description: string;
    amount: number;
    issuedDate: Date;
    advancePayment: AdvancePayment;
}