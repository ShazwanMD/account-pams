import {Action} from '@ngrx/store';
import {Account} from '../../../shared/model/account/account.interface';
import {AccountActions} from './account.action';

export type AccountSponsorListState = Account[];

const initialState: AccountSponsorListState = <Account[]>[];

export function accountSponsorListReducer(state = initialState, action: Action): AccountSponsorListState {
  switch (action.type) {
    case AccountActions.FIND_ACCOUNTS_BY_ACTOR_SPONSOR_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
