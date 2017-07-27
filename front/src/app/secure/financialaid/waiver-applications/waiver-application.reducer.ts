import {Action} from '@ngrx/store';
import {WaiverApplicationActions} from './waiver-application.action';
import {WaiverApplication} from '../../../shared/model/financialaid/waiver-application.interface';

export type WaiverApplicationState = WaiverApplication;

const initialState: WaiverApplicationState = <WaiverApplicationState>{};

export function waiverApplicationReducer(state = initialState, action: Action): WaiverApplicationState {
  switch (action.type) {
    case WaiverApplicationActions.FIND_WAIVER_APPLICATION_BY_ID_SUCCESS: {
      return action.payload;
    }
    case WaiverApplicationActions.FIND_WAIVER_APPLICATION_BY_REFERENCE_NO_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
