import {AcademicSession} from '../academic-sessions/academic-session.interface';
import {MetaObject} from '../../core/meta-object.interface';
import {AccountChargeType} from './account-charge-type.enum';
import {StudyMode} from '../../common/study-modes/study-mode.interface';
import {CohortCode} from '../../common/cohort-codes/cohort-code.interface';
export interface AccountCharge extends MetaObject {
  referenceNo: string;
  sourceNo: string;
  description: string;
  amount: number;
  chargeType: AccountChargeType;
  session: AcademicSession;

  // admission
  studyMode?: StudyMode;
  cohortCode?: CohortCode;
  ordinal: number;

  // transient
  invoiced?: boolean;

  // selection
  selected?: boolean;
}
