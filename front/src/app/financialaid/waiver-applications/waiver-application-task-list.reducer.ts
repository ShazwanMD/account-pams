import {Action} from '@ngrx/store';
import {WaiverApplicationTask} from "./waiver-application-task.interface";
import {WaiverApplicationActions} from "./waiver-application.action";

export type WaiverApplicationTaskListState = WaiverApplicationTask[];

const initialState: WaiverApplicationTaskListState = <WaiverApplicationTask[]>[];

export function waiverApplicationTaskListReducer(state = initialState, action: Action): WaiverApplicationTaskListState {
  switch (action.type) {
    case WaiverApplicationActions.FIND_ASSIGNED_WAIVER_APPLICATION_TASKS_SUCCESS: {
      return action.payload;
    }
    case WaiverApplicationActions.FIND_POOLED_WAIVER_APPLICATION_TASKS_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
