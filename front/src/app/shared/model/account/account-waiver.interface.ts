import {MetaObject} from '../../../core/meta-object.interface';
import {AcademicSession} from './academic-session.interface';
export interface AccountWaiver extends MetaObject {
  sourceNo: string;
  amount: number;
  session: AcademicSession;
}
