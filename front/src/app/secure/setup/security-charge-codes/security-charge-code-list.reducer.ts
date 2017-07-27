import { SecurityChargeCode } from '../../../shared/model/common/security-charge-code.interface';
import {Action} from '@ngrx/store';
import { SetupActions } from '../setup.action';

export type SecurityChargeCodeListState = SecurityChargeCode[];

const initialState: SecurityChargeCodeListState = <SecurityChargeCode[]>[];

export function securityChargeCodeListReducer(state = initialState, action: Action): SecurityChargeCodeListState {
  switch (action.type) {
    case SetupActions.FIND_SECURITY_CHARGE_CODES_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
