import {Action} from '@ngrx/store';
import {ReceiptTask} from "./receipt-task.interface";
import {ReceiptActions} from "./receipt.action";

export type ReceiptTaskListState = ReceiptTask[];

const initialState: ReceiptTaskListState = <ReceiptTask[]>[];

export function assignedReceiptTaskListReducer(state = initialState, action: Action): ReceiptTaskListState {
  switch (action.type) {
    case ReceiptActions.FIND_ASSIGNED_RECEIPT_TASKS_SUCCESS: {
      return action.payload;
    }
  }
}

export function pooledReceiptTaskListReducer(state = initialState, action: Action): ReceiptTaskListState {
  switch (action.type) {
    case ReceiptActions.FIND_POOLED_RECEIPT_TASKS_SUCCESS: {
      return action.payload;
    }
  }
}
