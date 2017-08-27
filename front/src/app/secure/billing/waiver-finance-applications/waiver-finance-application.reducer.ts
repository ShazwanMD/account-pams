import {Action} from '@ngrx/store';
import {WaiverApplication} from '../../../shared/model/financialaid/waiver-application.interface';
import { WaiverFinanceApplication } from "../../../shared/model/billing/waiver-finance-application.interface";
import { WaiverFinanceApplicationActions } from "./waiver-finance-application.action";

export type WaiverFinanceApplicationState = WaiverFinanceApplication;

const initialState: WaiverFinanceApplicationState = <WaiverFinanceApplicationState>{};

export function waiverFinanceApplicationReducer(state = initialState, action: Action): WaiverFinanceApplicationState {
  switch (action.type) {
    case WaiverFinanceApplicationActions.FIND_WAIVER_FINANCE_APPLICATION_BY_ID_SUCCESS: {
      return action.payload;
    }
    case WaiverFinanceApplicationActions.FIND_WAIVER_FINANCE_APPLICATION_BY_REFERENCE_NO_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
