import { AccountCharge } from '../account/account-charge.interface';
import {Knockoff} from './knockoff.interface';
import {Document} from '../../../core/document.interface';

export interface KnockoffAccountCharge extends Document {
    id: number;
    accountCharge: AccountCharge;
    knockoff: Knockoff;

selected?: boolean;
}