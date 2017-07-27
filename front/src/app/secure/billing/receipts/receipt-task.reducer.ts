import {Action} from '@ngrx/store';
import {ReceiptActions} from './receipt.action';
import {ReceiptTask} from '../../../shared/model/billing/receipt-task.interface';

export type ReceiptTaskState = ReceiptTask;

const initialState: ReceiptTaskState = <ReceiptTaskState>{};

export function receiptTaskReducer(state = initialState, action: Action): ReceiptTaskState {
  switch (action.type) {
    case ReceiptActions.FIND_RECEIPT_TASK_BY_TASK_ID_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
