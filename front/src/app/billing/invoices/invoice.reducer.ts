import {Action} from '@ngrx/store';
import {InvoiceActions} from "./invoice.action";
import {Invoice} from "./invoice.interface";

export type InvoiceState = Invoice;

const initialState: InvoiceState = <InvoiceState>{};

export function invoiceReducer(state = initialState, action: Action): InvoiceState {
  switch (action.type) {
    case InvoiceActions.FIND_INVOICE_BY_ID_SUCCESS: {
      return action.payload;
    }
    case InvoiceActions.FIND_INVOICE_BY_TASK_ID_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
