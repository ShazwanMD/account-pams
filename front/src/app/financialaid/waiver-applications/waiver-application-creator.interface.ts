import {Sponsor} from "../../identity/sponsor.interface";
import {AcademicSession} from "../../account/academic-sessions/academic-session.interface";
import {CohortCode} from "../../common/cohort-codes/cohort-code.interface";
import {FacultyCode} from "../../common/faculty-codes/faculty-code.interface";
export interface WaiverApplicationCreator {
  sourceNo:string;
  description:string;
  reason:string;
  waivedAmount: number;
  sponsor?:Sponsor;
  facultyCode?:FacultyCode;
  cohortCode?:CohortCode;
  academicSession?:AcademicSession;

}
