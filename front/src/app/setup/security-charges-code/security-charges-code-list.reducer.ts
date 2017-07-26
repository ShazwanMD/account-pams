import { SecurityChargesCode } from '../../common/security-charges-code/security-charges-code.interface';
import {Action} from '@ngrx/store';
import { SetupActions } from '../setup.action';

export type SecurityChargesCodeListState = SecurityChargesCode[];

const initialState: SecurityChargesCodeListState = <SecurityChargesCode[]>[];

export function securityChargesCodeListReducer(state = initialState, action: Action): SecurityChargesCodeListState {
  switch (action.type) {
    case SetupActions.FIND_SECURITY_CHARGES_CODES_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
