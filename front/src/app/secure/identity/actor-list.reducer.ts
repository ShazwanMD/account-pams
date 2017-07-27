import {Action} from '@ngrx/store';

import * as _ from 'lodash';
import {ActorActions} from './actor.action';
import {Actor} from '../../shared/model/identity/actor.interface';

export type ActorListState = Actor[];

const initialState: ActorListState = <Actor[]>[];

export function actorListReducer(state = initialState, action: Action): ActorListState {
  switch (action.type) {
    case ActorActions.FIND_ACTORS_SUCCESS: {
      return action.payload;
    }

    case ActorActions.CREATE_ACTOR_SUCCESS: {
      return [...state, action.payload];
    }

    case ActorActions.SAVE_ACTOR_SUCCESS: {
      let index = _.findIndex(state, {id: action.payload.id});
      if (index >= 0) {
        return [
          ...state.slice(0, index),
          action.payload,
          ...state.slice(index + 1),
        ];
      }
      return state;
    }
    case ActorActions.REMOVE_ACTOR_SUCCESS: {
      return state.filter((actor) => {
        return actor.id !== action.payload.id;
      });
    }
    default: {
      return state;
    }
  }
}
