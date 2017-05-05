import {MetaObject} from "../../core/meta-object.interface";
import {ChargeCode} from "../charge-codes/charge-code.interface";
export interface FeeScheduleItem extends MetaObject {
  amount: number;
  chargeCode: ChargeCode;
}
