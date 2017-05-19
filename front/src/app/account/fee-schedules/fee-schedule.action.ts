import {Injectable} from '@angular/core';
import {Action} from '@ngrx/store';

@Injectable()
export class FeeScheduleActions {

  static FIND_FEE_SCHEDULES = '[FeeSchedule] Find FeeSchedules';
  findFeeSchedules(): Action {
    return {
      type: FeeScheduleActions.FIND_FEE_SCHEDULES
    };
  }

  static FIND_FEE_SCHEDULES_SUCCESS = '[FeeSchedule] Find FeeSchedules Success';
  findFeeSchedulesSuccess(feeSchedules): Action {
    console.log("findFeeSchedulesSuccess");
    console.log("feeSchedules: " + feeSchedules.length);
    return {
      type: FeeScheduleActions.FIND_FEE_SCHEDULES_SUCCESS,
      payload: feeSchedules
    };
  }

  static FIND_FEE_SCHEDULE_BY_CODE = '[FeeSchedule] Find FeeSchedule By Code';
  findFeeScheduleByCode(code): Action {
    return {
      type: FeeScheduleActions.FIND_FEE_SCHEDULE_BY_CODE,
      payload: code
    };
  }

  static FIND_FEE_SCHEDULE_BY_CODE_SUCCESS = '[FeeSchedule] Find FeeSchedule By Code Success';
  findFeeScheduleByCodeSuccess(feeSchedule): Action {
    console.log("findFeeScheduleByCodeSuccess");
    return {
      type: FeeScheduleActions.FIND_FEE_SCHEDULE_BY_CODE_SUCCESS,
      payload: feeSchedule
    };
  }

  static FIND_FEE_SCHEDULE_ITEMS = '[FeeSchedule] Find FeeSchedule Items';
  findFeeScheduleItems(feeSchedule): Action {
    console.log("findFeeScheduleItems");
    return {
      type: FeeScheduleActions.FIND_FEE_SCHEDULE_ITEMS,
      payload: feeSchedule
    };
  }

  static FIND_FEE_SCHEDULE_ITEMS_SUCCESS = '[FeeSchedule] Find FeeSchedule Items Success';
  findFeeScheduleItemsSuccess(feeScheduleItems): Action {
    console.log("findFeeScheduleItemsSuccess");
    return {
      type: FeeScheduleActions.FIND_FEE_SCHEDULE_ITEMS_SUCCESS,
      payload: feeScheduleItems
    };
  }

  static SAVE_FEE_SCHEDULE = '[FeeSchedule] Save FeeSchedule';
  saveFeeSchedule(feeSchedule): Action {
    console.log("saveFeeSchedule");
    return {
      type: FeeScheduleActions.SAVE_FEE_SCHEDULE,
      payload: feeSchedule
    };
  }

  static SAVE_FEE_SCHEDULE_SUCCESS = '[FeeSchedule] Save FeeSchedule Success';
  saveFeeScheduleSuccess(feeSchedule): Action {
    return {
      type: FeeScheduleActions.SAVE_FEE_SCHEDULE_SUCCESS,
      payload: feeSchedule
    };
  }

  static UPDATE_FEE_SCHEDULE = '[FeeSchedule] Update FeeSchedule';
  updateFeeSchedule(feeSchedule): Action {
    return {
      type: FeeScheduleActions.UPDATE_FEE_SCHEDULE,
      payload: feeSchedule
    };
  }

  static UPDATE_FEE_SCHEDULE_SUCCESS = '[FeeSchedule] Update FeeSchedule Success';
  updateFeeScheduleSuccess(feeSchedule): Action {
    return {
      type: FeeScheduleActions.UPDATE_FEE_SCHEDULE_SUCCESS,
      payload: feeSchedule
    };
  }

  static REMOVE_FEE_SCHEDULE = '[FeeSchedule] Remove FeeSchedule';
  removeFeeSchedule(feeSchedule): Action {
    return {
      type: FeeScheduleActions.REMOVE_FEE_SCHEDULE,
      payload: feeSchedule
    };
  }

  static REMOVE_FEE_SCHEDULE_SUCCESS = '[FeeSchedule] Remove FeeSchedule Success';
  removeFeeScheduleSuccess(feeSchedule): Action {
    return {
      type: FeeScheduleActions.REMOVE_FEE_SCHEDULE_SUCCESS,
      payload: feeSchedule
    };
  }

  static ADD_FEE_SCHEDULE_ITEM = '[FeeSchedule] Add FeeSchedule Item';

  addFeeScheduleItem(feeSchedule, item): Action {
    return {
      type: FeeScheduleActions.ADD_FEE_SCHEDULE_ITEM,
      payload: {feeSchedule:feeSchedule, item:item}
    };
  }

  static ADD_FEE_SCHEDULE_ITEM_SUCCESS = '[FeeSchedule] Add FeeSchedule Item Success';

  addFeeScheduleItemSuccess(message): Action {
    return {
      type: FeeScheduleActions.ADD_FEE_SCHEDULE_ITEM_SUCCESS,
      payload: message
    };
  }

  static DELETE_FEE_SCHEDULE_ITEM = '[FeeSchedule] Delete FeeSchedule Item';

  deleteFeeScheduleItem(feeSchedule, item): Action {
    return {
      type: FeeScheduleActions.DELETE_FEE_SCHEDULE_ITEM,
      payload: {feeSchedule:feeSchedule, item:item}
    };
  }

  static DELETE_FEE_SCHEDULE_ITEM_SUCCESS = '[FeeSchedule] Add FeeSchedule Item Success';

  deleteFeeScheduleItemSuccess(message): Action {
    return {
      type: FeeScheduleActions.DELETE_FEE_SCHEDULE_ITEM_SUCCESS,
      payload: message
    };
  }

  static UPDATE_FEE_SCHEDULE_ITEM = '[FeeSchedule] Update FeeSchedule Item';

  updateFeeScheduleItem(feeSchedule, item): Action {
    return {
      type: FeeScheduleActions.UPDATE_FEE_SCHEDULE_ITEM,
      payload: {feeSchedule:feeSchedule, item:item}
    };
  }

  static UPDATE_FEE_SCHEDULE_ITEM_SUCCESS = '[FeeSchedule] Update FeeSchedule Item Success';

  updateFeeScheduleItemSuccess(message): Action {
    return {
      type: FeeScheduleActions.UPDATE_FEE_SCHEDULE_ITEM_SUCCESS,
      payload: message
    };
  }


}
