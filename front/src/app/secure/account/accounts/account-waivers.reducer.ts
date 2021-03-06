import {Action} from '@ngrx/store';
import {AccountActions} from './account.action';
import {AccountWaiver} from '../../../shared/model/account/account-waiver.interface';

export type AccountWaiverState = AccountWaiver[];

const initialState: AccountWaiverState = <AccountWaiver[]>[];

export function accountWaiverReducer(state = initialState, action: Action): AccountWaiverState {
  switch (action.type) {
    case AccountActions.FIND_ACCOUNT_WAIVERS_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
