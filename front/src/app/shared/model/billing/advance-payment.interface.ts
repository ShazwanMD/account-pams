import { Account } from '../account/account.interface';
import { MetaObject } from '../../../core/meta-object.interface';
import { Receipt } from './receipt.interface';

export interface AdvancePayment extends MetaObject {
    referenceNo: string;
    description: string;
    amount: number;
    balanceAmount: number;
    receipt: Receipt;
    status: boolean;
    account: Account;
}