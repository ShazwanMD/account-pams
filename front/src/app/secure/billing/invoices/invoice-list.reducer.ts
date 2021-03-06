import {Action} from '@ngrx/store';
import {InvoiceActions} from './invoice.action';
import {Invoice} from '../../../shared/model/billing/invoice.interface';

export type InvoiceListState = Invoice[];

const initialState: InvoiceListState = <Invoice[]>[];

export function archivedInvoiceListReducer(state = initialState, action: Action): InvoiceListState {
  switch (action.type) {
    case InvoiceActions.FIND_ARCHIVED_INVOICES_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}

export function invoiceListReducer(state = initialState, action: Action): InvoiceListState {
  switch (action.type) {
    case InvoiceActions.FIND_COMPLETED_INVOICES_SUCCESS: {
      return action.payload;
    }
    case InvoiceActions.FIND_UNPAID_INVOICES_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
