import {MetaObject} from "../../core/meta-object.interface";
import {StudyMode} from "../../common/study-modes/study-mode.interface";
import {CohortCode} from "../../common/cohort-codes/cohort-code.interface";
export  interface FeeSchedule extends MetaObject {
  code: string;
  description: string;
  cohortCode: CohortCode;
  studyMode: StudyMode;
}
