import {Action} from '@ngrx/store';
import {AccountActions} from './account.action';
import {Account} from '../../../shared/model/account/account.interface';

export type AccountState = Account;

const initialState: AccountState = <Account>{};

export function accountReducer(state = initialState, action: Action): AccountState {
  switch (action.type) {
    case AccountActions.FIND_ACCOUNT_BY_CODE_SUCCESS: {
      return action.payload;
    }
    case AccountActions.FIND_INVOICES_BY_ACCOUNT_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
