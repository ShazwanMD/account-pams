import {Sponsor} from '../identity/sponsor.interface';
import {AcademicSession} from '../account/academic-session.interface';
import {CohortCode} from '../common/cohort-code.interface';
import {FacultyCode} from '../common/faculty-code.interface';
export interface SettlementCreator {
  sourceNo: string;
  description: string;
  sponsor?: Sponsor;
  facultyCode?: FacultyCode;
  cohortCode?: CohortCode;
  academicSession?: AcademicSession;
  issuedDate: Date;
}
