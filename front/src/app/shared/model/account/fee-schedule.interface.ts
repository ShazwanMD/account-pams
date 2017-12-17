import {MetaObject} from '../../../core/meta-object.interface';
import {StudyMode} from '../common/study-mode.interface';
import {CohortCode} from '../common/cohort-code.interface';
import {ResidencyCode} from '../common/residency-code.interface';
import { StudyCenterCode } from "../common/study-center-code.interface";
export  interface FeeSchedule extends MetaObject {
  code: string;
  description: string;
  residencyCode: ResidencyCode;
  cohortCode: CohortCode;
  studyMode: StudyMode;
  totalAmount: number;
  status:boolean;
  studyCenterCode: StudyCenterCode;
}
