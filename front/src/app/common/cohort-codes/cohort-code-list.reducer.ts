import {Action} from '@ngrx/store';
import {GraduateCentre} from "./graduate-centre.interface";
import {CommonActions} from "../common.action";

export type GraduateCentreListState = GraduateCentre[];

const initialState: GraduateCentreListState = <GraduateCentre[]>[];

export function graduateCentreListReducer(state = initialState, action: Action): GraduateCentreListState {
  switch (action.type) {
    case CommonActions.FIND_GRADUATE_CENTRES_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
