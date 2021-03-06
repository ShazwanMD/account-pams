import { TaxCode } from './../common/tax-code.interface';
import {MetaObject} from '../../../core/meta-object.interface';
import {AccountChargeType} from './account-charge-type.enum';
import {StudyMode} from '../common/study-mode.interface';
import {CohortCode} from '../common/cohort-code.interface';
import {AcademicSession} from './academic-session.interface';
export interface AccountCharge extends MetaObject {
  referenceNo: string;
  sourceNo: string;
  description: string;
  amount: number;
  chargeType: AccountChargeType;
  session: AcademicSession;
  taxCode: TaxCode;
  chargeDate: Date;
  taxAmount: number;
  netAmount: number;
  balanceAmount: number;
  paid:Boolean;

  // admission
  studyMode?: StudyMode;
  cohortCode?: CohortCode;
  ordinal: number;

  // transient
  invoiced?: boolean;
  inclusive: boolean;
  

  // selection
  selected?: boolean;
}
