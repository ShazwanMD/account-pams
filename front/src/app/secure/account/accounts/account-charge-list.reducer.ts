import {Action} from '@ngrx/store';
import {AccountActions} from './account.action';
import {AccountCharge} from '../../../shared/model/account/account-charge.interface';
import { ReceiptActions } from '../../billing/receipts/receipt.action';

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

export function accountChargeListReducer(state = initialState, action: Action): AccountChargeListState {
  switch (action.type) {
//    case ReceiptActions.FIND_COMPLETED_ACCOUNT_CHARGES_SUCCESS: {
//      return action.payload;
//    }
    case AccountActions.FIND_UNPAID_ACCOUNT_CHARGES_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
