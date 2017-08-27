import {Action} from '@ngrx/store';
import {WaiverApplicationTask} from '../../../shared/model/financialaid/waiver-application-task.interface';
import { WaiverFinanceApplicationTask } from "../../../shared/model/billing/waiver-finance-application-task.interface";
import { WaiverFinanceApplicationActions } from "./waiver-finance-application.action";

export type WaiverFinanceApplicationTaskState = WaiverFinanceApplicationTask;

const initialState: WaiverFinanceApplicationTaskState = <WaiverFinanceApplicationTaskState>{};

export function waiverFinanceApplicationTaskReducer(state = initialState, action: Action): WaiverFinanceApplicationTaskState {
  switch (action.type) {
    case WaiverFinanceApplicationActions.FIND_WAIVER_FINANCE_APPLICATION_TASK_BY_TASK_ID_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
