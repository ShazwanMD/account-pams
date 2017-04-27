import {Injectable} from '@angular/core';
import {Effect, Actions} from '@ngrx/effects';
import {from} from "rxjs/observable/from";
import {IdentityService} from "../../services/identity.service";
import {SponsorActions} from "./sponsor.action";


@Injectable()
export class SponsorEffects {
  constructor(private actions$: Actions,
              private accountActions: SponsorActions,
              private identityService: IdentityService) {
  }

  @Effect() findSponsors$ = this.actions$
    .ofType(SponsorActions.FIND_SPONSORS)
    .switchMap(() => this.identityService.findSponsors())
    .map(accounts => this.accountActions.findSponsorsSuccess(accounts));

  @Effect() findSponsor$ = this.actions$
    .ofType(SponsorActions.FIND_SPONSOR)
    .map(action => action.payload)
    .switchMap(code => this.identityService.findSponsorByIdentityNo(code))
    .map(account => this.accountActions.findSponsorSuccess(account));

  @Effect() updateSponsor$ = this.actions$
    .ofType(SponsorActions.UPDATE_SPONSOR)
    .map(action => action.payload);
    // .switchMap(account => this.identityService.updateSponsor(account))
    // .map(account => this.accountActions.updateSponsorSuccess(account));
}
