import {Sponsor} from "../../identity/sponsor.interface";
import {AcademicSession} from "../../account/academic-sessions/academic-session.interface";
import {CohortCode} from "../../common/cohort-codes/cohort-code.interface";
import {FacultyCode} from "../../common/faculty-codes/faculty-code.interface";
export interface SettlementCreator {
  sourceNo:string;
  description:string;
  sponsor?:Sponsor;
  facultyCode?:FacultyCode;
  cohortCode?:CohortCode;
  academicSession?:AcademicSession;
  issuedDate: Date;
}
