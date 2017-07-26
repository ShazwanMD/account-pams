import {MetaObject} from '../../../core/meta-object.interface';
export interface VoucherItem extends MetaObject {
  description: string;
  amount: number;
  balanceAmount: number;
}
