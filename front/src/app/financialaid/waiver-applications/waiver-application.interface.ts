import {Document} from "../../core/document.interface";
import {AcademicSession} from "../../account/accounts/academic-session.interface";
import {Account} from "../../account/accounts/account.interface";
export interface WaiverApplication extends Document{

  reason:string;
  memo:string;
  balance:number;
  waivedAmount:number;
  gracedAmount:number;
  account:Account;
  academicSession:AcademicSession;

}

