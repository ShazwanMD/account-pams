import {Actor} from '../identity/actor.interface';
import {MetaObject} from '../../../core/meta-object.interface';
import {AccountTransactionCode} from './account-transaction-code.enum';

export  interface AccountActivity extends MetaObject {
  sourceNo: string;
  totalAmount: number;
  transactionCode: AccountTransactionCode;

}
