import {Injectable} from '@angular/core';
import {Action} from '@ngrx/store';

@Injectable()
export class ActorActions {

  static FIND_ACTORS = '[Actor] Find Actors';
  findActors(): Action {
    return {
      type: ActorActions.FIND_ACTORS
    };
  }

  static FIND_ACTORS_SUCCESS = '[Actor] Find Actors Success';
  findActorsSuccess(actors): Action {
    console.log("findActorsSuccess");
    console.log("accounts: " + actors.length);
    return {
      type: ActorActions.FIND_ACTORS_SUCCESS,
      payload: actors
    };
  }

  static FIND_ACTOR = '[Actor] Find Actor';
  findActor(identityNo): Action {
    return {
      type: ActorActions.FIND_ACTOR,
      payload: identityNo
    };
  }

  static FIND_ACTOR_SUCCESS = '[Actor] Find Actor Success';
  findActorSuccess(actor): Action {
    console.log("findActorSuccess");
    return {
      type: ActorActions.FIND_ACTOR_SUCCESS,
      payload: actor
    };
  }

  static RESET_ACTOR = '[Actor] Reset Blank Actor';
  resetActor(): Action {
    return {
      type: ActorActions.RESET_ACTOR
    };
  }

  static SAVE_ACTOR = '[Actor] Save Actor';
  saveActor(account): Action {
    return {
      type: ActorActions.SAVE_ACTOR,
      payload: account
    };
  }

  static SAVE_ACTOR_SUCCESS = '[Actor] Save Actor Success';
  saveActorSuccess(account): Action {
    return {
      type: ActorActions.SAVE_ACTOR_SUCCESS,
      payload: account
    };
  }

  static UPDATE_ACTOR = '[Actor] Update Actor';
  updateActor(account): Action {
    return {
      type: ActorActions.UPDATE_ACTOR,
      payload: account
    };
  }

  static UPDATE_ACTOR_SUCCESS = '[Actor] Update Actor Success';
  updateActorSuccess(account): Action {
    return {
      type: ActorActions.UPDATE_ACTOR_SUCCESS,
      payload: account
    };
  }

  static CREATE_ACTOR = '[Actor] Create Actor';
  createActor(account): Action {
    return {
      type: ActorActions.CREATE_ACTOR,
      payload: account
    };
  }

  static CREATE_ACTOR_SUCCESS = '[Actor] Create Actor Success';
  createActorSuccess(account): Action {
    return {
      type: ActorActions.CREATE_ACTOR_SUCCESS,
      payload: account
    };
  }

  static REMOVE_ACTOR = '[Actor] Remove Actor';
  removeActor(account): Action {
    return {
      type: ActorActions.REMOVE_ACTOR,
      payload: account
    };
  }

  static REMOVE_ACTOR_SUCCESS = '[Actor] Remove Actor Success';
  removeActorSuccess(account): Action {
    return {
      type: ActorActions.REMOVE_ACTOR_SUCCESS,
      payload: account
    };
  }
}
