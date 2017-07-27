import {Action} from '@ngrx/store';
import {WaiverApplicationActions} from './waiver-application.action';
import {WaiverApplicationTask} from '../../../shared/model/financialaid/waiver-application-task.interface';

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
