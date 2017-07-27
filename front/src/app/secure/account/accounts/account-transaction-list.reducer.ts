import {Action} from '@ngrx/store';

import {AccountActions} from './account.action';
import {AccountTransaction} from '../../../shared/model/account/account-transaction.interface';

export type AccountTransactionListState = AccountTransaction[];

const initialState: AccountTransactionListState = <AccountTransaction[]>[];

export function accountTransactionListReducer(state = initialState, action: Action): AccountTransactionListState {
  switch (action.type) {
    case AccountActions.FIND_ACCOUNT_TRANSACTIONS_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
