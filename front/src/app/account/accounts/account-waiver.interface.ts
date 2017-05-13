import {AcademicSession} from "../academic-sessions/academic-session.interface";
import {MetaObject} from "../../core/meta-object.interface";
export interface AccountWaiver extends MetaObject{
  sourceNo:string;
  amount:number;
  session:AcademicSession;
}
