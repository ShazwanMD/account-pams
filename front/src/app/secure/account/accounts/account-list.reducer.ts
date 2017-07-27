import {Action} from '@ngrx/store';

import * as _ from 'lodash';
import {Account} from '../../../shared/model/account/account.interface';
import {AccountActions} from './account.action';

export type AccountListState = Account[];

const initialState: AccountListState = <Account[]>[];

export function accountListReducer(state = initialState, action: Action): AccountListState {
  switch (action.type) {
    case AccountActions.FIND_ACCOUNTS_SUCCESS: {
      return action.payload;
    }
    case AccountActions.CREATE_ACCOUNT_SUCCESS: {
      return [...state, action.payload];
    }
    case AccountActions.SAVE_ACCOUNT_SUCCESS: {
      let index = _.findIndex(state, {id: action.payload.id});
      if (index >= 0) {
        return [
          ...state.slice(0, index),
          action.payload,
          ...state.slice(index + 1),
        ];
      }
      return state;
    }
    case AccountActions.REMOVE_ACCOUNT_SUCCESS: {
      return state.filter((account) => {
        return account.id !== action.payload.id;
      });
    }
    default: {
      return state;
    }
  }
}
