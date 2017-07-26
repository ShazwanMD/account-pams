import {MetaObject} from '../../../core/meta-object.interface';
import {ChargeCode} from './charge-code.interface';
export interface FeeScheduleItem extends MetaObject {
  amount: number;
  description: string;
  ordinal: number;
  chargeCode: ChargeCode;

  // selection
  selected?: boolean;
}
