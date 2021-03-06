import {Injectable} from '@angular/core';
import {Actions, Effect} from '@ngrx/effects';
import {InvoiceActions} from './invoice.action';
import {from} from 'rxjs/observable/from';
import {BillingService} from '../../../../services/billing.service';
import {BillingModuleState} from '../index';
import {Store} from '@ngrx/store';
import 'rxjs/add/operator/withLatestFrom';
import {DebitNoteActions} from '../debit-notes/debit-note.action';
import { Router } from "@angular/router";
import { NotificationService } from "../../../../services/notification.service";

@Injectable()
export class InvoiceEffects {

  private INVOICE_TASK: string[] = 'billingModuleState.invoiceTask'.split('.');
  private INVOICE: string[] = 'billingModuleState.invoice'.split('.');

  constructor(private actions$: Actions,
              private invoiceActions: InvoiceActions,
              private debitNoteActions: DebitNoteActions,
              private billingService: BillingService,
              private notificationService: NotificationService,
              private router: Router,
              private store$: Store<BillingModuleState>) {
  }

  @Effect() findUnpaidInvoices$ = this.actions$
    .ofType(InvoiceActions.FIND_UNPAID_INVOICES)
    .map((action) => action.payload)
    .switchMap((account) => this.billingService.findUnpaidInvoices(account))
    .map((invoices) => this.invoiceActions.findUnpaidInvoicesSuccess(invoices));

  @Effect() findCompletedInvoices$ = this.actions$
    .ofType(InvoiceActions.FIND_COMPLETED_INVOICES)
    .switchMap(() => this.billingService.findCompletedInvoices())
    .map((invoices) => this.invoiceActions.findCompletedInvoicesSuccess(invoices));

  @Effect() findArchivedInvoices$ = this.actions$
    .ofType(InvoiceActions.FIND_ARCHIVED_INVOICES)
    .switchMap(() => this.billingService.findArchivedInvoices())
    .map((invoices) => this.invoiceActions.findArchivedInvoicesSuccess(invoices));

 @Effect() removeInvoiceTask$ = this.actions$
    .ofType(InvoiceActions.REMOVE_INVOICE_TASK)
    .map((action) => action.payload)
    .switchMap((invoiceTask) => this.billingService.removeInvoiceTask(invoiceTask))
    .map((task) => this.invoiceActions.removeInvoiceTaskSuccess(task))
    .mergeMap((action) => from([action,
                                this.invoiceActions.findAssignedInvoiceTasks(),
                                this.invoiceActions.findPooledInvoiceTasks(),
                                this.invoiceActions.findArchivedInvoices(),
      ],
    ));

  @Effect() findAssignedInvoiceTasks$ = this.actions$
    .ofType(InvoiceActions.FIND_ASSIGNED_INVOICE_TASKS)
    .switchMap(() => this.billingService.findAssignedInvoiceTasks())
    .map((invoices) => this.invoiceActions.findAssignedInvoiceTasksSuccess(invoices));

  @Effect() findPooledInvoiceTasks$ = this.actions$
    .ofType(InvoiceActions.FIND_POOLED_INVOICE_TASKS)
    .switchMap(() => this.billingService.findPooledInvoiceTasks())
    .map((invoices) => this.invoiceActions.findPooledInvoiceTasksSuccess(invoices));

  @Effect() findInvoiceTaskByTaskId = this.actions$
    .ofType(InvoiceActions.FIND_INVOICE_TASK_BY_TASK_ID)
    .map((action) => action.payload)
    .switchMap((taskId) => this.billingService.findInvoiceTaskByTaskId(taskId))
    .map((task) => this.invoiceActions.findInvoiceTaskByTaskIdSuccess(task));

  @Effect() findInvoiceByReferenceNo$ = this.actions$
    .ofType(InvoiceActions.FIND_INVOICE_BY_REFERENCE_NO)
    .map((action) => action.payload)
    .switchMap((referenceNo) => this.billingService.findInvoiceByReferenceNo(referenceNo))
    .map((invoice) => this.invoiceActions.findInvoiceByReferenceNoSuccess(invoice))
    .mergeMap((action) => from([
      action,
      this.invoiceActions.findInvoiceItems(action.payload),
      this.invoiceActions.findDebitNotesByInvoice(action.payload),
      this.invoiceActions.findCreditNotesByInvoice(action.payload),
    ]));

  @Effect() findInvoiceItems$ = this.actions$
    .ofType(InvoiceActions.FIND_INVOICE_ITEMS)
    .map((action) => action.payload)
    .switchMap((invoice) => this.billingService.findInvoiceItems(invoice))
    .map((items) => this.invoiceActions.findInvoiceItemsSuccess(items));

  @Effect() startInvoiceTask$ = this.actions$
    .ofType(InvoiceActions.START_INVOICE_TASK)
    .map((action) => action.payload)
    .switchMap((invoice) => this.billingService.startInvoiceTask(invoice))
    .map((referenceNo) => this.invoiceActions.startInvoiceTaskSuccess(referenceNo))
    .mergeMap((action) => from([action,
        this.invoiceActions.findAssignedInvoiceTasks(),
        this.invoiceActions.findPooledInvoiceTasks(),
      ],
    ));

  @Effect() completeInvoiceTask$ = this.actions$
    .ofType(InvoiceActions.COMPLETE_INVOICE_TASK)
    .map((action) => action.payload)
    .switchMap((invoiceTask) => this.billingService.completeInvoiceTask(invoiceTask))
    .map((message) => this.invoiceActions.completeInvoiceTaskSuccess(message))
    .catch((error) => this.notificationService.showError(error))
    .mergeMap((action) => from([action,
        this.invoiceActions.findAssignedInvoiceTasks(),
        this.invoiceActions.findPooledInvoiceTasks(),
        this.invoiceActions.findArchivedInvoices(),
      ],
    ));

  @Effect() claimInvoiceTask$ = this.actions$
    .ofType(InvoiceActions.CLAIM_INVOICE_TASK)
    .map((action) => action.payload)
    .switchMap((invoiceTask) => this.billingService.claimInvoiceTask(invoiceTask))
    .map((message) => this.invoiceActions.claimInvoiceTaskSuccess(message))
    .mergeMap((action) => from([action,
        this.invoiceActions.findAssignedInvoiceTasks(),
        this.invoiceActions.findPooledInvoiceTasks(),
      ],
    ));

  @Effect() releaseInvoiceTask$ = this.actions$
    .ofType(InvoiceActions.RELEASE_INVOICE_TASK)
    .map((action) => action.payload)
    .switchMap((invoiceTask) => this.billingService.releaseInvoiceTask(invoiceTask))
    .map((message) => this.invoiceActions.releaseInvoiceTaskSuccess(message))
    .mergeMap((action) => from([action,
        this.invoiceActions.findAssignedInvoiceTasks(),
        this.invoiceActions.findPooledInvoiceTasks(),
      ],
    ));

  @Effect() cancelInvoice$ = this.actions$
    .ofType(InvoiceActions.CANCEL_INVOICE)
    .map((action) => action.payload)
    .switchMap((invoice) => this.billingService.cancelInvoice(invoice))
    .map((invoice) => this.invoiceActions.cancelInvoiceSuccess(invoice));
//  .withLatestFrom(this.store$.select(...this.INVOICE))
//  .map(state => state[1])
//  .map(invoice => this.invoiceActions.findInvoiceByReferenceNo(invoice));

  @Effect() updateInvoice$ = this.actions$
    .ofType(InvoiceActions.UPDATE_INVOICE)
    .map((action) => action.payload)
    .switchMap((invoice) => this.billingService.updateInvoice(invoice))
    .map((invoice) => this.invoiceActions.updateInvoiceSuccess(invoice));

  @Effect() addInvoiceItem$ =
    this.actions$
      .ofType(InvoiceActions.ADD_INVOICE_ITEM)
      .map((action) => action.payload)
      .switchMap((payload) => this.billingService.addInvoiceItem(payload.invoice, payload.item))
      .map((message) => this.invoiceActions.addInvoiceItemSuccess(message))
      .withLatestFrom(this.store$.select(...this.INVOICE_TASK))
      .map((state) => state[1])
      .map((invoice) => this.invoiceActions.findInvoiceItems(invoice))
  .do((action) => this.router.navigate(['/secure/billing/invoices/invoice-task-detail/:taskId', action.payload])).ignoreElements();

  @Effect() updateInvoiceItem$ = this.actions$
    .ofType(InvoiceActions.UPDATE_INVOICE_ITEM)
    .map((action) => action.payload)
    .switchMap((payload) => this.billingService.updateInvoiceItem(payload.invoice, payload.item))
    .map((message) => this.invoiceActions.updateInvoiceItemSuccess(message))
    .withLatestFrom(this.store$.select(...this.INVOICE_TASK))
    .map((state) => state[1])
    .map((invoice) => this.invoiceActions.findInvoiceItems(invoice))
  .do((action) => this.router.navigate(['/secure/billing/invoices/invoice-task-detail/:taskId', action.payload])).ignoreElements();

  @Effect() deleteInvoiceItem$ = this.actions$
    .ofType(InvoiceActions.DELETE_INVOICE_ITEM)
    .map((action) => action.payload)
    .flatMap((payload) => this.billingService.deleteInvoiceItem(payload.invoice, payload.item))
    .map((message) => this.invoiceActions.deleteInvoiceItemSuccess(message))
    .withLatestFrom(this.store$.select(...this.INVOICE_TASK))
    .map((state) => state[1])
    .map((invoice) => this.invoiceActions.findInvoiceItems(invoice))
  .do((action) => this.router.navigate(['/secure/billing/invoices/invoice-task-detail/:taskId', action.payload])).ignoreElements();

  @Effect() findDebitNotesByInvoice = this.actions$
    .ofType(InvoiceActions.FIND_DEBIT_NOTES_BY_INVOICE)
    .map((action) => action.payload)
    .switchMap((invoice) => this.billingService.findDebitNotesByInvoice(invoice))
    .map((notes) => this.invoiceActions.findDebitNotesByInvoiceSuccess(notes));

  @Effect() findCreditNotesByInvoice = this.actions$
    .ofType(InvoiceActions.FIND_CREDIT_NOTES_BY_INVOICE)
    .map((action) => action.payload)
    .switchMap((invoice) => this.billingService.findCreditNotesByInvoice(invoice))
    .map((notes) => this.invoiceActions.findCreditNotesByInvoiceSuccess(notes));

}
