import {Action} from '@ngrx/store';
import {KnockoffActions} from './knockoff.action';
import {Knockoff} from '../../../shared/model/billing/knockoff.interface';

export type KnockoffState = Knockoff;

const initialState: KnockoffState = <KnockoffState>{};

export function knockoffReducer(state = initialState, action: Action): KnockoffState {
  switch (action.type) {
    case KnockoffActions.FIND_KNOCKOFF_BY_REFERENCE_NO_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
