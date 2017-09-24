import {Action} from '@ngrx/store';
import {AccountActions} from './account.action';
import {AccountActivity} from '../../../shared/model/account/account-activity.interface';

export type AccountActivityListState = AccountActivity[];

const initialState: AccountActivityListState = <AccountActivity[]>[];

export function accountActivityListReducer(state = initialState, action: Action): AccountActivityListState {
  switch (action.type) {
    case AccountActions.FIND_ACCOUNT_ACTIVITIES_SUCCESS: {
      return action.payload;
    }
    case AccountActions.FIND_ACCOUNT_ACTIVITIES_BY_SESSION_SUCCESS: {
        return action.payload;
      }
    default: {
      return state;
    }
  }
}
