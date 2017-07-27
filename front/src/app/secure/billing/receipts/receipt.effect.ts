import {Injectable} from '@angular/core';
import {Effect, Actions} from '@ngrx/effects';
import {ReceiptActions} from './receipt.action';
import {from} from 'rxjs/observable/from';
import {BillingService} from '../../../../services/billing.service';
import {BillingModuleState} from '../index';
import {Store} from '@ngrx/store';

@Injectable()
export class ReceiptEffects {

  private RECEIPT_TASK: string[] = 'billingModuleState.receiptTask'.split('.');

  constructor(private actions$: Actions,
              private receiptActions: ReceiptActions,
              private billingService: BillingService,
              private store$: Store<BillingModuleState>) {
  }

  @Effect() findAssignedReceiptTasks$ = this.actions$
    .ofType(ReceiptActions.FIND_ASSIGNED_RECEIPT_TASKS)
    .switchMap(() => this.billingService.findAssignedReceiptTasks())
    .map((receipts) => this.receiptActions.findAssignedReceiptTasksSuccess(receipts));

  @Effect() findPooledReceiptTasks$ = this.actions$
    .ofType(ReceiptActions.FIND_POOLED_RECEIPT_TASKS)
    .switchMap(() => this.billingService.findPooledReceiptTasks())
    .map((receipts) => this.receiptActions.findPooledReceiptTasksSuccess(receipts));

  @Effect() findReceiptTaskByTaskId = this.actions$
    .ofType(ReceiptActions.FIND_RECEIPT_TASK_BY_TASK_ID)
    .map((action) => action.payload)
    .switchMap((taskId) => this.billingService.findReceiptTaskByTaskId(taskId))
    .map((task) => this.receiptActions.findReceiptTaskByTaskIdSuccess(task));

  @Effect() findReceiptByReferenceNo$ = this.actions$
    .ofType(ReceiptActions.FIND_RECEIPT_BY_REFERENCE_NO)
    .map((action) => action.payload)
    .switchMap((referenceNo) => this.billingService.findReceiptByReferenceNo(referenceNo))
    .map((receipt) => this.receiptActions.findReceiptByReferenceNoSuccess(receipt))
    .mergeMap((action) => from([action, this.receiptActions.findReceiptItems(action.payload)]));

  @Effect() findReceiptItems$ = this.actions$
    .ofType(ReceiptActions.FIND_RECEIPT_ITEMS)
    .map((action) => action.payload)
    .switchMap((receipt) => this.billingService.findReceiptItems(receipt))
    .map((items) => this.receiptActions.findReceiptItemsSuccess(items));

  @Effect() startReceiptTask$ = this.actions$
    .ofType(ReceiptActions.START_RECEIPT_TASK)
    .map((action) => action.payload)
    .switchMap((receipt) => this.billingService.startReceiptTask(receipt))
    .map((referenceNo) => this.receiptActions.startReceiptTaskSuccess(referenceNo))
    .mergeMap((action) => from([action,
        this.receiptActions.findAssignedReceiptTasks(),
        this.receiptActions.findPooledReceiptTasks(),
      ],
    ));

  @Effect() completeReceiptTask$ = this.actions$
    .ofType(ReceiptActions.COMPLETE_RECEIPT_TASK)
    .map((action) => action.payload)
    .switchMap((receiptTask) => this.billingService.completeReceiptTask(receiptTask))
    .map((message) => this.receiptActions.completeReceiptTaskSuccess(message))
    .mergeMap((action) => from([action,
        this.receiptActions.findAssignedReceiptTasks(),
        this.receiptActions.findPooledReceiptTasks(),
      ],
    ));

  @Effect() claimReceiptTask$ = this.actions$
    .ofType(ReceiptActions.CLAIM_RECEIPT_TASK)
    .map((action) => action.payload)
    .switchMap((receiptTask) => this.billingService.claimReceiptTask(receiptTask))
    .map((message) => this.receiptActions.claimReceiptTaskSuccess(message))
    .mergeMap((action) => from([action,
        this.receiptActions.findAssignedReceiptTasks(),
        this.receiptActions.findPooledReceiptTasks(),
      ],
    ));

  @Effect() releaseReceiptTask$ = this.actions$
    .ofType(ReceiptActions.RELEASE_RECEIPT_TASK)
    .map((action) => action.payload)
    .switchMap((receiptTask) => this.billingService.releaseReceiptTask(receiptTask))
    .map((message) => this.receiptActions.releaseReceiptTaskSuccess(message))
    .mergeMap((action) => from([action,
        this.receiptActions.findAssignedReceiptTasks(),
        this.receiptActions.findPooledReceiptTasks(),
      ],
    ));

  @Effect() updateReceipt$ = this.actions$
    .ofType(ReceiptActions.UPDATE_RECEIPT)
    .map((action) => action.payload)
    .switchMap((receipt) => this.billingService.updateReceipt(receipt))
    .map((receipt) => this.receiptActions.updateReceiptSuccess(receipt));

  @Effect() addReceiptItem$ =
    this.actions$
      .ofType(ReceiptActions.ADD_RECEIPT_ITEM)
      .map((action) => action.payload)
      .switchMap((payload) => this.billingService.addReceiptItem(payload.receipt, payload.item))
      .map((message) => this.receiptActions.addReceiptItemSuccess(message))
      .withLatestFrom(this.store$.select(...this.RECEIPT_TASK))
      .map((state) => state[1])
      .map((receipt) => this.receiptActions.findReceiptItems(receipt));

  @Effect() updateReceiptItem$ = this.actions$
    .ofType(ReceiptActions.UPDATE_RECEIPT_ITEM)
    .map((action) => action.payload)
    .switchMap((payload) => this.billingService.updateReceiptItem(payload.receipt, payload.item))
    .map((message) => this.receiptActions.updateReceiptItemSuccess(message))
    .withLatestFrom(this.store$.select(...this.RECEIPT_TASK))
    .map((state) => state[1])
    .map((receipt) => this.receiptActions.findReceiptItems(receipt));

  @Effect() deleteReceiptItem$ = this.actions$
    .ofType(ReceiptActions.DELETE_RECEIPT_ITEM)
    .map((action) => action.payload)
    .switchMap((payload) => this.billingService.deleteReceiptItem(payload.receipt, payload.item))
    .map((message) => this.receiptActions.deleteReceiptItemSuccess(message))
    .withLatestFrom(this.store$.select(...this.RECEIPT_TASK))
    .map((state) => state[1])
    .map((receipt) => this.receiptActions.findReceiptItems(receipt));

  @Effect() addReceiptInvoiceItems$ =
      this.actions$
        .ofType(ReceiptActions.ADD_RECEIPT_INVOICE_ITEM)
        .map((action) => action.payload)
        .switchMap((payload) => this.billingService.addReceiptInvoiceItems(payload.receipt, payload.invoice))
        .map((message) => this.receiptActions.addReceiptInvoiceItemsSuccess(message));
//        .withLatestFrom(this.store$.select(...this.RECEIPT_TASK))
//        .map(state => state[1])
//        .map(receipt => this.receiptActions.findReceiptItems(receipt));

}
