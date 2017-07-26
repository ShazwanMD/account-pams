import {Action} from '@ngrx/store';
import {WaiverApplicationActions} from './waiver-application.action';
import {WaiverApplicationTask} from '../../shared/model/financialaid/waiver-application-task.interface';

export type WaiverApplicationTaskListState = WaiverApplicationTask[];

const initialState: WaiverApplicationTaskListState = <WaiverApplicationTask[]>[];

export function assignedWaiverApplicationTaskListReducer(state = initialState, action: Action): WaiverApplicationTaskListState {
  switch (action.type) {
    case WaiverApplicationActions.FIND_ASSIGNED_WAIVER_APPLICATION_TASKS_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}

export function pooledWaiverApplicationTaskListReducer(state = initialState, action: Action): WaiverApplicationTaskListState {
  switch (action.type) {
    case WaiverApplicationActions.FIND_POOLED_WAIVER_APPLICATION_TASKS_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
