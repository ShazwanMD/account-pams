import {MetaObject} from '../../../core/meta-object.interface';
export interface TaxCode extends MetaObject {
  code: string;
  description: string;
  taxRate: number;
}
