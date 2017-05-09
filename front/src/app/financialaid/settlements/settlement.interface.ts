import {Sponsor} from "../../identity/sponsor.interface";
import {AcademicSession} from "../../account/academic-sessions/academic-session.interface";
export interface Settlement {

  referenceNo:string;
  sourceNo:string;
  description:string;
  //sponsor:Sponsor;
  academicSession:AcademicSession;

}
