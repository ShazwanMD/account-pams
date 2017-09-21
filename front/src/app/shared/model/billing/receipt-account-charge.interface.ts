import {Receipt} from './receipt.interface';
import {Document} from '../../../core/document.interface';
import { AccountCharge } from '../account/account-charge.interface';

export interface ReceiptAccountCharge extends Document {
    accountCharge: AccountCharge;
    receipt: Receipt;
}