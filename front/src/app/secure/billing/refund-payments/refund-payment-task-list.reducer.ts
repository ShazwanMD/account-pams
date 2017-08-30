import {Action} from '@ngrx/store';
import { RefundPaymentTask } from "../../../shared/model/billing/refund-payment-task.interface";
import { RefundPaymentActions } from "./refund-payment.action";

export type RefundPaymentTaskListState = RefundPaymentTask[];

const initialState: RefundPaymentTaskListState = <RefundPaymentTask[]>[];

export function assignedRefundPaymentTaskListReducer(state = initialState, action: Action): RefundPaymentTaskListState {
  switch (action.type) {
    case RefundPaymentActions.FIND_ASSIGNED_REFUND_PAYMENT_TASKS_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}

export function pooledRefundPaymentTaskListReducer(state = initialState, action: Action): RefundPaymentTaskListState {
  switch (action.type) {
    case RefundPaymentActions.FIND_POOLED_REFUND_PAYMENT_TASKS_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
