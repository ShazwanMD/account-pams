import {Action} from '@ngrx/store';
import { RefundPayment } from "../../../shared/model/billing/refund-payment.interface";
import { RefundPaymentActions } from "./refund-payment.action";

export type RefundPaymentState = RefundPayment;

const initialState: RefundPaymentState = <RefundPaymentState>{};

export function refundPaymentReducer(state = initialState, action: Action): RefundPaymentState {
  switch (action.type) {
    case RefundPaymentActions.FIND_REFUND_PAYMENT_BY_REFERENCE_NO_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
