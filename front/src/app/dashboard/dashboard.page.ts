import {Component, OnInit, ChangeDetectionStrategy} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
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
  private POOLED_CREDIT_NOTE_TASKS: string[] = "billingModuleState.pooledCreditNoteTasks".split(".");
  private POOLED_DEBIT_NOTE_TASKS: string[] = "billingModuleState.pooledDebitNoteTasks".split(".");
  private POOLED_INVOICE_TASKS = "billingModuleState.pooledInvoiceTasks".split(".");

  private pooledCreditNoteTasks$: Observable<CreditNoteTask[]>;
  private pooledDebitNoteTasks$: Observable<DebitNoteTask[]>;
  private pooledInvoiceTasks$: Observable<InvoiceTask[]>;

  private tasks$: Observable<TaskList>;

  constructor(private router: Router,
              private billingStore: Store<BillingModuleState>,
              private creditNoteActions: CreditNoteActions,
              private debitNoteActions: DebitNoteActions,
              private invoiceActions: InvoiceActions,
              private route: ActivatedRoute) {
      this.pooledCreditNoteTasks$ = this.billingStore.select(...this.POOLED_CREDIT_NOTE_TASKS);
      this.pooledDebitNoteTasks$ = this.billingStore.select(...this.POOLED_DEBIT_NOTE_TASKS);
      this.pooledInvoiceTasks$ = this.billingStore.select(...this.POOLED_INVOICE_TASKS);
      
      this.tasks$ = Observable
      .combineLatest(
          this.pooledCreditNoteTasks$,
          this.pooledDebitNoteTasks$,
          this.pooledInvoiceTasks$,
          (
                  pooledCreditNoteTasks : Array<Task>,
                  pooledDebitNoteTasks : Array<Task>,
                  pooledInvoiceTasks : Array<Task>
          ) => {
              
              console.log(pooledInvoiceTasks)
              return {
                  list: pooledInvoiceTasks,
                  tasks: pooledInvoiceTasks,
                  total: pooledInvoiceTasks.length,
              }
          }
      );
  }

  ngOnInit(): void {
      console.log("find pooled tasks");
      //this.store.dispatch(this.actions.findAssignedCreditNoteTasks());
      this.billingStore.dispatch(this.creditNoteActions.findPooledCreditNoteTasks());
      this.billingStore.dispatch(this.debitNoteActions.findPooledDebitNoteTasks());
      this.billingStore.dispatch(this.invoiceActions.findPooledInvoiceTasks());
      //this.store.dispatch(this.actions.findArchivedCreditNotes());
    }
  
  view(task: Task) {
      console.log("taskId: " + task.taskId);
      this.router.navigate(['/billing/credit-notes/credit-note-task-detail', task.taskId]);
    }
}
