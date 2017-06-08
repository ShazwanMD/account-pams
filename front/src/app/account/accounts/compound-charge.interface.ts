import {AccountCharge} from "./account-charge.interface";
export interface CompoundCharge extends AccountCharge {
  compoundCode : string;
  compoundDescription : string;
}
