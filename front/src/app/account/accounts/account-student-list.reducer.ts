import {Action} from '@ngrx/store';
import {Observable} from 'rxjs/Observable';
import {Account} from "./account.interface";
import {AccountActions} from "./account.action";

export type AccountStudentListState = Account[];

const initialState: AccountStudentListState = <Account[]>[];

export function accountStudentListReducer(state = initialState, action: Action): AccountStudentListState {
  switch (action.type) {
  case AccountActions.FIND_ACCOUNTS_BY_ACTOR_SUCCESS: {
      return action.payload;
    }
  default: {
      return state;
    }
  }
}