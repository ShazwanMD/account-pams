import {MetaObject} from '../../../core/meta-object.interface';
import {ChargeCode} from './charge-code.interface';
import {AcademicSession} from './academic-session.interface';
export interface AccountSponsorship extends MetaObject {
  sourceNo: string;
  amount: number;
  startDate: Date;
  chargeCode: ChargeCode;
  session: AcademicSession;
}
