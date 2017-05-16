import {AcademicSession} from "../academic-sessions/academic-session.interface";
import {MetaObject} from "../../core/meta-object.interface";
export interface AccountCharge extends MetaObject {
  referenceNo: string;
  sourceNo: string;
  description:string;
  amount: number;
  session: AcademicSession;
}
