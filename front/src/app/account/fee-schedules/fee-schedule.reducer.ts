import {Action} from '@ngrx/store';
import {FeeSchedule} from "./fee-schedule.interface";
import {FeeScheduleActions} from "./fee-schedule.action";

export type FeeScheduleState = FeeSchedule;

const initialState: FeeScheduleState = <FeeSchedule>{};

export function feeScheduleReducer(state = initialState, action: Action): FeeScheduleState {
  switch (action.type) {
    case FeeScheduleActions.FIND_FEE_SCHEDULE_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
