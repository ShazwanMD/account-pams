import {MetaObject} from '../../../core/meta-object.interface';
export interface SecurityChargeCode extends MetaObject {
  section: string;
  description: string;
  offense: string;
  offenseDescription: string;
  amount: number;
  amountDescription: string;
  active: boolean;
}
