import {Action} from '@ngrx/store';
import { WaiverItem } from "../../../shared/model/billing/waiver-item.interface";
import { WaiverFinanceApplicationActions } from "./waiver-finance-application.action";

export type WaiverItemListState = WaiverItem[];

const initialState: WaiverItemListState = <WaiverItem[]>[];

export function waiverItemListReducer(state = initialState, action: Action): WaiverItemListState {
  switch (action.type) {
    case WaiverFinanceApplicationActions.FIND_WAIVER_ITEMS_SUCCESS: {
      return action.payload;
    }
    case WaiverFinanceApplicationActions.FIND_WAIVER_DEBIT_ITEMS_SUCCESS: {
        return action.payload;
      }
    case WaiverFinanceApplicationActions.FIND_WAIVER_INVOICE_ITEMS_SUCCESS: {
        return action.payload;
      }
    default: {
      return state;
    }
  }
}
