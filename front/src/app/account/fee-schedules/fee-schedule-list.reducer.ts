import {Action} from '@ngrx/store';
import {FeeSchedule} from "./fee-schedule.interface";
import {FeeScheduleActions} from "./fee-schedule.action";

export type FeeScheduleListState = FeeSchedule[];

const initialState: FeeScheduleListState = <FeeSchedule[]>[];

export function feeScheduleListReducer(state = initialState, action: Action): FeeScheduleListState {
  switch (action.type) {
    case FeeScheduleActions.FIND_FEE_SCHEDULES_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
