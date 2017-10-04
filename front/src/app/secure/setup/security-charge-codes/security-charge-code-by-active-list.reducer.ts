import {SecurityChargeCode} from '../../../shared/model/common/security-charge-code.interface';
import {Action} from '@ngrx/store';
import {SetupActions} from '../setup.action';

export type SecurityChargeCodeByActiveListState = SecurityChargeCode[];

const initialState: SecurityChargeCodeByActiveListState = <SecurityChargeCode[]>[];

export function securityChargeCodeByActiveListReducer(state = initialState, action: Action): SecurityChargeCodeByActiveListState {
  switch (action.type) {
    case SetupActions.FIND_SECURITY_CHARGE_CODES_BY_ACTIVE_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
