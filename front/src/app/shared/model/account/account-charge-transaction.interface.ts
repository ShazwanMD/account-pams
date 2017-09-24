import {MetaObject} from '../../../core/meta-object.interface';
import {ChargeCode} from './charge-code.interface';
import {AcademicSession} from './academic-session.interface';

export interface AccountChargeTransaction extends MetaObject {
  sourceNo: string;
  amount: number;
  postedDate: Date;
  chargeCode: ChargeCode;
  session: AcademicSession;
}
