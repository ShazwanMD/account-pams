import {Action} from '@ngrx/store';
import {CommonActions} from '../common.action';
import { ProgramLevel } from '../../shared/model/common/program-level.interface';

export type ProgramLevelListState = ProgramLevel[];

const initialState: ProgramLevelListState = <ProgramLevel[]>[];

export function programLevelListReducer(state = initialState, action: Action): ProgramLevelListState {
  switch (action.type) {
    case CommonActions.FIND_PROGRAM_LEVELS_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
