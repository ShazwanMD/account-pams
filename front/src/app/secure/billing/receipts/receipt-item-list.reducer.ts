import {Action} from '@ngrx/store';
import {ReceiptActions} from './receipt.action';
import {ReceiptItem} from '../../../shared/model/billing/receipt-item.interface';

export type ReceiptItemListState = ReceiptItem[];

const initialState: ReceiptItemListState = <ReceiptItem[]>[];

export function receiptItemListReducer(state = initialState, action: Action): ReceiptItemListState {
  switch (action.type) {
    case ReceiptActions.FIND_RECEIPT_ITEMS_SUCCESS: {
      return action.payload;
    }
    case ReceiptActions.FIND_RECEIPT_INVOICE_ITEMS_SUCCESS: {
        return action.payload;
    }
    case ReceiptActions.FIND_RECEIPT_DEBIT_ITEMS_SUCCESS: {
        return action.payload;
    }
    default: {
      return state;
    }
  }
}
