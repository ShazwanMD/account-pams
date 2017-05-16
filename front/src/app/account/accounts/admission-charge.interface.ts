import {AccountCharge} from "./account-charge.interface";
import {StudyMode} from "../../common/study-modes/study-mode.interface";
import {CohortCode} from "../../common/cohort-codes/cohort-code.interface";
export interface AdmissionCharge extends AccountCharge {
  studyMode?: StudyMode;
  cohortCode?: CohortCode;
}
