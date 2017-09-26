import { Injectable } from '@angular/core';
import { Actions, Effect } from '@ngrx/effects';
import { from } from 'rxjs/observable/from';
import { Store } from '@ngrx/store';
import { BillingService } from '../../../../services/billing.service';
import { BillingModuleState } from '../index';
import { KnockoffActions } from './knockoff.action';

@Injectable()
export class KnockoffEffects {

    //private KNOCKOFF: string[] = 'billingModuleState.knockoffs'.split( '.' );
    //private KNOCKOFF_TASK: string[] = 'billingModuleState.knockoffTask'.split('.');

    constructor( private actions$: Actions,
        private knockoffActions: KnockoffActions,
        private billingService: BillingService,
        private store$: Store<BillingModuleState> ) {
    }

    @Effect() findKnockoffs$ = this.actions$
        .ofType( KnockoffActions.FIND_KNOCKOFFS )
        .map(( action ) => action.payload )
        .switchMap(() => this.billingService.findKnockoffs() )
        .map(( knockoff ) => this.knockoffActions.findKnockoffsSuccess( knockoff ) );

    @Effect() findKnockoffByReferenceNo$ = this.actions$
        .ofType( KnockoffActions.FIND_KNOCKOFF_BY_REFERENCE_NO )
        .map(( action ) => action.payload )
        .switchMap(( referenceNo ) => this.billingService.findKnockoffByReferenceNo( referenceNo ) )
        .map(( knockoff ) => this.knockoffActions.findKnockoffByReferenceNoSuccess( knockoff ) );

    @Effect() findCompletedKnockoffs$ = this.actions$
        .ofType( KnockoffActions.FIND_COMPLETED_KNOCKOFFS )
        .switchMap(() => this.billingService.findCompletedKnockoffs() )
        .map(( knockoffs ) => this.knockoffActions.findCompletedKnockoffsSuccess( knockoffs ) );

    @Effect() findArchivedknockoffs$ = this.actions$
        .ofType( KnockoffActions.FIND_ARCHIVED_KNOCKOFFS )
        .switchMap(() => this.billingService.findArchivedknockoffs() )
        .map(( knockoffs ) => this.knockoffActions.findArchivedknockoffsSuccess( knockoffs ) );

    @Effect() findAssignedKnockoffTasks$ = this.actions$
        .ofType( KnockoffActions.FIND_ASSIGNED_KNOCKOFF_TASKS )
        .switchMap(() => this.billingService.findAssignedKnockoffTasks() )
        .map(( knockoffs ) => this.knockoffActions.findAssignedKnockoffTasksSuccess( knockoffs ) );


    @Effect() findPooledKnockoffTasks$ = this.actions$
        .ofType( KnockoffActions.FIND_POOLED_KNOCKOFF_TASKS )
        .switchMap(() => this.billingService.findPooledKnockoffTasks() )
        .map(( knockoffs ) => this.knockoffActions.findPooledKnockoffTasksSuccess( knockoffs ) );

    @Effect() findKnockoffTaskByTaskId$ = this.actions$
        .ofType( KnockoffActions.FIND_KNOCKOFF_TASK_BY_TASK_ID )
        .map(( action ) => action.payload )
        .switchMap(( taskId ) => this.billingService.findKnockoffTaskByTaskId( taskId ) )
        .map(( task ) => this.knockoffActions.findKnockoffTaskByTaskIdSuccess( task ) );

    @Effect() findKnockoffsByInvoice$ = this.actions$
        .ofType( KnockoffActions.FIND_INVOICE_BY_KNOCKOFF )
        .map(( action ) => action.payload )
        .switchMap(( knockoff ) => this.billingService.findKnockoffsByInvoice( knockoff ) )
        .map(( knockoff ) => this.knockoffActions.findKnockoffsByInvoiceSuccess( knockoff ) );

    @Effect() findKnockoffItems$ = this.actions$
        .ofType( KnockoffActions.FIND_KNOCKOFF_ITEM )
        .map(( action ) => action.payload )
        .switchMap(( knockoff ) => this.billingService.findKnockoffItems( knockoff ) )
        .map(( knockoff ) => this.knockoffActions.findKnockoffItemsSuccess( knockoff ) );

    @Effect() findKnockoffItemsByInvoice$ = this.actions$
        .ofType( KnockoffActions.FIND_KNOCKOFF_ITEM_BY_INVOICE )
        .map(( action ) => action.payload )
        .switchMap(( payload ) => this.billingService.findKnockoffItemsByInvoice( payload.knockoff, payload.invoice ) )
        .map(( knockoff ) => this.knockoffActions.findKnockoffItemsByInvoiceSuccess( knockoff ) );

    @Effect() itemToKnockoffItem$ = this.actions$
        .ofType( KnockoffActions.INVOICE_ITEM_TO_KNOCKOFF_ITEM )
        .map(( action ) => action.payload )
        .switchMap(( payload ) => this.billingService.itemToKnockoffItem( payload.invoice, payload.knockoff ) )
        .map(( knockoff ) => this.knockoffActions.itemToKnockoffItemSuccess( knockoff ) );

    @Effect() startKnockoffTask$ = this.actions$
        .ofType( KnockoffActions.START_KNOCKOFF_TASK )
        .map(( action ) => action.payload )
        .switchMap(( knockoff ) => this.billingService.startKnockoffTask( knockoff ) )
        .map(( referenceNo ) => this.knockoffActions.startKnockoffTaskSuccess( referenceNo ) )
        .mergeMap(( action ) => from( [action,
            this.knockoffActions.findAssignedKnockoffTasks(),
            this.knockoffActions.findPooledKnockoffTasks(),
        ],
        ) );

    @Effect() completeKnockoffTask$ = this.actions$
        .ofType( KnockoffActions.COMPLETE_KNOCKOFF_TASK )
        .map(( action ) => action.payload )
        .switchMap(( knockoffTask ) => this.billingService.completeKnockoffTask( knockoffTask ) )
        .map(( message ) => this.knockoffActions.completeKnockoffTaskSuccess( message ) )
        .mergeMap(( action ) => from( [action,
            this.knockoffActions.findAssignedKnockoffTasks(),
            this.knockoffActions.findPooledKnockoffTasks(),
            this.knockoffActions.findArchivedknockoffs(),
        ],
        ) );

    @Effect() claimKnockoffTask$ = this.actions$
        .ofType( KnockoffActions.CLAIM_KNOCKOFF_TASK )
        .map(( action ) => action.payload )
        .switchMap(( knockoffTask ) => this.billingService.claimKnockoffTask( knockoffTask ) )
        .map(( message ) => this.knockoffActions.claimKnockoffTaskSuccess( message ) )
        .mergeMap(( action ) => from( [action,
            this.knockoffActions.findAssignedKnockoffTasks(),
            this.knockoffActions.findPooledKnockoffTasks(),
        ],
        ) );

    @Effect() releaseKnockoffTask$ = this.actions$
        .ofType( KnockoffActions.RELEASE_KNOCKOFF_TASK )
        .map(( action ) => action.payload )
        .switchMap(( knockoffTask ) => this.billingService.releaseKnockoffTask( knockoffTask ) )
        .map(( message ) => this.knockoffActions.releaseKnockoffTaskSuccess( message ) )
        .mergeMap(( action ) => from( [action,
            this.knockoffActions.findAssignedKnockoffTasks(),
            this.knockoffActions.findPooledKnockoffTasks(),
        ],
        ) );

    @Effect() addKnockoffInvoice$ =
    this.actions$
        .ofType( KnockoffActions.ADD_KNOCKOFF_INVOICE )
        .map(( action ) => action.payload )
        .switchMap(( payload ) => this.billingService.addKnockoffInvoice( payload.knockoff, payload.invoice ) )
        .map(( message ) => this.knockoffActions.addKnockoffInvoiceSuccess( message ) );

    @Effect() addKnockoffDebitNote$ =
    this.actions$
        .ofType( KnockoffActions.ADD_KNOCKOFF_DEBIT_NOTE )
        .map(( action ) => action.payload )
        .switchMap(( payload ) => this.billingService.addKnockoffDebitNote( payload.knockoff, payload.debitNote ) )
        .map(( message ) => this.knockoffActions.addKnockoffDebitNoteSuccess( message ) );
    
    @Effect() updateKnockoff$ =
        this.actions$
            .ofType( KnockoffActions.UPDATE_KNOCKOFF )
            .map(( action ) => action.payload )
            .switchMap(( knockoff ) => this.billingService.updateKnockoff(knockoff) )
            .map(( message ) => this.knockoffActions.updateKnockoffSuccess(message) );
    
    @Effect() findKnockoffsByAccountCharge$ = this.actions$
    .ofType( KnockoffActions.FIND_ACCOUNT_CHARGE_BY_KNOCKOFF )
    .map(( action ) => action.payload )
    .switchMap(( knockoff ) => this.billingService.findKnockoffsByAccountCharge(knockoff) )
    .map(( accountCharge ) => this.knockoffActions.findKnockoffsByAccountChargeSuccess(accountCharge) );
    
    @Effect() findKnockoffsByDebitNote$ = this.actions$
    .ofType( KnockoffActions.FIND_DEBIT_NOTE_BY_KNOCKOFF )
    .map(( action ) => action.payload )
    .switchMap(( knockoff ) => this.billingService.findKnockoffsByDebitNote(knockoff) )
    .map(( message ) => this.knockoffActions.findKnockoffsByDebitNoteSuccess(message) );
}