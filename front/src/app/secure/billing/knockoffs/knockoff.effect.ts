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
    private KNOCKOFF_TASK: string[] = 'billingModuleState.knockoffTask'.split('.');

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
        .map(( knockoff ) => this.knockoffActions.findKnockoffByReferenceNoSuccess( knockoff ) )
        .mergeMap((action) => from([action,
            this.knockoffActions.findKnockoffItems(action.payload),
            //this.receiptActions.findReceiptsByInvoice(action.payload),
          ],
    ));

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
        .map(( task ) => this.knockoffActions.findKnockoffTaskByTaskIdSuccess( task ) )
        .mergeMap((action) => from([action,
            this.knockoffActions.findKnockoffItems(action.payload),
            //this.receiptActions.findReceiptsByInvoice(action.payload),
          ],
    ));

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

    @Effect() findDebitKnockoffItems$ = this.actions$
    .ofType( KnockoffActions.FIND_KNOCKOFF_ITEM_BY_DEBIT )
    .map(( action ) => action.payload )
    .switchMap(( payload ) => this.billingService.findDebitKnockoffItems(payload.knockoff, payload.debitNote) )
    .map(( knockoff ) => this.knockoffActions.findDebitKnockoffItemsSuccess(knockoff) );
    
    @Effect() itemToKnockoffItem$ = this.actions$
        .ofType( KnockoffActions.INVOICE_ITEM_TO_KNOCKOFF_ITEM )
        .map(( action ) => action.payload )
        .switchMap(( payload ) => this.billingService.itemToKnockoffItem( payload.invoice, payload.knockoff ) )
        .map(( knockoff ) => this.knockoffActions.itemToKnockoffItemSuccess( knockoff ) )
        .withLatestFrom(this.store$.select(...this.KNOCKOFF_TASK))
        .map((state) => state[1])
        .map((knockoff) => this.knockoffActions.findKnockoffItems(knockoff));       

    @Effect() debitToKnockoffItem$ = this.actions$
    .ofType( KnockoffActions.DEBIT_ITEM_TO_KNOCKOFF_ITEM )
    .map(( action ) => action.payload )
    .switchMap(( payload ) => this.billingService.debitToKnockoffItem(payload.debitNote, payload.knockoff) )
    .map(( knockoff ) => this.knockoffActions.debitToKnockoffItemSuccess(knockoff) )
    .withLatestFrom(this.store$.select(...this.KNOCKOFF_TASK))
    .map((state) => state[1])
    .map((knockoff) => this.knockoffActions.findKnockoffItems(knockoff));
    
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
    
    @Effect() removeKnockoffTask$ = this.actions$
    .ofType(KnockoffActions.REMOVE_KNOCKOFF_TASK)
    .map((action) => action.payload)
    .switchMap((knockoffTask) => this.billingService.removeKnockoffTask(knockoffTask))
    .map((task) => this.knockoffActions.removeKnockoffTaskSuccess(task))
    .mergeMap((action) => from([action,
                                this.knockoffActions.findAssignedKnockoffTasks(),
                                this.knockoffActions.findPooledKnockoffTasks(),
                                this.knockoffActions.findArchivedknockoffs(),
      ],
    ));
    
    @Effect() addKnockoffInvoice$ =
    this.actions$
        .ofType( KnockoffActions.ADD_KNOCKOFF_INVOICE )
        .map(( action ) => action.payload )
        .switchMap(( payload ) => this.billingService.addKnockoffInvoice( payload.knockoff, payload.invoice ) )
        .map(( message ) => this.knockoffActions.addKnockoffInvoiceSuccess( message ) )
        .withLatestFrom(this.store$.select(...this.KNOCKOFF_TASK))
        .map((state) => state[1])
        .map((knockoff) => this.knockoffActions.findKnockoffsByInvoice(knockoff));  

    @Effect() addKnockoffAccountCharge$ =
    this.actions$
        .ofType( KnockoffActions.ADD_KNOCKOFF_ACCOUNT_CHARGE )
        .map(( action ) => action.payload )
        .switchMap(( payload ) => this.billingService.addKnockoffAccountCharge( payload.knockoff, payload.accountCharge ) )
        .map(( message ) => this.knockoffActions.addKnockoffAccountChargeSuccess( message ) )
        .withLatestFrom(this.store$.select(...this.KNOCKOFF_TASK))
        .map((state) => state[1])
        .map((knockoff) => this.knockoffActions.findKnockoffsByAccountCharge(knockoff));         

    @Effect() addKnockoffDebitNote$ =
    this.actions$
        .ofType( KnockoffActions.ADD_KNOCKOFF_DEBIT_NOTE )
        .map(( action ) => action.payload )
        .switchMap(( payload ) => this.billingService.addKnockoffDebitNote( payload.knockoff, payload.debitNote ) )
        .map(( message ) => this.knockoffActions.addKnockoffDebitNoteSuccess( message ) )
        .withLatestFrom(this.store$.select(...this.KNOCKOFF_TASK))
        .map((state) => state[1])
        .map((knockoff) => this.knockoffActions.findKnockoffsByDebitNote(knockoff)); 
    
    @Effect() updateKnockoff$ =
    this.actions$
         .ofType( KnockoffActions.UPDATE_KNOCKOFF )
         .map(( action ) => action.payload )
         .switchMap(( knockoff ) => this.billingService.updateKnockoff(knockoff) )
        .map(( message ) => this.knockoffActions.updateKnockoffSuccess(message) );
    
     @Effect() findKnockoffsByAccountCharge$ = this.actions$
     .ofType( KnockoffActions.FIND_ACCOUNT_CHARGE_BY_KNOCKOFF )
     .map(( action ) => action.payload )
    .switchMap(( knockoff ) => this.billingService.findKnockoffsByAccountCharge( knockoff ) )
    .map(( knockoff ) => this.knockoffActions.findKnockoffsByAccountChargeSuccess( knockoff ) );
    
    @Effect() findKnockoffsByDebitNote$ = this.actions$
    .ofType( KnockoffActions.FIND_DEBIT_NOTE_BY_KNOCKOFF )
    .map(( action ) => action.payload )
    .switchMap(( knockoff ) => this.billingService.findKnockoffsByDebitNote(knockoff) )
    .map(( knockoff ) => this.knockoffActions.findKnockoffsByDebitNoteSuccess(knockoff) );

    @Effect() addKnockoffItem$ =
    this.actions$
      .ofType(KnockoffActions.ADD_KNOCKOFF_ITEM)
      .map((action) => action.payload)
      .switchMap((payload) => this.billingService.addKnockoffItem(payload.knockoff, payload.item))
      .map((message) => this.knockoffActions.addKnockoffItemSuccess(message))
      .withLatestFrom(this.store$.select(...this.KNOCKOFF_TASK))
      .map((state) => state[1])
      .map((knockoff) => this.knockoffActions.findKnockoffItems(knockoff));       

    @Effect() findInvoiceKnockoffItems$ = this.actions$
    .ofType( KnockoffActions.FIND_INVOICE_KNOCKOFF_ITEM )
    .map(( action ) => action.payload )
    .switchMap(( payload ) => this.billingService.findInvoiceKnockoffItems( payload.knockoff, payload.invoice ) )
    .map(( knockoff ) => this.knockoffActions.findInvoiceKnockoffItemsSuccess(knockoff) );
    
    @Effect() updateKnockoffItems$ = this.actions$
    .ofType(KnockoffActions.UPDATE_KNOCKOFF_ITEM)
    .map((action) => action.payload)
    .switchMap((payload) => this.billingService.updateKnockoffItems(payload.knockoff, payload.item))
    .map((message) => this.knockoffActions.updateKnockoffItemsSuccess(message))
    .withLatestFrom(this.store$.select(...this.KNOCKOFF_TASK))
    .map((state) => state[1])
    .map((knockoff) => this.knockoffActions.findKnockoffItems(knockoff));
    
    @Effect() deleteKnockoffDebitNotes$ = this.actions$
    .ofType(KnockoffActions.DELETE_KNOCKOFF_DEBIT_NOTE)
    .map((action) => action.payload)
    .switchMap((knockoffDebitNote) => this.billingService.deleteKnockoffDebitNotes(knockoffDebitNote))
    .map((message) => this.knockoffActions.deleteKnockoffDebitNotesSuccess(message))
            .withLatestFrom(this.store$.select(...this.KNOCKOFF_TASK))
      .map((state) => state[1])
      .map((debitNote) => this.knockoffActions.findKnockoffsByDebitNote(debitNote));
    
    @Effect() deleteKnockoffInvoices$ = this.actions$
    .ofType(KnockoffActions.DELETE_KNOCKOFF_INVOICE)
    .map((action) => action.payload)
    .switchMap((knockoffInvoice) => this.billingService.deleteKnockoffInvoices(knockoffInvoice))
    .map((message) => this.knockoffActions.deleteKnockoffInvoicesSuccess(message))
        .withLatestFrom(this.store$.select(...this.KNOCKOFF_TASK))
      .map((state) => state[1])
      .map((knockoff) => this.knockoffActions.findKnockoffsByInvoice(knockoff));
    
    @Effect() deleteKnockoffAccCharges$ = this.actions$
    .ofType(KnockoffActions.DELETE_KNOCKOFF_ACC_CHARGES)
    .map((action) => action.payload)
    .switchMap((knockoffAccountCharge) => this.billingService.deleteKnockoffAccCharges(knockoffAccountCharge))
    .map((message) => this.knockoffActions.deleteKnockoffAccChargesSuccess(message))
    .withLatestFrom(this.store$.select(...this.KNOCKOFF_TASK))
      .map((state) => state[1])
      .map((knockoff) => this.knockoffActions.findKnockoffsByAccountCharge(knockoff));
    
    @Effect() deleteKnockoffItem$ = this.actions$
    .ofType(KnockoffActions.DELETE_KNOCKOFF_ITEM)
    .map((action) => action.payload)
    .switchMap((payload) => this.billingService.deleteKnockoffItem(payload.knockoff, payload.item))
    .map((message) => this.knockoffActions.deleteKnockoffItemSuccess(message))
    .withLatestFrom(this.store$.select(...this.KNOCKOFF_TASK))
      .map((state) => state[1])
      .map((knockoff) => this.knockoffActions.findKnockoffItems(knockoff));
}


