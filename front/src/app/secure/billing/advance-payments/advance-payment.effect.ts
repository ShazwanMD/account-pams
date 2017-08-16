import {Injectable} from '@angular/core';
import {Actions, Effect} from '@ngrx/effects';
import {BillingService} from '../../../../services/billing.service';
import {Store} from '@ngrx/store';

@Injectable()
export class AdvancePaymentEffects {
    
    constructor(private actions$: Actions,
            private advancePaymentActions: AdvancePaymentActions,
            private billingService: BillingService,
            private store$: Store<BillingModuleState>) {
    }
    
    @Effect() findUnpaidAdvancePayments$ = this.actions$
    .ofType(InvoiceActions.FIND_UNPAID_INVOICES)
    .map((action) => action.payload)
    .switchMap((account) => this.billingService.findUnpaidAdvancePayments(account))
    .map((payments) => this.advancePaymentActions.findUnpaidAdvancePaymentSuccess(payments));
}