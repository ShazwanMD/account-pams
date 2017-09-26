import { AccountCharge } from "../account/account-charge.interface";
import {Knockoff} from './knockoff.interface';
import {Document} from '../../../core/document.interface';

export interface KnockoffAccountCharge extends Document {
    accountCharge: AccountCharge;
    knockoff: Knockoff;
}