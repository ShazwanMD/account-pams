import {MetaObject} from "../../core/meta-object.interface";
import {AcademicSession} from "./academic-session.interface";
export interface AccountTransaction extends MetaObject {
  sourceNo:string;
  amount:number;
  postedDate:Date;
  session:AcademicSession;
}
