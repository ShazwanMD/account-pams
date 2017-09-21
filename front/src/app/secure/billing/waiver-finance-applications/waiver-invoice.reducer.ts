import {Action} from '@ngrx/store';
import { WaiverInvoice } from "../../../shared/model/billing/waiver-invoice.interface";
import { WaiverFinanceApplicationActions } from "./waiver-finance-application.action";

export type WaiverInvoiceState = WaiverInvoice[];

const initialState: WaiverInvoiceState = <WaiverInvoice[]>[];

export function waiverInvoiceReducer(state = initialState, action: Action): WaiverInvoiceState {
  switch (action.type) {
    case WaiverFinanceApplicationActions.FIND_WAIVER_INVOICE_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
