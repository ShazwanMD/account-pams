import {Injectable} from '@angular/core';
import {Effect, Actions} from '@ngrx/effects';
import {from} from "rxjs/observable/from";
import {IdentityService} from "../../services/identity.service";
import {ActorActions} from "./actor.action";


@Injectable()
export class ActorEffects {
  constructor(private actions$: Actions,
              private actorActions: ActorActions,
              private identityService: IdentityService) {
  }

  @Effect() findActors$ = this.actions$
    .ofType(ActorActions.FIND_ACTORS)
    .switchMap(() => this.identityService.findActors())
    .map(actors => this.actorActions.findActorsSuccess(actors));

  @Effect() findActor$ = this.actions$
    .ofType(ActorActions.FIND_ACTOR)
    .map(action => action.payload)
    .switchMap(code => this.identityService.findActorByIdentityNo(code))
    .map(actor => this.actorActions.findActorSuccess(actor));

  @Effect() saveActor$ = this.actions$
    .ofType(ActorActions.SAVE_ACTOR)
    .map(action => action.payload);
    // .switchMap(actor => this.identityService.saveActor(actor))
    // .map(actor => this.actorActions.saveActorSuccess(actor));

  @Effect() updateActor$ = this.actions$
    .ofType(ActorActions.UPDATE_ACTOR)
    .map(action => action.payload);
    // .switchMap(actor => this.identityService.updateActor(actor))
    // .map(actor => this.actorActions.updateActorSuccess(actor));
}
