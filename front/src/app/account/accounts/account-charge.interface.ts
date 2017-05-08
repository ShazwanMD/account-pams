import {AcademicSession} from "../academic-sessions/academic-session.interface";
export interface AccountCharge {
  referenceNo:string;
  sourceNo:string;
  amount:number;
  session:AcademicSession;
}
