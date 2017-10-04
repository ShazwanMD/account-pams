import {MetaObject} from '../../../core/meta-object.interface';
import {AcademicSession} from './academic-session.interface';
import { WaiverApplicationType } from "../financialaid/waiver-application-type.enum";
export interface AccountWaiver extends MetaObject {
  sourceNo: string;
  amount: number;
  session: AcademicSession;
  status: boolean;
  waiverType: WaiverApplicationType;
}
