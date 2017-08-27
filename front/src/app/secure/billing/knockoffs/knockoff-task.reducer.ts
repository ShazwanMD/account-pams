import {Action} from '@ngrx/store';
import {KnockoffTask} from '../../../shared/model/billing/knockoff-task.interface';
import {KnockoffActions} from './knockoff.action';

export type KnockoffTaskState = KnockoffTask;

const initialState: KnockoffTaskState = <KnockoffTaskState>{};

export function knockoffTaskReducer(state = initialState, action: Action): KnockoffTaskState {
  switch (action.type) {
    case KnockoffActions.FIND_KNOCKOFF_TASK_BY_TASK_ID_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
