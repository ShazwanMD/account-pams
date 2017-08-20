import {Action} from '@ngrx/store';

import * as _ from 'lodash';
import {Knockoff} from '../../../shared/model/billing/knockoff.interface';
import {KnockoffActions} from './knockoff.action';

export type KnockoffListState = Knockoff[];

const initialState: KnockoffListState = <Knockoff[]>[];

export function knockoffListReducer(state = initialState, action: Action): KnockoffListState {
  switch (action.type) {
    case KnockoffActions.FIND_KNOCKOFFS_SUCCESS: {
      return action.payload;
    }
    case KnockoffActions.SAVE_KNOCKOFF_SUCCESS: {
      let index = _.findIndex(state, {id: action.payload.id});
      if (index >= 0) {
        return [
          ...state.slice(0, index),
          action.payload,
          ...state.slice(index + 1),
        ];
      }
      return state;
    }
    default: {
      return state;
    }
  }
}
