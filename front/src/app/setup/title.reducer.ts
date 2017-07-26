import {Action} from '@ngrx/store';
import {SetupActions} from './setup.action';

export type TitleState = string;

const initialState: TitleState = 'Setup';

export function titleReducer(state = initialState, action: Action): TitleState {
  switch (action.type) {
    case SetupActions.CHANGE_TITLE_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
