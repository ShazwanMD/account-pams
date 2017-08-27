import {Action} from '@ngrx/store';
import {KnockoffTask} from '../../../shared/model/billing/knockoff-task.interface';
import {KnockoffActions} from './knockoff.action';

export type KnockoffTaskListState = KnockoffTask[];

const initialState: KnockoffTaskListState = <KnockoffTask[]>[];

export function assignedKnockoffTaskListReducer(state = initialState, action: Action): KnockoffTaskListState {
  switch (action.type) {
    case KnockoffActions.FIND_ASSIGNED_KNOCKOFF_TASKS_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}

export function pooledKnockoffTaskListReducer(state = initialState, action: Action): KnockoffTaskListState {
  switch (action.type) {
    case KnockoffActions.FIND_POOLED_KNOCKOFF_TASKS_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
