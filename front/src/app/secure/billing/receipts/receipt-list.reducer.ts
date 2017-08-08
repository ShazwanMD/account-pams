import {Action} from '@ngrx/store';
import {ReceiptActions} from './receipt.action';
import {Receipt} from '../../../shared/model/billing/receipt.interface';

export type ReceiptListState = Receipt[];

const initialState: ReceiptListState = <Receipt[]>[];

export function archivedReceiptListReducer(state = initialState, action: Action): ReceiptListState {
  switch (action.type) {
    case ReceiptActions.FIND_ARCHIVED_RECEIPT_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}

export function receiptListReducer(state = initialState, action: Action): ReceiptListState {
  switch (action.type) {
    case ReceiptActions.FIND_COMPLETED_RECEIPTS_SUCCESS: {
      return action.payload;
    }
    case ReceiptActions.FIND_UNPAID_INVOICES_SUCCESS: {
        return action.payload;
      }
    default: {
      return state;
    }
  }
}
