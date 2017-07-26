import {MetaObject} from '../../../core/meta-object.interface';
import {ChargeCode} from '../account/charge-code.interface';
import {TaxCode} from '../../../common/tax-codes/tax-code.interface';
// todo(hajar): pretax, tax, total
export interface InvoiceItem extends MetaObject {
  description: string;
  amount: number;
  balanceAmount: number;
  chargeCode: ChargeCode;
  taxCode?: TaxCode;
  inclusive: boolean;

  // selection
  selected?: boolean;
}
