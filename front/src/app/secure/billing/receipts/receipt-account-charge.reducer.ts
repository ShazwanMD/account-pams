import {Action} from '@ngrx/store';
import {ReceiptActions} from './receipt.action';
import {ReceiptAccountCharge} from '../../../shared/model/billing/receipt-account-charge.interface';

export type ReceiptAccountChargeListState = ReceiptAccountCharge[];

const initialState: ReceiptAccountChargeListState = <ReceiptAccountCharge[]>[];

export function receiptAccountChargeListReducer(state = initialState, action: Action): ReceiptAccountChargeListState {
  switch (action.type) {
    case ReceiptActions.FIND_RECEIPTS_BY_ACCOUNT_CHARGE_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
