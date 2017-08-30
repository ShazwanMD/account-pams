import {Action} from '@ngrx/store';

import * as _ from 'lodash';
import { RefundPayment } from "../../../shared/model/billing/refund-payment.interface";
import { RefundPaymentActions } from "./refund-payment.action";

export type RefundPaymentListState = RefundPayment[];

const initialState: RefundPaymentListState = <RefundPayment[]>[];

export function refundPaymentListReducer(state = initialState, action: Action): RefundPaymentListState {
  switch (action.type) {
    case RefundPaymentActions.FIND_REFUND_PAYMENTS_SUCCESS: {
      return action.payload;
    }
    case RefundPaymentActions.FIND_COMPLETED_REFUND_PAYMENTS_SUCCESS: {
        return action.payload;
      }
    default: {
      return state;
    }
  }
}

export function archivedRefundPaymentListReducer(state = initialState, action: Action): RefundPaymentListState {
    switch (action.type) {
      case RefundPaymentActions.FIND_ARCHIVED_REFUND_PAYMENTS_SUCCESS: {
        return action.payload;
      }
      default: {
        return state;
      }
    }
  }
