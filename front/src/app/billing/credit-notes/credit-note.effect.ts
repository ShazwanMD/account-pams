import {Injectable} from '@angular/core';
import {Effect, Actions} from '@ngrx/effects';
import {CreditNoteActions} from "./credit-note.action";
import {from} from "rxjs/observable/from";
import {BillingService} from "../../../services/billing.service";
import {BillingModuleState} from "../index";
import {Store} from "@ngrx/store";

@Injectable()
export class CreditNoteEffects {

  private CREDIT_NOTE_TASK: string[] = "billingModuleState.creditNoteTask".split(".");

  constructor(private actions$: Actions,
              private creditNoteActions: CreditNoteActions,
              private billingService: BillingService,
              private store$: Store<BillingModuleState>) {
  }

  @Effect() findCompletedCreditNotes$ = this.actions$
    .ofType(CreditNoteActions.FIND_COMPLETED_CREDIT_NOTES)
    .switchMap(() => this.billingService.findCompletedCreditNotes())
    .map(creditNotes => this.creditNoteActions.findCompletedCreditNotesSuccess(creditNotes));

  @Effect() findArchivedCreditNotes$ = this.actions$
    .ofType(CreditNoteActions.FIND_ARCHIVED_CREDIT_NOTES)
    .switchMap(() => this.billingService.findArchivedCreditNotes())
    .map(creditNotes => this.creditNoteActions.findArchivedCreditNotesSuccess(creditNotes));

  @Effect() findAssignedCreditNoteTasks$ = this.actions$
    .ofType(CreditNoteActions.FIND_ASSIGNED_CREDIT_NOTE_TASKS)
    .switchMap(() => this.billingService.findAssignedCreditNoteTasks())
    .map(creditNotes => this.creditNoteActions.findAssignedCreditNoteTasksSuccess(creditNotes));

  @Effect() findPooledCreditNoteTasks$ = this.actions$
    .ofType(CreditNoteActions.FIND_POOLED_CREDIT_NOTE_TASKS)
    .switchMap(() => this.billingService.findPooledCreditNoteTasks())
    .map(creditNotes => this.creditNoteActions.findPooledCreditNoteTasksSuccess(creditNotes));

  @Effect() findCreditNoteTaskByTaskId = this.actions$
    .ofType(CreditNoteActions.FIND_CREDIT_NOTE_TASK_BY_TASK_ID)
    .map(action => action.payload)
    .switchMap(taskId => this.billingService.findCreditNoteTaskByTaskId(taskId))
    .map(task => this.creditNoteActions.findCreditNoteTaskByTaskIdSuccess(task));

  @Effect() findCreditNoteByReferenceNo$ = this.actions$
    .ofType(CreditNoteActions.FIND_CREDIT_NOTE_BY_REFERENCE_NO)
    .map(action => action.payload)
    .switchMap(referenceNo => this.billingService.findCreditNoteByReferenceNo(referenceNo))
    .map(creditNote => this.creditNoteActions.findCreditNoteByReferenceNoSuccess(creditNote));

  @Effect() startCreditNoteTask$ = this.actions$
    .ofType(CreditNoteActions.START_CREDIT_NOTE_TASK)
    .map(action => action.payload)
    .switchMap(creditNote => this.billingService.startCreditNoteTask(creditNote))
    .map(referenceNo => this.creditNoteActions.startCreditNoteTaskSuccess(referenceNo))
    .mergeMap(action => from([action,
        this.creditNoteActions.findAssignedCreditNoteTasks(),
        this.creditNoteActions.findPooledCreditNoteTasks()
      ]
    ));

  @Effect() completeCreditNoteTask$ = this.actions$
    .ofType(CreditNoteActions.COMPLETE_CREDIT_NOTE_TASK)
    .map(action => action.payload)
    .switchMap(creditNoteTask => this.billingService.completeCreditNoteTask(creditNoteTask))
    .map(message => this.creditNoteActions.completeCreditNoteTaskSuccess(message))
    .mergeMap(action => from([action,
        this.creditNoteActions.findAssignedCreditNoteTasks(),
        this.creditNoteActions.findPooledCreditNoteTasks()
      ]
    ));

  @Effect() claimCreditNoteTask$ = this.actions$
    .ofType(CreditNoteActions.CLAIM_CREDIT_NOTE_TASK)
    .map(action => action.payload)
    .switchMap(creditNoteTask => this.billingService.claimCreditNoteTask(creditNoteTask))
    .map(message => this.creditNoteActions.claimCreditNoteTaskSuccess(message))
    .mergeMap(action => from([action,
        this.creditNoteActions.findAssignedCreditNoteTasks(),
        this.creditNoteActions.findPooledCreditNoteTasks()
      ]
    ));

  @Effect() releaseCreditNoteTask$ = this.actions$
    .ofType(CreditNoteActions.RELEASE_CREDIT_NOTE_TASK)
    .map(action => action.payload)
    .switchMap(creditNoteTask => this.billingService.releaseCreditNoteTask(creditNoteTask))
    .map(message => this.creditNoteActions.releaseCreditNoteTaskSuccess(message))
    .mergeMap(action => from([action,
        this.creditNoteActions.findAssignedCreditNoteTasks(),
        this.creditNoteActions.findPooledCreditNoteTasks()
      ]
    ));

  @Effect() updateCreditNote$ = this.actions$
    .ofType(CreditNoteActions.UPDATE_CREDIT_NOTE)
    .map(action => action.payload)
    .switchMap(creditNote => this.billingService.updateCreditNote(creditNote))
    .map(creditNote => this.creditNoteActions.updateCreditNoteSuccess(creditNote));
}
