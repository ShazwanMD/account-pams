import {Action} from '@ngrx/store';
import {Observable} from 'rxjs/Observable';

import * as _ from 'lodash';
import {Account} from "./account.interface";
import {AccountActions} from "./account.action";
import {AccountCharge} from "./account-charge.interface";

export type AccountChargeListState = AccountCharge[];

const initialState: AccountChargeListState = <AccountCharge[]>[];

export function accountChargeListReducer(state = initialState, action: Action): AccountChargeListState {
  switch (action.type) {
    case AccountActions.FIND_ACCOUNT_CHARGES_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
