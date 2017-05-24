import {MetaObject} from "../../core/meta-object.interface";
import {ChargeCode} from "../charge-codes/charge-code.interface";
export interface FeeScheduleItem extends MetaObject {
  amount: number;
  description:string;
  chargeCode: ChargeCode;

  // selection
  selected?: boolean;
}
