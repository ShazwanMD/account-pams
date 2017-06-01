import {Injectable} from '@angular/core';
import {Effect, Actions} from '@ngrx/effects';
import {DebitNoteActions} from "./debit-note.action";
import {from} from "rxjs/observable/from";
import {BillingService} from "../../../services/billing.service";
import {BillingModuleState} from "../index";
import { Store } from "@ngrx/store";

@Injectable()
export class DebitNoteEffects {
    
    private DEBIT_NOTE_TASK: string[] = "billingModuleState.debitNoteTask".split(".");

    constructor(private actions$: Actions,
                private debitNoteActions: DebitNoteActions,
                private billingService: BillingService,
                private store$: Store<BillingModuleState>) {
    }
    
  /*  @Effect() findDebitNotes$ = this.actions$
    .ofType(DebitNoteActions.FIND_DEBIT_NOTES)
    .map(action => action.payload)
    .switchMap(invoice => this.billingService.findDebitNotes(invoice))
    .map(debitNotes => this.debitNoteActions.findDebitNotesSuccess(debitNotes)); */
    
    @Effect() findDebitNoteByReferenceNo$ = this.actions$
    .ofType(DebitNoteActions.FIND_DEBIT_NOTES_BY_REFERENCE_NO)
    .map(action => action.payload)
    .switchMap(referenceNo => this.billingService.findDebitNoteByReferenceNo(referenceNo))
    .map(debitNote => this.debitNoteActions.findDebitNoteByReferenceNoSuccess(debitNote));

    @Effect() findDebitNoteTaskByTaskId = this.actions$
    .ofType(DebitNoteActions.FIND_DEBIT_NOTE_TASK_BY_TASK_ID)
    .map(action => action.payload)
    .switchMap(taskId => this.billingService.findDebitNoteTaskByTaskId(taskId))
    .map(task => this.debitNoteActions.findDebitNoteTaskByTaskIdSuccess(task));
    
    // @Effect() startDebitNoteTask$ = this.actions$
    // .ofType(DebitNoteActions.START_DEBIT_NOTE_TASK)
    // .map(action => action.payload)
    // .switchMap(debitNote => this.billingService.startDebitNoteTask(debitNote))
    // .map(debitNote => this.debitNoteActions.startDebitNoteTaskSuccess(debitNote));

  @Effect() startDebitNoteTask$ = this.actions$
    .ofType(DebitNoteActions.START_DEBIT_NOTE_TASK)
    .map(action => action.payload)
    .switchMap(debitNote => this.billingService.startDebitNoteTask(debitNote))
    .map(referenceNo => this.debitNoteActions.startDebitNoteTaskSuccess(referenceNo))
    .mergeMap(action => from([action,
        this.debitNoteActions.findAssignedDebitNoteTasks(),
        this.debitNoteActions.findPooledDebitNoteTasks()
      ]
    ));

    @Effect() updateDebitNote$ = this.actions$
    .ofType(DebitNoteActions.UPDATE_DEBIT_NOTE)
    .map(action => action.payload)
    .switchMap(debitNote => this.billingService.updateDebitNote(debitNote))
    .map(debitNote => this.debitNoteActions.updateDebitNoteSuccess(debitNote));
    
    
}