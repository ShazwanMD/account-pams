import {Document} from "../../core/document.interface";
import {AcademicSession} from "../../account/academic-sessions/academic-session.interface";
import {Account} from "../../account/accounts/account.interface";
export interface WaiverApplication extends Document{
  referenceNo:string;
  sourceNo:string;
  description:string;
  reason:string;
  memo:string;
  balance:number;
  effectiveBalance:number;
  waivedAmount:number;
  gracedAmount:number;
  account:Account;
  academicSession:AcademicSession;
}

