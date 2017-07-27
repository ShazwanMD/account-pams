import {Action} from '@ngrx/store';
import {ReceiptActions} from './receipt.action';
import {ReceiptTask} from '../../../shared/model/billing/receipt-task.interface';

export type ReceiptTaskListState = ReceiptTask[];

const initialState: ReceiptTaskListState = <ReceiptTask[]>[];

export function assignedReceiptTaskListReducer(state = initialState, action: Action): ReceiptTaskListState {
  switch (action.type) {
    case ReceiptActions.FIND_ASSIGNED_RECEIPT_TASKS_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}

export function pooledReceiptTaskListReducer(state = initialState, action: Action): ReceiptTaskListState {
  switch (action.type) {
    case ReceiptActions.FIND_POOLED_RECEIPT_TASKS_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
