import {MetaObject} from '../../../core/meta-object.interface';
import {TaxCode} from '../common/tax-code.interface';
export interface ChargeCode extends MetaObject {
  code: string;
  description: string;
  priority: number;
  taxCode?: TaxCode;
  inclusive: boolean;
}
