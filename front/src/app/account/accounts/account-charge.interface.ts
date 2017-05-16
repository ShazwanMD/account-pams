import {AcademicSession} from "../academic-sessions/academic-session.interface";
import {MetaObject} from "../../core/meta-object.interface";
import {AccountChargeType} from "./account-charge-type.enum";
export interface AccountCharge extends MetaObject {
  referenceNo: string;
  sourceNo: string;
  description: string;
  amount: number;
  invoiced: boolean;
  chargeType: AccountChargeType;
  session: AcademicSession;
}
