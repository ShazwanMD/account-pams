import {Injectable} from '@angular/core';
import {Effect, Actions} from '@ngrx/effects';
import { Store } from "@ngrx/store";
import {CreditNoteActions} from "./credit-note.action";
import {BillingService} from "../../../services/billing.service";
import {BillingModuleState} from "../index";

@Injectable()
export class CreditNoteEffects {
    
    private CREDIT_NOTE_TASK: string[] = "billingModuleState.creditNoteTask".split(".");

    constructor(private actions$: Actions,
                private creditNoteActions: CreditNoteActions,
                private billingService: BillingService,
                private store$: Store<BillingModuleState>) {
    } 
    
    @Effect() findCreditNotes$ = this.actions$
    .ofType(CreditNoteActions.FIND_CREDIT_NOTES)
    .map(action => action.payload)
    .switchMap(invoice => this.billingService.findCreditNotes(invoice))
    .map(creditNotes => this.creditNoteActions.findCreditNotesSuccess(creditNotes));
    
    @Effect() findCreditNoteByReferenceNo$ = this.actions$
    .ofType(CreditNoteActions.FIND_CREDIT_NOTES_BY_REFERENCE_NO)
    .map(action => action.payload)
    .switchMap(referenceNo => this.billingService.findCreditNoteByReferenceNo(referenceNo))
    .map(creditNote => this.creditNoteActions.findCreditNoteByReferenceNoSuccess(creditNote));

    @Effect() findCreditNoteTaskByTaskId = this.actions$
    .ofType(CreditNoteActions.FIND_CREDIT_NOTE_TASK_BY_TASK_ID)
    .map(action => action.payload)
    .switchMap(taskId => this.billingService.findCreditNoteTaskByTaskId(taskId))
    .map(task => this.creditNoteActions.findCreditNoteTaskByTaskIdSuccess(task));
    
    @Effect() startCreditNoteTask$ = this.actions$
    .ofType(CreditNoteActions.START_CREDIT_NOTE_TASK)
    .map(action => action.payload)
    .switchMap(creditNote => this.billingService.startCreditNoteTask(creditNote))
    .map(creditNote => this.creditNoteActions.startCreditNoteTaskSuccess(creditNote));
    
    @Effect() updateCreditNote$ = this.actions$
    .ofType(CreditNoteActions.UPDATE_CREDIT_NOTE)
    .map(action => action.payload)
    .switchMap(creditNote => this.billingService.updateCreditNote(creditNote))
    .map(creditNote => this.creditNoteActions.updateCreditNoteSuccess(creditNote));
}