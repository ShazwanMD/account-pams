import {Action} from '@ngrx/store';
import { WaiverAccountCharge } from "../../../shared/model/billing/waiver-account-charge.interface";
import { WaiverFinanceApplicationActions } from "./waiver-finance-application.action";

export type WaiverAccountChargeState = WaiverAccountCharge[];

const initialState: WaiverAccountChargeState = <WaiverAccountCharge[]>[];

export function waiverAccountChargeReducer(state = initialState, action: Action): WaiverAccountChargeState {
  switch (action.type) {
    case WaiverFinanceApplicationActions.FIND_WAIVER_ACCOUNT_CHARGE_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
