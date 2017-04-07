import {Action} from '@ngrx/store';
import {AccountActions} from "./account.action";
import {Account} from "./account.interface";
import {Actor} from "../../identity/actor.interface";

export type AccountState = Account;

const initialState: AccountState = {
  id: 0,
  code: '',
  name: '',
  email: '',
  actor:<Actor>{},
  balanceAmount:0
};

export function accountReducer(state = initialState, action: Action): AccountState {
  console.log("action: " + action.type);
  switch (action.type) {
    case AccountActions.RESET_ACCOUNT: {
      return initialState;
    }
    case AccountActions.FIND_ACCOUNT_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
