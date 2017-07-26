import {Action} from '@ngrx/store';
import {InvoiceActions} from './invoice.action';
import {InvoiceTask} from '../../shared/model/billing/invoice-task.interface';

export type InvoiceTaskState = InvoiceTask;

const initialState: InvoiceTaskState = <InvoiceTaskState>{};

export function invoiceTaskReducer(state = initialState, action: Action): InvoiceTaskState {
  switch (action.type) {
    case InvoiceActions.FIND_INVOICE_TASK_BY_TASK_ID_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
