import {Injectable} from '@angular/core';
import {Effect, Actions} from '@ngrx/effects';
import {InvoiceActions} from "./invoice.action";
import {from} from "rxjs/observable/from";
import {BillingService} from "../../../services/billing.service";
import {BillingModuleState} from "../index";
import {Store} from "@ngrx/store";
import 'rxjs/add/operator/withLatestFrom';


@Injectable()
export class InvoiceEffects {

  private INVOICE_TASK = "billingModuleState.invoiceTask".split(".");

  constructor(private actions$: Actions,
              private invoiceActions: InvoiceActions,
              private billingService: BillingService,
              private store$: Store<BillingModuleState>) {
  }

  @Effect() findAssignedInvoiceTasks$ = this.actions$
    .ofType(InvoiceActions.FIND_ASSIGNED_INVOICE_TASKS)
    .switchMap(() => this.billingService.findAssignedInvoiceTasks())
    .map(invoices => this.invoiceActions.findAssignedInvoiceTasksSuccess(invoices));


  @Effect() findPooledInvoiceTasks$ = this.actions$
    .ofType(InvoiceActions.FIND_POOLED_INVOICE_TASKS)
    .switchMap(() => this.billingService.findPooledInvoiceTasks())
    .map(invoices => this.invoiceActions.findPooledInvoiceTasksSuccess(invoices));

  @Effect() findInvoiceTaskByTaskId = this.actions$
    .ofType(InvoiceActions.FIND_INVOICE_TASK_BY_TASK_ID)
    .map(action => action.payload)
    .switchMap(taskId => this.billingService.findInvoiceTaskByTaskId(taskId))
    .map(task => this.invoiceActions.findInvoiceTaskByTaskIdSuccess(task));

  @Effect() findInvoiceByReferenceNo$ = this.actions$
    .ofType(InvoiceActions.FIND_INVOICE_BY_REFERENCE_NO)
    .map(action => action.payload)
    .switchMap(referenceNo => this.billingService.findInvoiceByReferenceNo(referenceNo))
    .map(invoice => this.invoiceActions.findInvoiceByReferenceNoSuccess(invoice))
    .mergeMap(action => from([action, this.invoiceActions.findInvoiceItems(action.payload)]));

  @Effect() findInvoiceItems$ = this.actions$
    .ofType(InvoiceActions.FIND_INVOICE_ITEMS)
    .map(action => action.payload)
    .switchMap(invoice => this.billingService.findInvoiceItems(invoice))
    .map(items => this.invoiceActions.findInvoiceItemsSuccess(items));

  @Effect() startInvoiceTask$ = this.actions$
    .ofType(InvoiceActions.START_INVOICE_TASK)
    .map(action => action.payload)
    .switchMap(invoice => this.billingService.startInvoiceTask(invoice))
    .mergeMap(action => from([action,
        this.invoiceActions.findAssignedInvoiceTasks(),
        this.invoiceActions.findPooledInvoiceTasks()
      ]
    ));

  @Effect() completeInvoiceTask$ = this.actions$
    .ofType(InvoiceActions.COMPLETE_INVOICE_TASK)
    .map(action => action.payload)
    .switchMap(invoiceTask => this.billingService.completeInvoiceTask(invoiceTask))
    .map(message => this.invoiceActions.completeInvoiceTaskSuccess(message))
    .mergeMap(action => from([action,
        this.invoiceActions.findAssignedInvoiceTasks(),
        this.invoiceActions.findPooledInvoiceTasks()
      ]
    ));

  @Effect() claimInvoiceTask$ = this.actions$
    .ofType(InvoiceActions.CLAIM_INVOICE_TASK)
    .map(action => action.payload)
    .switchMap(invoiceTask => this.billingService.claimInvoiceTask(invoiceTask))
    .map(message => this.invoiceActions.claimInvoiceTaskSuccess(message))
    .mergeMap(action => from([action,
        this.invoiceActions.findAssignedInvoiceTasks(),
        this.invoiceActions.findPooledInvoiceTasks()
      ]
    ));

  @Effect() releaseInvoiceTask$ = this.actions$
    .ofType(InvoiceActions.RELEASE_INVOICE_TASK)
    .map(action => action.payload)
    .switchMap(invoiceTask => this.billingService.releaseInvoiceTask(invoiceTask))
    .map(message => this.invoiceActions.releaseInvoiceTaskSuccess(message))
    .mergeMap(action => from([action,
        this.invoiceActions.findAssignedInvoiceTasks(),
        this.invoiceActions.findPooledInvoiceTasks()
      ]
    ));

  @Effect() updateInvoice$ = this.actions$
    .ofType(InvoiceActions.UPDATE_INVOICE)
    .map(action => action.payload)
    .switchMap(invoice => this.billingService.updateInvoice(invoice))
    .map(invoice => this.invoiceActions.updateInvoiceSuccess(invoice));

  @Effect() addInvoiceItem$ =
    this.actions$
      .ofType(InvoiceActions.ADD_INVOICE_ITEM)
      .map(action => action.payload)
      .switchMap(payload => this.billingService.addInvoiceItem(payload.invoice, payload.item))
      .map(message => this.invoiceActions.addInvoiceItemSuccess(message))
      .withLatestFrom(this.store$.select(...this.INVOICE_TASK))
      .map(state => state[1])
      .map(invoice => this.invoiceActions.findInvoiceItems(invoice));
}
