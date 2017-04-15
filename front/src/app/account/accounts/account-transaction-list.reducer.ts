import {Action} from '@ngrx/store';
import {Observable} from 'rxjs/Observable';

import * as _ from 'lodash';
import {Account} from "./account.interface";
import {AccountActions} from "./account.action";
import {AccountTransaction} from "./account-transaction.interface";

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
