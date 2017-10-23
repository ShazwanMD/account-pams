import { AccountActions } from './../../account/accounts/account.action';
import { AccountCharge } from './../../../shared/model/account/account-charge.interface';
import {Injectable} from '@angular/core';
import {Actions, Effect} from '@ngrx/effects';
import {ReceiptActions} from './receipt.action';
import {from} from 'rxjs/observable/from';
import {BillingService} from '../../../../services/billing.service';
import {BillingModuleState} from '../index';
import {Store} from '@ngrx/store';
import { AccountService } from '../../../../services/account.service';
import { Router } from "@angular/router";
import { Receipt } from './../../../shared/model/billing/receipt.interface';
import {Account} from '../../../shared/model/account/account.interface';

@Injectable()
export class ReceiptEffects {

  private RECEIPT_TASK: string[] = 'billingModuleState.receiptTask'.split('.');
  private RECEIPT: string[] = 'billingModuleState.receipt'.split('.');
  private ACCOUNT: string[] = 'accountModuleState.account'.split('.');


  constructor(private actions$: Actions,
              private receiptActions: ReceiptActions, 
              private accountActions: AccountActions,                   
              private billingService: BillingService,
              private accountService: AccountService,
              private router: Router,
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
  
  @Effect() findArchivedReceipts$ = this.actions$
  .ofType(ReceiptActions.FIND_ARCHIVED_RECEIPT)
  .switchMap(() => this.billingService.findArchivedReceipts())
  .map((receipts) => this.receiptActions.findArchivedReceiptsSuccess(receipts));

  @Effect() findReceiptTaskByTaskId = this.actions$
    .ofType(ReceiptActions.FIND_RECEIPT_TASK_BY_TASK_ID)
    .map((action) => action.payload)
    .switchMap((taskId) => this.billingService.findReceiptTaskByTaskId(taskId))
    .map((task) => this.receiptActions.findReceiptTaskByTaskIdSuccess(task))
    .mergeMap((action) => from([action,
        this.receiptActions.findReceiptItems(action.payload),
        //this.receiptActions.findReceiptsByInvoice(action.payload),
      ],
    ));

  @Effect() findUnpaidInvoices$ = this.actions$
  .ofType(ReceiptActions.FIND_UNPAID_INVOICES)
  .map((action) => action.payload)
  .switchMap((account) => this.billingService.findUnpaidInvoices(account))
  .map((invoices) => this.receiptActions.findUnpaidInvoicesSuccess(invoices));
  
  @Effect() findReceiptByReferenceNo$ = this.actions$
    .ofType(ReceiptActions.FIND_RECEIPT_BY_REFERENCE_NO)
    .map((action) => action.payload)
    .switchMap((referenceNo) => this.billingService.findReceiptByReferenceNo(referenceNo))
    .map((receipt) => this.receiptActions.findReceiptByReferenceNoSuccess(receipt))
    .mergeMap((action) => from([action, this.receiptActions.findReceiptItems(action.payload)]));

  @Effect() findReceiptsByInvoice$ = this.actions$
  .ofType(ReceiptActions.FIND_RECEIPTS_BY_INVOICE)
  .map((action) => action.payload)
  .switchMap((receipt) => this.billingService.findReceiptsByInvoice(receipt))
  .map((receipts) => this.receiptActions.findReceiptsByInvoiceSuccess(receipts));

  @Effect() findReceiptsByAccountCharge$ = this.actions$
  .ofType(ReceiptActions.FIND_RECEIPTS_BY_ACCOUNT_CHARGE)
  .map((action) => action.payload)
  .switchMap((receipt) => this.billingService.findReceiptsByAccountCharge(receipt))
  .map((receipts) => this.receiptActions.findReceiptsByAccountChargeSuccess(receipts));

  @Effect() findReceiptsByDebitNote$ = this.actions$
  .ofType(ReceiptActions.FIND_RECEIPTS_BY_DEBIT_NOTE)
  .map((action) => action.payload)
  .switchMap((receipt) => this.billingService.findReceiptsByDebitNote(receipt))
  .map((receipts) => this.receiptActions.findReceiptsByDebitNoteSuccess(receipts));
  
  @Effect() findReceiptItems$ = this.actions$
    .ofType(ReceiptActions.FIND_RECEIPT_ITEMS)
    .map((action) => action.payload)
    .switchMap((receipt) => this.billingService.findReceiptItems(receipt))
    .map((items) => this.receiptActions.findReceiptItemsSuccess(items));

  @Effect() findInvoiceReceiptItems$ = this.actions$
  .ofType(ReceiptActions.FIND_RECEIPT_INVOICE_ITEMS)
  .map((action) => action.payload)
  .switchMap((payload) => this.billingService.findInvoiceReceiptItems(payload.receipt, payload.invoice))
  .map((message) => this.receiptActions.findInvoiceReceiptItemsSuccess(message));
  
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
        this.receiptActions.findArchivedReceipts(),
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
  
  @Effect() itemToReceiptItem$ =
      this.actions$
        .ofType(ReceiptActions.ITEM_TO_RECEIPT_ITEM)
        .map((action) => action.payload)
        .switchMap((payload) => this.billingService.itemToReceiptItem(payload.invoice, payload.receipt))
        .map((message) => this.receiptActions.itemToReceiptItemSuccess(message))
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
      .map((message) => this.receiptActions.addReceiptInvoiceItemsSuccess(message))
      .withLatestFrom(this.store$.select(...this.RECEIPT_TASK))
      .map((state) => state[1])
      .map((receipt) => this.receiptActions.findReceiptsByInvoice(receipt));

  
  @Effect() updateItemToReceipt$ = this.actions$
  .ofType(ReceiptActions.UPDATE_ITEM_RECEIPT)
  .map((action) => action.payload)
  .switchMap((payload) => this.billingService.updateItemToReceipt(payload.receipt, payload.item))
  .map((message) => this.receiptActions.updateItemToReceiptSuccess(message))
  .withLatestFrom(this.store$.select(...this.RECEIPT_TASK))
  .map((state) => state[1])
  .map((receipt) => this.receiptActions.findReceiptItems(receipt))
  .do((action) => this.router.navigate(['/secure/billing/receipts/view-task/:taskId', action.payload])).ignoreElements();

  @Effect() findCompletedAccountCharges$ = this.actions$
  .ofType(ReceiptActions.FIND_COMPLETED_ACCOUNT_CHARGES)
  .switchMap(() => this.accountService.findCompletedAccountCharges())
  .map((charges) => this.receiptActions.findCompletedAccountChargesSuccess(charges));
  
  @Effect() removeReceiptTask$ = this.actions$
  .ofType(ReceiptActions.REMOVE_RECEIPT_TASK)
  .map((action) => action.payload)
  .switchMap((receiptTask) => this.billingService.removeReceiptTask(receiptTask))
  .map((task) => this.receiptActions.removeReceiptTaskSuccess(task))
  .mergeMap((action) => from([action,
                              this.receiptActions.findAssignedReceiptTasks(),
                              this.receiptActions.findPooledReceiptTasks(),
                              this.receiptActions.findArchivedReceipts(),
    ],
  ));
  
  @Effect() addReceiptCharge$ =
      this.actions$
        .ofType(ReceiptActions.ADD_RECEIPT_CHARGE)
        .map((action) => action.payload)
        .switchMap((payload) => this.billingService.addReceiptCharge(payload.receipt, payload.charge))
        .map((message) => this.receiptActions.addReceiptChargeSuccess(message))
        .withLatestFrom(this.store$.select(...this.RECEIPT_TASK))
        .map((state) => state[1])
        .map((receipt) => this.receiptActions.findReceiptsByAccountCharge(receipt));


  @Effect() addReceiptDebitNote$ =
      this.actions$
        .ofType(ReceiptActions.ADD_RECEIPT_DEBIT_NOTE)
        .map((action) => action.payload)
        .switchMap((payload) => this.billingService.addReceiptDebitNote(payload.receipt, payload.debitNote))
        .map((message) => this.receiptActions.addReceiptChargeSuccess(message))
        .withLatestFrom(this.store$.select(...this.RECEIPT_TASK))
        .map((state) => state[1])
        .map((receipt) => this.receiptActions.findReceiptsByDebitNote(receipt));
 }

 
