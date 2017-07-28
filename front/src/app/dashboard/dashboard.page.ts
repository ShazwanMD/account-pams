import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';
import {Task} from '../core/task.interface';
import {TaskList} from './task-list.interface';
import {Store} from '@ngrx/store';
import {BillingModuleState} from '../secure/billing/index';
import {CreditNoteActions} from '../secure/billing/credit-notes/credit-note.action';
import {DebitNoteActions} from '../secure/billing/debit-notes/debit-note.action';
import {InvoiceActions} from '../secure/billing/invoices/invoice.action';
import {ReceiptActions} from '../secure/billing/receipts/receipt.action';
import {FinancialaidModuleState} from '../secure/financialaid/index';
import {WaiverApplicationActions} from '../secure/financialaid/waiver-applications/waiver-application.action';
import {ReceiptTask} from '../shared/model/billing/receipt-task.interface';
import {WaiverApplicationTask} from '../shared/model/financialaid/waiver-application-task.interface';
import {InvoiceTask} from '../shared/model/billing/invoice-task.interface';
import {DebitNoteTask} from '../shared/model/billing/debit-note-task.interface';
import {CreditNoteTask} from '../shared/model/billing/credit-note-task.interface';

@Component({
  selector: 'pams-dashboard-page',
  templateUrl: './dashboard.page.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})

export class DashboardPage implements OnInit {
  private ASSIGNED_CREDIT_NOTES: string[] = 'billingModuleState.assignedCreditNoteTasks'.split('.');
  private ASSIGNED_DEBIT_NOTES: string[] = 'billingModuleState.assignedDebitNoteTasks'.split('.');
  private ASSIGNED_INVOICES: string[] = 'billingModuleState.assignedInvoiceTasks'.split('.');
  private ASSIGNED_RECEIPTS = 'billingModuleState.assignedReceiptTasks'.split('.');
  private ASSIGNED_WAIVER_APPLICATIONS = 'financialaidModuleState.assignedWaiverApplicationTasks'.split('.');

  private POOLED_CREDIT_NOTES: string[] = 'billingModuleState.pooledCreditNoteTasks'.split('.');
  private POOLED_DEBIT_NOTES: string[] = 'billingModuleState.pooledDebitNoteTasks'.split('.');
  private POOLED_INVOICES: string[] = 'billingModuleState.pooledInvoiceTasks'.split('.');
  private POOLED_RECEIPTS = 'billingModuleState.pooledReceiptTasks'.split('.');
  private POOLED_WAIVER_APPLICATIONS = 'financialaidModuleState.pooledWaiverApplicationTasks'.split('.');

  private assignedCreditNotes$: Observable<CreditNoteTask[]>;
  private assignedDebitNotes$: Observable<DebitNoteTask[]>;
  private assignedInvoices$: Observable<InvoiceTask[]>;
  private assignedReceipts$: Observable<ReceiptTask[]>;
  private assignedWaiverApplications$: Observable<WaiverApplicationTask>;

  private pooledCreditNotes$: Observable<CreditNoteTask[]>;
  private pooledDebitNotes$: Observable<DebitNoteTask[]>;
  private pooledInvoices$: Observable<InvoiceTask[]>;
  private pooledReceipts$: Observable<ReceiptTask[]>;
  private pooledWaiverApplications$: Observable<WaiverApplicationTask>;

  private assignedTasks$: Observable<TaskList>;
  private pooledTasks$: Observable<TaskList>;

  constructor(private router: Router,
              private billingStore: Store<BillingModuleState>,
              private financialaidStore: Store<FinancialaidModuleState>,
              private creditNoteActions: CreditNoteActions,
              private debitNoteActions: DebitNoteActions,
              private invoiceActions: InvoiceActions,
              private receiptActions: ReceiptActions,
              private waiverApplicationActions: WaiverApplicationActions) {
    this.assignedCreditNotes$ = this.billingStore.select(...this.ASSIGNED_CREDIT_NOTES);
    this.assignedDebitNotes$ = this.billingStore.select(...this.ASSIGNED_DEBIT_NOTES);
    this.assignedInvoices$ = this.billingStore.select(...this.ASSIGNED_INVOICES);
    this.assignedReceipts$ = this.billingStore.select(...this.ASSIGNED_RECEIPTS);
    this.assignedWaiverApplications$ = this.financialaidStore.select(...this.ASSIGNED_WAIVER_APPLICATIONS);

    this.pooledCreditNotes$ = this.billingStore.select(...this.POOLED_CREDIT_NOTES);
    this.pooledDebitNotes$ = this.billingStore.select(...this.POOLED_DEBIT_NOTES);
    this.pooledInvoices$ = this.billingStore.select(...this.POOLED_INVOICES);
    this.pooledReceipts$ = this.billingStore.select(...this.POOLED_RECEIPTS);
    this.pooledWaiverApplications$ = this.financialaidStore.select(...this.POOLED_WAIVER_APPLICATIONS);

    this.assignedTasks$ = Observable
      .combineLatest(
        this.assignedCreditNotes$,
        this.assignedDebitNotes$,
        this.assignedInvoices$,
        this.assignedReceipts$,
        this.assignedWaiverApplications$,
        (creditNotes: Task[],
         debitNotes: Task[],
         invoices: Task[],
         receipts: Task[],
         assignedWaiverApplications: Task[],) => {
          const tasks = [creditNotes, debitNotes, invoices, receipts, assignedWaiverApplications];
          const combined = [].concat(...tasks);
          return {
            list: combined,
            total: combined.length,
          };
        },
      );

    this.pooledTasks$ = Observable
      .combineLatest(
        this.pooledCreditNotes$,
        this.pooledDebitNotes$,
        this.pooledInvoices$,
        this.pooledReceipts$,
        this.pooledWaiverApplications$,
        (creditNotes: Task[],
         debitNotes: Task[],
         invoices: Task[],
         receipts: Task[],
         pooledWaiverApplications: Task[],) => {
          const tasks = [creditNotes, debitNotes, invoices, receipts, pooledWaiverApplications];
          const combined = [].concat(...tasks);
          return {
            list: combined,
            total: combined.length,
          };
        },
      );
  }

  ngOnInit(): void {
    this.billingStore.dispatch(this.creditNoteActions.findAssignedCreditNoteTasks());
    this.billingStore.dispatch(this.debitNoteActions.findAssignedDebitNoteTasks());
    this.billingStore.dispatch(this.invoiceActions.findAssignedInvoiceTasks());
    this.billingStore.dispatch(this.receiptActions.findAssignedReceiptTasks());
    this.financialaidStore.dispatch(this.waiverApplicationActions.findAssignedWaiverApplicationTasks());

    this.billingStore.dispatch(this.creditNoteActions.findPooledCreditNoteTasks());
    this.billingStore.dispatch(this.debitNoteActions.findPooledDebitNoteTasks());
    this.billingStore.dispatch(this.invoiceActions.findPooledInvoiceTasks());
    this.billingStore.dispatch(this.receiptActions.findPooledReceiptTasks());
    this.financialaidStore.dispatch(this.waiverApplicationActions.findPooledWaiverApplicationTasks());
  }

  viewAssignedTask(task: Task) {
    let uri = task['invoice'] ? '/billing/invoices/invoice-task-detail' :
      task['debitNote'] ? '/billing/debit-notes/debit-note-task-detail' :
        task['creditNote'] ? '/billing/credit-notes/credit-note-task-detail' :
          task['receipt'] ? '/billing/receipts/view-task' :
            task['application'] ? '/financialaid/waiver-applications/view-task' : null;
    this.router.navigate([uri, task.taskId]);
  }

  claimTask(task: Task) {
    if (task['invoice'])
      this.billingStore.dispatch(this.invoiceActions.claimInvoiceTask(task));
    else if (task['debitNote'])
      this.billingStore.dispatch(this.debitNoteActions.claimDebitNoteTask(task));
    else if (task['creditNote'])
      this.billingStore.dispatch(this.creditNoteActions.claimCreditNoteTask(task));
    else if (task['receipt'])
      this.billingStore.dispatch(this.receiptActions.claimReceiptTask(task));
    else if (task['application'])
      this.financialaidStore.dispatch(this.waiverApplicationActions.claimWaiverApplicationTask(task));
  }

  viewPooledTask(task: Task) {
    let uri = task['invoice'] ? '/billing/invoices/invoice-task-detail' :
      task['debitNote'] ? '/billing/debit-notes/debit-note-task-detail' :
        task['creditNote'] ? '/billing/credit-notes/credit-note-task-detail' :
          task['receipt'] ? '/billing/receipts/view-task' :
            task['application'] ? '/financialaid/waiver-applications/view-task' : null;
    this.router.navigate([uri, task.taskId]);
  }
}
