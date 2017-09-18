import {Action} from '@ngrx/store';
import {KnockoffActions} from './knockoff.action';
import {KnockoffInvoice} from '../../../shared/model/billing/knockoff-invoice.interface';

export type KnockoffInvoiceListState = KnockoffInvoice[];

const initialState: KnockoffInvoiceListState = <KnockoffInvoice[]>[];

export function knockoffInvoiceListReducer(state = initialState, action: Action): KnockoffInvoiceListState {
  switch (action.type) {
    case KnockoffActions.FIND_INVOICE_BY_KNOCKOFF_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
