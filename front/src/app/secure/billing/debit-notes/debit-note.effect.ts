import {Injectable} from '@angular/core';
import {Actions, Effect} from '@ngrx/effects';
import {DebitNoteActions} from './debit-note.action';
import {from} from 'rxjs/observable/from';
import {BillingService} from '../../../../services/billing.service';
import {BillingModuleState} from '../index';
import {Store} from '@ngrx/store';

@Injectable()
export class DebitNoteEffects {

  private DEBIT_NOTE_TASK: string[] = 'billingModuleState.debitNoteTask'.split('.');

  constructor(private actions$: Actions,
              private debitNoteActions: DebitNoteActions,
              private billingService: BillingService,
              private store$: Store<BillingModuleState>) {
  }

  @Effect() findDebitNotesByInvoice$ = this.actions$
    .ofType(DebitNoteActions.FIND_DEBIT_NOTES_SUCCESS)
    .map((action) => action.payload)
    .switchMap((invoice) => this.billingService.findDebitNotesbyInvoice(invoice))
    .map((debitNotes) => this.debitNoteActions.findDebitNotesSuccess(debitNotes));

  @Effect() findCompletedDebitNotes$ = this.actions$
    .ofType(DebitNoteActions.FIND_COMPLETED_DEBIT_NOTES)
    .switchMap(() => this.billingService.findCompletedDebitNotes())
    .map((debitNotes) => this.debitNoteActions.findCompletedDebitNotesSuccess(debitNotes));

  @Effect() findArchivedDebitNotes$ = this.actions$
    .ofType(DebitNoteActions.FIND_ARCHIVED_DEBIT_NOTES)
    .switchMap(() => this.billingService.findArchivedDebitNotes())
    .map((debitNotes) => this.debitNoteActions.findArchivedDebitNotesSuccess(debitNotes));

  @Effect() findAssignedDebitNoteTasks$ = this.actions$
    .ofType(DebitNoteActions.FIND_ASSIGNED_DEBIT_NOTE_TASKS)
    .switchMap(() => this.billingService.findAssignedDebitNoteTasks())
    .map((debitNotes) => this.debitNoteActions.findAssignedDebitNoteTasksSuccess(debitNotes));

  @Effect() findPooledDebitNoteTasks$ = this.actions$
    .ofType(DebitNoteActions.FIND_POOLED_DEBIT_NOTE_TASKS)
    .switchMap(() => this.billingService.findPooledDebitNoteTasks())
    .map((debitNotes) => this.debitNoteActions.findPooledDebitNoteTasksSuccess(debitNotes));

  @Effect() findDebitNoteTaskByTaskId = this.actions$
    .ofType(DebitNoteActions.FIND_DEBIT_NOTE_TASK_BY_TASK_ID)
    .map((action) => action.payload)
    .switchMap((taskId) => this.billingService.findDebitNoteTaskByTaskId(taskId))
    .map((task) => this.debitNoteActions.findDebitNoteTaskByTaskIdSuccess(task));

  @Effect() findDebitNoteByReferenceNo$ = this.actions$
    .ofType(DebitNoteActions.FIND_DEBIT_NOTE_BY_REFERENCE_NO)
    .map((action) => action.payload)
    .switchMap((referenceNo) => this.billingService.findDebitNoteByReferenceNo(referenceNo))
    .map((debitNote) => this.debitNoteActions.findDebitNoteByReferenceNoSuccess(debitNote))
    .mergeMap((action) => from([action, this.debitNoteActions.findDebitNoteItems(action.payload)]));

  @Effect() findDebitNoteItems$ = this.actions$
    .ofType(DebitNoteActions.FIND_DEBIT_NOTE_ITEMS)
    .map((action) => action.payload)
    .switchMap((debitNote) => this.billingService.findDebitNoteItems(debitNote))
    .map((items) => this.debitNoteActions.findDebitNoteItemsSuccess(items));

  @Effect() startDebitNoteTask$ = this.actions$
    .ofType(DebitNoteActions.START_DEBIT_NOTE_TASK)
    .map((action) => action.payload)
    .switchMap((debitNote) => this.billingService.startDebitNoteTask(debitNote))
    .map((referenceNo) => this.debitNoteActions.startDebitNoteTaskSuccess(referenceNo))
    .mergeMap((action) => from([action,
        this.debitNoteActions.findAssignedDebitNoteTasks(),
        this.debitNoteActions.findPooledDebitNoteTasks(),
      ],
    ));
  
  @Effect() removeDebitNoteTask$ = this.actions$
  .ofType(DebitNoteActions.REMOVE_DEBIT_NOTE_TASK)
  .map((action) => action.payload)
  .switchMap((debitNoteTask) => this.billingService.removeDebitNoteTask(debitNoteTask))
  .map((task) => this.debitNoteActions.removeDebitNoteTaskSuccess(task))
  .mergeMap((action) => from([action,
                              this.debitNoteActions.findAssignedDebitNoteTasks(),
                              this.debitNoteActions.findPooledDebitNoteTasks(),
                              this.debitNoteActions.findArchivedDebitNotes(),
    ],
  ));

  @Effect() completeDebitNoteTask$ = this.actions$
    .ofType(DebitNoteActions.COMPLETE_DEBIT_NOTE_TASK)
    .map((action) => action.payload)
    .switchMap((debitNoteTask) => this.billingService.completeDebitNoteTask(debitNoteTask))
    .map((message) => this.debitNoteActions.completeDebitNoteTaskSuccess(message))
    .mergeMap((action) => from([action,
        this.debitNoteActions.findAssignedDebitNoteTasks(),
        this.debitNoteActions.findPooledDebitNoteTasks(),
        this.debitNoteActions.findArchivedDebitNotes(),
      ],
    ));

  @Effect() claimDebitNoteTask$ = this.actions$
    .ofType(DebitNoteActions.CLAIM_DEBIT_NOTE_TASK)
    .map((action) => action.payload)
    .switchMap((debitNoteTask) => this.billingService.claimDebitNoteTask(debitNoteTask))
    .map((message) => this.debitNoteActions.claimDebitNoteTaskSuccess(message))
    .mergeMap((action) => from([action,
        this.debitNoteActions.findAssignedDebitNoteTasks(),
        this.debitNoteActions.findPooledDebitNoteTasks(),

      ],
    ));

  @Effect() releaseDebitNoteTask$ = this.actions$
    .ofType(DebitNoteActions.RELEASE_DEBIT_NOTE_TASK)
    .map((action) => action.payload)
    .switchMap((debitNoteTask) => this.billingService.releaseDebitNoteTask(debitNoteTask))
    .map((message) => this.debitNoteActions.releaseDebitNoteTaskSuccess(message))
    .mergeMap((action) => from([action,
        this.debitNoteActions.findAssignedDebitNoteTasks(),
        this.debitNoteActions.findPooledDebitNoteTasks(),
      ],
    ));

  @Effect() updateDebitNote$ = this.actions$
    .ofType(DebitNoteActions.UPDATE_DEBIT_NOTE)
    .map((action) => action.payload)
    .switchMap((debitNote) => this.billingService.updateDebitNote(debitNote))
    .map((debitNote) => this.debitNoteActions.updateDebitNoteSuccess(debitNote));

  @Effect() addDebitNoteItem$ =
    this.actions$
      .ofType(DebitNoteActions.ADD_DEBIT_NOTE_ITEM)
      .map((action) => action.payload)
      .switchMap((payload) => this.billingService.addDebitNoteItem(payload.debitNote, payload.item))
      .map((message) => this.debitNoteActions.addDebitNoteItemSuccess(message))
      .withLatestFrom(this.store$.select(...this.DEBIT_NOTE_TASK))
      .map((state) => state[1])
      .map((debitNote) => this.debitNoteActions.findDebitNoteItems(debitNote));

  @Effect() updateDebitNoteItem$ = this.actions$
    .ofType(DebitNoteActions.UPDATE_DEBIT_NOTE_ITEM)
    .map((action) => action.payload)
    .switchMap((payload) => this.billingService.updateDebitNoteItem(payload.debitNote, payload.item))
    .map((message) => this.debitNoteActions.updateDebitNoteItemSuccess(message))
    .withLatestFrom(this.store$.select(...this.DEBIT_NOTE_TASK))
    .map((state) => state[1])
    .map((debitNote) => this.debitNoteActions.findDebitNoteItems(debitNote));

  @Effect() deleteDebitNoteItem$ = this.actions$
    .ofType(DebitNoteActions.DELETE_DEBIT_NOTE_ITEM)
    .map((action) => action.payload)
    .switchMap((payload) => this.billingService.deleteDebitNoteItem(payload.debitNote, payload.item))
    .map((message) => this.debitNoteActions.deleteDebitNoteItemSuccess(message))
    .withLatestFrom(this.store$.select(...this.DEBIT_NOTE_TASK))
    .map((state) => state[1])
    .map((debitNote) => this.debitNoteActions.findDebitNoteItems(debitNote));

  @Effect() findUnpaidDebitNotes$ = this.actions$
  .ofType(DebitNoteActions.FIND_UNPAID_DEBIT_NOTES)
  .map((action) => action.payload)
  .switchMap((account) => this.billingService.findUnpaidDebitNotes(account))
  .map((debitNotes) => this.debitNoteActions.findUnpaidDebitNotesSuccess(debitNotes));
}
