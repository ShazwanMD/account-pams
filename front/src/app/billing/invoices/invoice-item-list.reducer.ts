import {Action} from '@ngrx/store';
import {InvoiceActions} from './invoice.action';
import {InvoiceItem} from '../../shared/model/billing/invoice-item.interface';

export type InvoiceItemListState = InvoiceItem[];

const initialState: InvoiceItemListState = <InvoiceItem[]>[];

export function invoiceItemListReducer(state = initialState, action: Action): InvoiceItemListState {
  switch (action.type) {
    case InvoiceActions.FIND_INVOICE_ITEMS_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
