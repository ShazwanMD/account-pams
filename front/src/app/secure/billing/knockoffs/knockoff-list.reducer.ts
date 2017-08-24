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
    case KnockoffActions.FIND_COMPLETED_KNOCKOFFS_SUCCESS: {
        return action.payload;
      }
    default: {
      return state;
    }
  }
}

export function archivedKnockoffListReducer(state = initialState, action: Action): KnockoffListState {
    switch (action.type) {
      case KnockoffActions.FIND_ARCHIVED_KNOCKOFFS_SUCCESS: {
        return action.payload;
      }
      default: {
        return state;
      }
    }
  }
