import { Injectable } from '@angular/core';
import { Actions, Effect } from '@ngrx/effects';
import { from } from 'rxjs/observable/from';
import { Store } from '@ngrx/store';
import { BillingService } from '../../../../services/billing.service';
import { BillingModuleState } from '../index';
import { KnockoffActions } from './knockoff.action';

@Injectable()
export class KnockoffEffects {

    private KNOCKOFF: string[] = 'billingModuleState.knockoff'.split( '.' );

    constructor( private actions$: Actions,
        private knockoffActions: KnockoffActions,
        private billingService: BillingService,
        private store$: Store<BillingModuleState> ) {
    }
    
    @Effect() findKnockoffs$ = this.actions$
    .ofType(KnockoffActions.FIND_KNOCKOFFS)
    .map((action) => action.payload)
    .switchMap(() => this.billingService.findKnockoffs())
    .map((knockoff) => this.knockoffActions.findKnockoffsSuccess(knockoff));
    
    @Effect() findKnockoffByReferenceNo$ = this.actions$
    .ofType(KnockoffActions.FIND_KNOCKOFF_BY_REFERENCE_NO)
    .map((action) => action.payload)
    .switchMap((referenceNo) => this.billingService.findKnockoffByReferenceNo(referenceNo))
    .map((knockoff) => this.knockoffActions.findKnockoffByReferenceNoSuccess(knockoff));
    
    @Effect() saveKnockoff$ = this.actions$
    .ofType(KnockoffActions.SAVE_KNOCKOFF)
    .map((action) => action.payload)
    .switchMap((payload) => this.billingService.saveKnockoff(payload.knockoff, payload.payment))
    .map((knockoff) => this.knockoffActions.saveKnockoffSuccess(knockoff));
}