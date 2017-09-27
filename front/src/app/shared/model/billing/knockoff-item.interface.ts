import { Invoice } from './invoice.interface';
import { Knockoff } from './knockoff.interface';
import { Document } from '../../../core/document.interface';
import { ChargeCode } from "../account/charge-code.interface";
import { AccountCharge } from '../account/account-charge.interface';

export interface KnockoffItem extends Document {
    id: number;
    description: string;
    dueAmount: number;
    appliedAmount: number;
    totalAmount: number;
    chargeCode: ChargeCode;
    accountCharge: AccountCharge;
    invoice: Invoice;
    knockoff: Knockoff;

      // selection
    selected?: boolean;
}