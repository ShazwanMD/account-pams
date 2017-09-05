import {Action} from '@ngrx/store';
import { WaiverFinanceApplicationActions } from "./waiver-finance-application.action";
import { WaiverFinanceApplication } from "../../../shared/model/billing/waiver-finance-application.interface";

export type WaiverFinanceApplicationListState = WaiverFinanceApplication[];

const initialState: WaiverFinanceApplicationListState = <WaiverFinanceApplication[]>[];

export function archivedWaiverFinanceApplicationListReducer(state = initialState, action: Action): WaiverFinanceApplicationListState {
  switch (action.type) {
    case WaiverFinanceApplicationActions.FIND_ARCHIVED_WAIVER_FINANCE_APPLICATIONS_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}

export function waiverFinanceApplicationListReducer(state = initialState, action: Action): WaiverFinanceApplicationListState {
  switch (action.type) {
    case WaiverFinanceApplicationActions.FIND_COMPLETED_WAIVER_FINANCE_APPLICATIONS_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
