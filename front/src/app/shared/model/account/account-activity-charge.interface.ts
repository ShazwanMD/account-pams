import {Actor} from '../identity/actor.interface';
import {MetaObject} from '../../../core/meta-object.interface';
import {AccountChargeType} from './account-charge-type.enum';

export  interface AccountActivityCharge extends MetaObject {
  sourceNo: string;
  totalAmount: number;
  transactionCode: AccountChargeType;
  postedDate: Date;
  description: string;
}
