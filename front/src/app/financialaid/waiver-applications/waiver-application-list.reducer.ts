import {Action} from '@ngrx/store';
import {WaiverApplication} from "./waiver-application.interface";
import {WaiverApplicationActions} from "./waiver-application.action";

export type WaiverApplicationListState = WaiverApplication[];

const initialState: WaiverApplicationListState = <WaiverApplication[]>[];

export function archivedWaiverApplicationListReducer(state = initialState, action: Action): WaiverApplicationListState {
  switch (action.type) {
    case WaiverApplicationActions.FIND_ARCHIVED_WAIVER_APPLICATIONS_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}

export function waiverApplicationListReducer(state = initialState, action: Action): WaiverApplicationListState {
    switch (action.type) {
      case WaiverApplicationActions.FIND_COMPLETED_WAIVER_APPLICATIONS_SUCCESS: {
        return action.payload;
      }
      default: {
        return state;
      }
    }
  }