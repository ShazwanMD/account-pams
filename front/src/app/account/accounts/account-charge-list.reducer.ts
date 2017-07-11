import {Action} from '@ngrx/store';
import {Observable} from 'rxjs/Observable';

import * as _ from 'lodash';
import {AccountActions} from './account.action';
import {AccountCharge} from './account-charge.interface';

export type AccountChargeListState = AccountCharge[];

const initialState: AccountChargeListState = <AccountCharge[]>[];

export function securityAccountChargeListReducer(state = initialState, action: Action): AccountChargeListState {
  switch (action.type) {
    case AccountActions.FIND_SECURITY_ACCOUNT_CHARGES_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}

export function admissionAccountChargeListReducer(state = initialState, action: Action): AccountChargeListState {
  switch (action.type) {
    case AccountActions.FIND_ADMISSION_ACCOUNT_CHARGES_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}

export function studentAffairsAccountChargeListReducer(state = initialState, action: Action): AccountChargeListState {
  switch (action.type) {
    case AccountActions.FIND_STUDENT_AFFAIRS_ACCOUNT_CHARGES_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}

export function loanAccountChargeListReducer(state = initialState, action: Action): AccountChargeListState {
  switch (action.type) {
    case AccountActions.FIND_LOAN_ACCOUNT_CHARGES_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
