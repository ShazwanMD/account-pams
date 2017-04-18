import {Action} from '@ngrx/store';
import {ReceiptActions} from "./receipt.action";
import {Receipt} from "./receipt.interface";

export type ReceiptState = Receipt;

const initialState: ReceiptState = <ReceiptState>{};

export function receiptReducer(state = initialState, action: Action): ReceiptState {
  switch (action.type) {
    case ReceiptActions.FIND_RECEIPT_BY_ID_SUCCESS: {
      return action.payload;
    }
    case ReceiptActions.FIND_RECEIPT_BY_REFERENCE_NO_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
