import {Action} from '@ngrx/store';
import {FeeScheduleItem} from '../../shared/model/account/fee-schedule-item.interface';
import {FeeScheduleActions} from './fee-schedule.action';

export type FeeScheduleItemListState = FeeScheduleItem[];

const initialState: FeeScheduleItemListState = <FeeScheduleItem[]>[];

export function feeScheduleItemListReducer(state = initialState, action: Action): FeeScheduleItemListState {
  switch (action.type) {
    case FeeScheduleActions.FIND_FEE_SCHEDULE_ITEMS_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
