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
  private CREDIT_NOTES: string[] = "billingModuleState.assignedCreditNoteTasks".split(".");
  private DEBIT_NOTES: string[] = "billingModuleState.assignedDebitNoteTasks".split(".");
  private INVOICES = "billingModuleState.assignedInvoiceTasks".split(".");

  private creditNotes$: Observable<CreditNoteTask[]>;
  private debitNotes$: Observable<DebitNoteTask[]>;
  private invoices$: Observable<InvoiceTask[]>;

  private tasks$: Observable<TaskList>;

  constructor(private router: Router,
              private billingStore: Store<BillingModuleState>,
              private creditNoteActions: CreditNoteActions,
              private debitNoteActions: DebitNoteActions,
              private invoiceActions: InvoiceActions) {
      this.creditNotes$ = this.billingStore.select(...this.CREDIT_NOTES);
      this.debitNotes$ = this.billingStore.select(...this.DEBIT_NOTES);
      this.invoices$ = this.billingStore.select(...this.INVOICES);
      
      this.tasks$ = Observable
      .combineLatest(
          this.creditNotes$,
          this.debitNotes$,
          this.invoices$,
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
    }
  
  viewTask(task: Task) {
      console.log("task",task);
      let uri = task['invoice'] ? '/invoices/invoice-task-detail' :
                  task['debitNote'] ? '/debit-notes/debit-note-task-detail' :
                  task['creditNote'] ? '/credit-notes/credit-note-task-detail' : null;
      this.router.navigate(['/billing' + uri, task.taskId]);
    }
}
