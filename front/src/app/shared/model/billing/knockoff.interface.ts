import { MetaObject } from '../../../core/meta-object.interface';
import { Invoice } from './invoice.interface';
import { AdvancePayment } from './advance-payment.interface';

export interface Knockoff extends MetaObject {
    id: number;
    referenceNo: string;
    sourceNo: string;
    auditNo: string;
    description: string;
    amount: number;
    balanceAmount: number;
    totalAmount: number;
    issuedDate: Date;
    payments?: AdvancePayment;
    accountCode: string;
    accountName: string;
}