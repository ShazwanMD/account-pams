import { WaiverFinanceApplication } from './waiver-finance-application.interface';
import { AccountCharge } from '../account/account-charge.interface';
import { Document } from '../../../core/document.interface';

export interface WaiverAccountCharge extends Document {
    accountCharge: AccountCharge;
    waiverApplication: WaiverFinanceApplication;

    // selection
    selected?: boolean;
}
