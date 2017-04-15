import {MetaObject} from "../../core/meta-object.interface";
import {AcademicSession} from "./academic-session.interface";
import {ChargeCode} from "./charge-code.interface";
export interface AccountTransaction extends MetaObject {
  sourceNo:string;
  amount:number;
  postedDate:Date;
  chargeCode:ChargeCode;
  session:AcademicSession;
}
