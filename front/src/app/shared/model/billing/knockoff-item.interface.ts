import { Invoice } from './invoice.interface';
import { Knockoff } from './knockoff.interface';
import { Document } from '../../../core/document.interface';
import { ChargeCode } from "../account/charge-code.interface";

export interface KnockoffItem extends Document {
    id: number;
    description: string;
    dueAmount: number;
    appliedAmount: number;
    totalAmount: number;
    chargeCode: ChargeCode;
    invoice: Invoice;
    knockoff: Knockoff;
}