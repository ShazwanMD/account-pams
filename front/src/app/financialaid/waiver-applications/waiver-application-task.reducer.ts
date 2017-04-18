import {Action} from '@ngrx/store';
import {WaiverApplicationTask} from "./waiver-application-task.interface";
import {WaiverApplicationActions} from "./waiver-application.action";

export type WaiverApplicationTaskState = WaiverApplicationTask;

const initialState: WaiverApplicationTaskState = <WaiverApplicationTaskState>{};

export function waiverApplicationTaskReducer(state = initialState, action: Action): WaiverApplicationTaskState {
  switch (action.type) {
    case WaiverApplicationActions.FIND_WAIVER_APPLICATION_TASK_BY_TASK_ID_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
