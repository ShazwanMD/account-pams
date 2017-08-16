import {Action} from '@ngrx/store';
import {AdvancePaymentActions} from './advance-payment.action';
import {AdvancePayment} from '../../../shared/model/billing/advance-payment.interface';

export type AdvancePaymentListState = AdvancePayment[];

const initialState: AdvancePaymentListState = <AdvancePayment[]>[];

export function advancePaymentListReducer(state = initialState, action: Action): AdvancePaymentListState {
    switch (action.type) {
      case AdvancePaymentActions.FIND_UNPAID_ADVANCE_PAYMENTS_SUCCESS: {
        return action.payload;
      }
      default: {
        return state;
      }
    }
  }