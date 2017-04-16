import {Action} from '@ngrx/store';
import {InvoiceTask} from "./invoice-task.interface";
import {InvoiceActions} from "./invoice.action";

export type InvoiceTaskListState = InvoiceTask[];

const initialState: InvoiceTaskListState = <InvoiceTask[]>[];

export function invoiceTaskListReducer(state = initialState, action: Action): InvoiceTaskListState {
  switch (action.type) {
    case InvoiceActions.FIND_ASSIGNED_INVOICE_TASKS_SUCCESS: {
      return action.payload;
    }
    case InvoiceActions.FIND_POOLED_INVOICE_TASKS_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
