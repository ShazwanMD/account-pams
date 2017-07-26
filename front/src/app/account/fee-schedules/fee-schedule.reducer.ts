import {Action} from '@ngrx/store';
import {FeeScheduleActions} from './fee-schedule.action';
import {FeeSchedule} from '../../shared/model/account/fee-schedule.interface';

export type FeeScheduleState = FeeSchedule;

const initialState: FeeScheduleState = <FeeSchedule>{};

export function feeScheduleReducer(state = initialState, action: Action): FeeScheduleState {
  switch (action.type) {
    case FeeScheduleActions.FIND_FEE_SCHEDULE_BY_CODE_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
