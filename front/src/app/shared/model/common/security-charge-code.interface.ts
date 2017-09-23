import { TaxCode } from './tax-code.interface';
import {MetaObject} from '../../../core/meta-object.interface';
export interface SecurityChargeCode extends MetaObject {
  section: string;
  description: string;
  offense: string;
  offenseDescription: string;
  amount: number;
  netAmount: number;
  taxAmount: number;
  amountDescription: string;
  inclusive: boolean;
  active: boolean;

  taxCode: TaxCode;
}
