import {Component, OnInit, ChangeDetectionStrategy, state} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {Store} from "@ngrx/store";
import {Observable} from "rxjs";
import {InvoiceActions} from "./invoice.action";
import {InvoiceTask} from "./invoice-task.interface";
import {InvoiceTaskListState} from "./invoice-task-list.reducer";
import {BillingModuleState} from "../index";

@Component({
  selector: 'pams-invoice-center',
  templateUrl: './invoice-center.page.html',
})

export class InvoiceCenterPage implements OnInit {

  private invoiceTasks$: Observable<InvoiceTask[]>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: InvoiceActions,
              private store: Store<BillingModuleState>) {
    this.invoiceTasks$ = this.store.select(state => state.invoiceTasks);
  }

  goBack(route: string): void {
    this.router.navigate(['/invoices']);
  }

  view(invoice: InvoiceTask) {
    console.log("invoice: " + invoice.taskId);
    this.router.navigate(['/view-task', invoice.taskId]);
  }

  ngOnInit(): void {
    console.log("find assigned invoice tasks");
    this.store.dispatch(this.actions.findAssignedInvoiceTasks());
  }
}

