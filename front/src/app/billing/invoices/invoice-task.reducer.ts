import {Action} from '@ngrx/store';
import {InvoiceActions} from "./invoice.action";
import {InvoiceTask} from "./invoice-task.interface";

export type InvoiceTaskState = InvoiceTask;

const initialState: InvoiceTaskState = <InvoiceTaskState>{};

export function invoiceTaskReducer(state = initialState, action: Action): InvoiceTaskState {
  switch (action.type) {
    case InvoiceActions.FIND_INVOICE_TASK_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
