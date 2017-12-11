import {Document} from '../../../core/document.interface';
import {AcademicSession} from '../account/academic-session.interface';
import {Account} from '../account/account.interface';
import { WaiverApplicationType } from "../financialaid/waiver-application-type.enum";
import { GraduateCenterType } from '../financialaid/graduate-center-type.enum';
export interface WaiverFinanceApplication extends Document {
  referenceNo: string;
  sourceNo: string;
  description: string;
  reason: string;
  memo: string;
  balance: number;
  effectiveBalance: number;
  waivedAmount: number;
  gracedAmount: number;
  account: Account;
  academicSession: AcademicSession;
  waiverType: WaiverApplicationType;
  graduateCenterType: GraduateCenterType;
}

