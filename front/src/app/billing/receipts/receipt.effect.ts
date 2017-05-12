import {Injectable} from '@angular/core';
import {Effect, Actions} from '@ngrx/effects';
import {ReceiptActions} from "./receipt.action";
import {from} from "rxjs/observable/from";
import {BillingService} from "../../../services/billing.service";


@Injectable()
export class ReceiptEffects {
  constructor(private actions$: Actions,
              private receiptActions: ReceiptActions,
              private billingService: BillingService) {
  }

  @Effect() findAssignedReceiptTasks$ = this.actions$
    .ofType(ReceiptActions.FIND_ASSIGNED_RECEIPT_TASKS)
    .switchMap(() => this.billingService.findAssignedReceiptTasks())
    .map(receipts => this.receiptActions.findAssignedReceiptTasksSuccess(receipts));


  @Effect() findPooledReceiptTasks$ = this.actions$
    .ofType(ReceiptActions.FIND_POOLED_RECEIPT_TASKS)
    .switchMap(() => this.billingService.findPooledReceiptTasks())
    .map(receipts => this.receiptActions.findPooledReceiptTasksSuccess(receipts));

  @Effect() findReceiptTaskByTaskId = this.actions$
    .ofType(ReceiptActions.FIND_RECEIPT_TASK_BY_TASK_ID)
    .map(action => action.payload)
    .switchMap(taskId => this.billingService.findReceiptTaskByTaskId(taskId))
    .map(task => this.receiptActions.findReceiptTaskByTaskIdSuccess(task));

  @Effect() findReceiptByReferenceNo$ = this.actions$
    .ofType(ReceiptActions.FIND_RECEIPT_BY_REFERENCE_NO)
    .map(action => action.payload)
    .switchMap(referenceNo => this.billingService.findReceiptByReferenceNo(referenceNo))
    .map(receipt => this.receiptActions.findReceiptByReferenceNoSuccess(receipt))
    .mergeMap(action => from([action, this.receiptActions.findReceiptItems(action.payload)]));

  @Effect() findReceiptItems$ = this.actions$
    .ofType(ReceiptActions.FIND_RECEIPT_ITEMS)
    .map(action => action.payload)
    .switchMap(receipt => this.billingService.findReceiptItems(receipt))
    .map(items => this.receiptActions.findReceiptItemsSuccess(items));

  @Effect() startReceiptTask$ = this.actions$
    .ofType(ReceiptActions.START_RECEIPT_TASK)
    .map(action => action.payload)
    .switchMap(receipt => this.billingService.startReceiptTask(receipt))
    .map(referenceNo => this.receiptActions.startReceiptTaskSuccess(referenceNo))
    .mergeMap(action => from([action,
       this.receiptActions.findAssignedReceiptTasks(),
       this.receiptActions.findPooledReceiptTasks()
      ]
    ));

  @Effect() completeReceiptTask$ = this.actions$
    .ofType(ReceiptActions.COMPLETE_RECEIPT_TASK)
    .map(action => action.payload)
    .switchMap(receiptTask => this.billingService.completeReceiptTask(receiptTask))
    .map(message => this.receiptActions.completeReceiptTaskSuccess(message))
    .mergeMap(action => from([action,
        this.receiptActions.findAssignedReceiptTasks(),
        this.receiptActions.findPooledReceiptTasks()
      ]
    ));
  
  @Effect() assignReceiptTask$ = this.actions$
    .ofType(ReceiptActions.ASSIGN_RECEIPT_TASK)
    .map(action => action.payload);
  // todo


  @Effect() releaseReceiptTask$ = this.actions$
    .ofType(ReceiptActions.RELEASE_RECEIPT_TASK)
    .map(action => action.payload);
  // todo
    // .switchMap(receipt => this.billingService.startReceiptTask(receipt))
    // .map(task => this.receiptActions.startReceiptTaskSuccess(task));

  @Effect() updateReceipt$ = this.actions$
    .ofType(ReceiptActions.UPDATE_RECEIPT)
    .map(action => action.payload)
    .switchMap(receipt => this.billingService.updateReceipt(receipt))
    .map(receipt => this.receiptActions.updateReceiptSuccess(receipt));
}
