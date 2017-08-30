import {Action} from '@ngrx/store';
import { RefundPaymentTask } from "../../../shared/model/billing/refund-payment-task.interface";
import { RefundPaymentActions } from "./refund-payment.action";

export type RefundPaymentTaskState = RefundPaymentTask;

const initialState: RefundPaymentTaskState = <RefundPaymentTaskState>{};

export function refundPaymentTaskReducer(state = initialState, action: Action): RefundPaymentTaskState {
  switch (action.type) {
    case RefundPaymentActions.FIND_REFUND_PAYMENT_TASK_BY_TASK_ID_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
