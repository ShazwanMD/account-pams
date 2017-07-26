import {Account} from '../account/account.interface';
import {MetaObject} from '../../../core/meta-object.interface';
import {Invoice} from '../billing/invoice.interface';
export interface SettlementItem extends MetaObject {
  balanceAmount: number;
  account: Account;
  invoice: Invoice;

  // selection
  selected?: boolean;
}
