import {Injectable} from '@angular/core';
import {Actions, Effect} from '@ngrx/effects';
import {BillingService} from '../../../../services/billing.service';
import {Store} from '@ngrx/store';
import {BillingModuleState} from '../index';
import {AdvancePaymentActions} from './advance-payment.action';

@Injectable()
export class AdvancePaymentEffects {
    
    private ADVANCE_PAYMENT: string[] = 'billingModuleState.advancePayments'.split('.');
    
    constructor(private actions$: Actions,
            private advancePaymentActions: AdvancePaymentActions,
            private billingService: BillingService,
            private store$: Store<BillingModuleState>) {
    }
    
    @Effect() findUnpaidAdvancePayments$ = this.actions$
    .ofType(AdvancePaymentActions.FIND_UNPAID_ADVANCE_PAYMENTS)
    .map((action) => action.payload)
    .switchMap((account) => this.billingService.findUnpaidAdvancePayments(account))
    .map((payments) => this.advancePaymentActions.findUnpaidAdvancePaymentSuccess(payments));
        
    @Effect() findAdvancePayments$ = this.actions$
    .ofType(AdvancePaymentActions.FIND_ADVANCE_PAYMENTS)
    .map((action) => action.payload)
    .switchMap(() => this.billingService.findAdvancePayments())
    .map((payments) => this.advancePaymentActions.findAdvancePaymentsSuccess(payments));
}