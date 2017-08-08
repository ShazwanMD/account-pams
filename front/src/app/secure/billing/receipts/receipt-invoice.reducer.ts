import {Action} from '@ngrx/store';
import {ReceiptActions} from './receipt.action';
import {ReceiptInvoice} from '../../../shared/model/billing/receipt-invoice.interface';

export type ReceiptInvoiceListState = ReceiptInvoice[];

const initialState: ReceiptInvoiceListState = <ReceiptInvoice[]>[];

export function receiptInvoiceListReducer(state = initialState, action: Action): ReceiptInvoiceListState {
  switch (action.type) {
    case ReceiptActions.FIND_RECEIPTS_BY_INVOICE_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
