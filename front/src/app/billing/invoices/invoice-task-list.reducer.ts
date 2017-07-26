import {Action} from '@ngrx/store';
import {InvoiceActions} from './invoice.action';
import {InvoiceTask} from '../../shared/model/billing/invoice-task.interface';

export type InvoiceTaskListState = InvoiceTask[];

const initialState: InvoiceTaskListState = <InvoiceTask[]>[];

export function assignedInvoiceTaskListReducer(state = initialState, action: Action): InvoiceTaskListState {
  switch (action.type) {
    case InvoiceActions.FIND_ASSIGNED_INVOICE_TASKS_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}

export function pooledInvoiceTaskListReducer(state = initialState, action: Action): InvoiceTaskListState {
  switch (action.type) {
    case InvoiceActions.FIND_POOLED_INVOICE_TASKS_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
