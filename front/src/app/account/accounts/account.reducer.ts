import {Action} from '@ngrx/store';
import {AccountActions} from "./account.action";
import {Account} from "./account.interface";
import {Actor} from "../../identity/actor.interface";

export type AccountState = Account;

const initialState: AccountState = {
  id: 0,
  code: '',
  description: '',
  name: '',
  email: '',
  actor:<Actor>{},
  balance:0
};

export function accountReducer(state = initialState, action: Action): AccountState {
  switch (action.type) {
    case AccountActions.FIND_ACCOUNT_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
