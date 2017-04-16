import {Injectable} from '@angular/core';
import {Effect, Actions} from '@ngrx/effects';
import {InvoiceActions} from "./invoice.action";
import {from} from "rxjs/observable/from";
import {BillingService} from "../../../services/billing.service";


@Injectable()
export class InvoiceEffects {
  constructor(private actions$: Actions,
              private invoiceActions: InvoiceActions,
              private billingService: BillingService) {
  }

  @Effect() findAssignedInvoiceTasks$ = this.actions$
    .ofType(InvoiceActions.FIND_ASSIGNED_INVOICE_TASKS)
    .switchMap(() => this.billingService.findAssignedInvoiceTasks())
    .map(invoices => this.invoiceActions.findAssignedInvoiceTasksSuccess(invoices));


  @Effect() findPooledInvoiceTasks$ = this.actions$
    .ofType(InvoiceActions.FIND_POOLED_INVOICE_TASKS)
    .switchMap(() => this.billingService.findPooledInvoiceTasks())
    .map(invoices => this.invoiceActions.findPooledInvoiceTasksSuccess(invoices));

  @Effect() findInvoiceByReferenceNo$ = this.actions$
    .ofType(InvoiceActions.FIND_INVOICE_BY_ID)
    .map(action => action.payload)
    .switchMap(code => this.billingService.findInvoiceByReferenceNo(code))
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
    .map(task => this.invoiceActions.startInvoiceTaskSuccess(task));

  @Effect() completeInvoiceTask$ = this.actions$
    .ofType(InvoiceActions.COMPLETE_INVOICE_TASK)
    .map(action => action.payload);
  // todo
    // .switchMap(invoice => this.billingService.startInvoiceTask(invoice))
    // .map(task => this.invoiceActions.startInvoiceTaskSuccess(task));

  @Effect() assignInvoiceTask$ = this.actions$
    .ofType(InvoiceActions.ASSIGN_INVOICE_TASK)
    .map(action => action.payload);
  // todo
    // .switchMap(invoice => this.billingService.startInvoiceTask(invoice))
    // .map(task => this.invoiceActions.startInvoiceTaskSuccess(task));


  @Effect() releaseInvoiceTask$ = this.actions$
    .ofType(InvoiceActions.RELEASE_INVOICE_TASK)
    .map(action => action.payload);
  // todo
    // .switchMap(invoice => this.billingService.startInvoiceTask(invoice))
    // .map(task => this.invoiceActions.startInvoiceTaskSuccess(task));

  @Effect() updateInvoice$ = this.actions$
    .ofType(InvoiceActions.UPDATE_INVOICE)
    .map(action => action.payload)
    .switchMap(invoice => this.billingService.updateInvoice(invoice))
    .map(invoice => this.invoiceActions.updateInvoiceSuccess(invoice));
}
