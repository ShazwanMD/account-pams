import {Action} from '@ngrx/store';
import {ActorActions} from './actor.action';
import {Actor} from '../shared/model/identity/actor.interface';

export type ActorState = Actor;

const initialState: ActorState = <Actor>{};

export function actorReducer(state = initialState, action: Action): ActorState {
  switch (action.type) {
    case ActorActions.FIND_ACTOR_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
