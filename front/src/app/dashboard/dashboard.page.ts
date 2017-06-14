import {Component, OnInit, ChangeDetectionStrategy} from '@angular/core';
import {Router} from '@angular/router';
import {Observable} from "rxjs";
import { Task } from "../core/task.interface";
import { TaskList } from "./task-list.interface";
import { Store } from "@ngrx/store";
import { BillingModuleState } from "../billing/index";
import { CreditNoteTask } from "../billing/credit-notes/credit-note-task.interface";
import { CreditNoteActions } from "../billing/credit-notes/credit-note.action";
import { DebitNoteTask } from "../billing/debit-notes/debit-note-task.interface";
import { DebitNoteActions } from "../billing/debit-notes/debit-note.action";
import { InvoiceTask } from "../billing/invoices/invoice-task.interface";
import { InvoiceActions } from "../billing/invoices/invoice.action";

@Component({
  selector: 'pams-dashboard-page',
  templateUrl: './dashboard.page.html',
  changeDetection: ChangeDetectionStrategy.OnPush
})

export class DashboardPage implements OnInit {
  private ASSIGNED_CREDIT_NOTES: string[] = "billingModuleState.assignedCreditNoteTasks".split(".");
  private ASSIGNED_DEBIT_NOTES: string[] = "billingModuleState.assignedDebitNoteTasks".split(".");
  private ASSIGNED_INVOICES: string[] = "billingModuleState.assignedInvoiceTasks".split(".");
  
  private POOLED_CREDIT_NOTES: string[] = "billingModuleState.pooledCreditNoteTasks".split(".");
  private POOLED_DEBIT_NOTES: string[] = "billingModuleState.pooledDebitNoteTasks".split(".");
  private POOLED_INVOICES: string[] = "billingModuleState.pooledInvoiceTasks".split(".");

  private assignedCreditNotes$: Observable<CreditNoteTask[]>;
  private assignedDebitNotes$: Observable<DebitNoteTask[]>;
  private assignedInvoices$: Observable<InvoiceTask[]>;
  
  private pooledCreditNotes$: Observable<CreditNoteTask[]>;
  private pooledDebitNotes$: Observable<DebitNoteTask[]>;
  private pooledInvoices$: Observable<InvoiceTask[]>;

  private assignedTasks$: Observable<TaskList>;
  private pooledTasks$: Observable<TaskList>;

  constructor(private router: Router,
              private billingStore: Store<BillingModuleState>,
              private creditNoteActions: CreditNoteActions,
              private debitNoteActions: DebitNoteActions,
              private invoiceActions: InvoiceActions) {
      this.assignedCreditNotes$ = this.billingStore.select(...this.ASSIGNED_CREDIT_NOTES);
      this.assignedDebitNotes$ = this.billingStore.select(...this.ASSIGNED_DEBIT_NOTES);
      this.assignedInvoices$ = this.billingStore.select(...this.ASSIGNED_INVOICES);
      
      this.pooledCreditNotes$ = this.billingStore.select(...this.POOLED_CREDIT_NOTES);
      this.pooledDebitNotes$ = this.billingStore.select(...this.POOLED_DEBIT_NOTES);
      this.pooledInvoices$ = this.billingStore.select(...this.POOLED_INVOICES);
      
      this.assignedTasks$ = Observable
      .combineLatest(
          this.assignedCreditNotes$,
          this.assignedDebitNotes$,
          this.assignedInvoices$,
          (
              creditNotes : Array<Task>,
              debitNotes : Array<Task>,
              invoices : Array<Task>
          ) => {
              const tasks = [creditNotes, debitNotes, invoices];
              const combined = [].concat(...tasks);
              return {
                  list: combined,
                  total: combined.length,
              }
          }
      );
      
      this.pooledTasks$ = Observable
      .combineLatest(
          this.pooledCreditNotes$,
          this.pooledDebitNotes$,
          this.pooledInvoices$,
          (
              creditNotes : Array<Task>,
              debitNotes : Array<Task>,
              invoices : Array<Task>
          ) => {
              const tasks = [creditNotes, debitNotes, invoices];
              const combined = [].concat(...tasks);
              return {
                  list: combined,
                  total: combined.length,
              }
          }
      );
  }

  ngOnInit(): void {
      this.billingStore.dispatch(this.creditNoteActions.findAssignedCreditNoteTasks());
      this.billingStore.dispatch(this.debitNoteActions.findAssignedDebitNoteTasks());
      this.billingStore.dispatch(this.invoiceActions.findAssignedInvoiceTasks());
      
      this.billingStore.dispatch(this.creditNoteActions.findPooledCreditNoteTasks());
      this.billingStore.dispatch(this.debitNoteActions.findPooledDebitNoteTasks());
      this.billingStore.dispatch(this.invoiceActions.findPooledInvoiceTasks());
    }
  
  viewAssignedTask(task: Task) {
      console.log("task",task);
      let uri = task['invoice'] ? '/invoices/invoice-task-detail' :
                  task['debitNote'] ? '/debit-notes/debit-note-task-detail' :
                  task['creditNote'] ? '/credit-notes/credit-note-task-detail' : null;
      this.router.navigate(['/billing' + uri, task.taskId]);
    }
  
  claimTask(task: Task) {
      console.log("task",task);
//      let action = task['invoice'] ? this.billingStore.dispatch(this.invoiceActions.claimInvoiceTask(task)) :
//      task['debitNote'] ? this.billingStore.dispatch(this.debitNoteActions.claimDebitNoteTask(task)) :
//      task['creditNote'] ? this.billingStore.dispatch(this.creditNoteActions.claimCreditNoteTask(task)) : undefined;
      
      this.billingStore.dispatch(
          task['invoice'] ? this.invoiceActions.claimInvoiceTask(task) :
          task['debitNote'] ? this.debitNoteActions.claimDebitNoteTask(task) :
          task['creditNote'] ? this.creditNoteActions.claimCreditNoteTask(task) : null
      );
      
//      if(task['invoice'])
//          this.billingStore.dispatch(this.invoiceActions.claimInvoiceTask(task));
//      else if(task['debitNote'])
//          this.billingStore.dispatch(this.debitNoteActions.claimDebitNoteTask(task));
//      else if(task['creditNote'])
//          this.billingStore.dispatch(this.creditNoteActions.claimCreditNoteTask(task));
    }
  
  viewPooledTask(task: Task) {
      console.log("task",task);
      let uri = task['invoice'] ? '/invoices/invoice-task-detail' :
                  task['debitNote'] ? '/debit-notes/debit-note-task-detail' :
                  task['creditNote'] ? '/credit-notes/credit-note-task-detail' : null;
      this.router.navigate(['/billing' + uri, task.taskId]);
    }
}
