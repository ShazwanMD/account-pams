import {Action} from '@ngrx/store';
import {AccountActions} from './account.action';
import {AccountActivityCharge} from '../../../shared/model/account/account-activity-charge.interface';
import { ReceiptActions } from '../../billing/receipts/receipt.action';

export type AccountChargeActivityListState = AccountActivityCharge[];

const initialState: AccountChargeActivityListState = <AccountActivityCharge[]>[];

export function accountChargeActivityListReducer(state = initialState, action: Action): AccountChargeActivityListState {
  switch (action.type) {
    case AccountActions.FIND_ACCOUNT_CHARGES_ACTIVITIES_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}