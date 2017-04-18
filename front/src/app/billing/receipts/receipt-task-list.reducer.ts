import {Action} from '@ngrx/store';
import {ReceiptTask} from "./receipt-task.interface";
import {ReceiptActions} from "./receipt.action";

export type ReceiptTaskListState = ReceiptTask[];

const initialState: ReceiptTaskListState = <ReceiptTask[]>[];

export function receiptTaskListReducer(state = initialState, action: Action): ReceiptTaskListState {
  switch (action.type) {
    case ReceiptActions.FIND_ASSIGNED_RECEIPT_TASKS_SUCCESS: {
      return action.payload;
    }
    case ReceiptActions.FIND_POOLED_RECEIPT_TASKS_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
