import {Action} from '@ngrx/store';
import { WaiverFinanceApplicationTask } from "../../../shared/model/billing/waiver-finance-application-task.interface";
import { WaiverFinanceApplicationActions } from "./waiver-finance-application.action";

export type WaiverFinanceApplicationTaskListState = WaiverFinanceApplicationTask[];

const initialState: WaiverFinanceApplicationTaskListState = <WaiverFinanceApplicationTask[]>[];

export function assignedWaiverFinanceApplicationTaskListReducer(state = initialState, action: Action): WaiverFinanceApplicationTaskListState {
  switch (action.type) {
    case WaiverFinanceApplicationActions.FIND_ASSIGNED_WAIVER_FINANCE_APPLICATION_TASKS_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}

export function pooledWaiverFinanceApplicationTaskListReducer(state = initialState, action: Action): WaiverFinanceApplicationTaskListState {
  switch (action.type) {
    case WaiverFinanceApplicationActions.FIND_POOLED_WAIVER_FINANCE_APPLICATION_TASKS_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
