import {Action} from '@ngrx/store';
import {KnockoffActions} from './knockoff.action';
import {KnockoffAccountCharge} from '../../../shared/model/billing/knockoff-account-charge.interface';

export type KnockoffAccountChargeListState = KnockoffAccountCharge[];

const initialState: KnockoffAccountChargeListState = <KnockoffAccountCharge[]>[];

export function knockoffAccountChargeListReducer(state = initialState, action: Action): KnockoffAccountChargeListState {
  switch (action.type) {
    case KnockoffActions.FIND_ACCOUNT_CHARGE_BY_KNOCKOFF_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
