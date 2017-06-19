import {AcademicSession} from "../academic-sessions/academic-session.interface";
import {StudyMode} from "../../common/study-modes/study-mode.interface";
import {CohortCode} from "../../common/cohort-codes/cohort-code.interface";
import {MetaObject} from "../../core/meta-object.interface";
import {AccountChargeType} from "./account-charge-type.enum";
export interface AccountCharge extends MetaObject {
  referenceNo: string;
  sourceNo: string;
  code: string;
  description: string;
  amount: number;
  chargeType: AccountChargeType;
  session: AcademicSession;
  doc: Date;
  ordinal: number;

  studyMode?: StudyMode;
  cohortCode?: CohortCode;

  // transient
  invoiced?: boolean;

    // selection
  selected?:boolean;
}
