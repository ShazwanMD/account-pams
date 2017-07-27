import {Action} from '@ngrx/store';
import {SetupActions} from '../setup.action';
import {ResidencyCode} from '../../../shared/model/common/residency-code.interface';

export type ResidencyCodeListState = ResidencyCode[];

const initialState: ResidencyCodeListState = <ResidencyCode[]>[];

export function residencyCodeListReducer(state = initialState, action: Action): ResidencyCodeListState {
  switch (action.type) {
    case SetupActions.FIND_RESIDENCY_CODES_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
