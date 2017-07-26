import { ResidencyCode } from '../../common/residency-codes/residency-code.interface';
import {Action} from '@ngrx/store';
import {SetupActions} from '../setup.action';

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
