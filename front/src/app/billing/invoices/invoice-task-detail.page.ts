import {Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {InvoiceTask} from "./invoice-task.interface";
import {InvoiceActions} from "./invoice.action";
import {Observable} from "rxjs";
import {BillingModuleState} from "../index";
import {Store} from "@ngrx/store";


@Component({
  selector: 'pams-invoice-task-detail',
  templateUrl: './invoice-task-detail.page.html',
})
export class InvoiceTaskDetailPage implements OnInit {

  private INVOICE_TASK = "billingModuleState.invoiceTask".split(".");
  private invoiceTask$: Observable<InvoiceTask>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private store: Store<BillingModuleState>,
              private actions: InvoiceActions) {
    this.invoiceTask$ = this.store.select(...this.INVOICE_TASK)
  }

  ngOnInit(): void {
    this.route.params.subscribe((params: { taskId: string }) => {
      let taskId: string = params.taskId;
      this.store.dispatch(this.actions.findInvoiceTaskByTaskId(taskId));
    });
  }

  goBack(): void {
    this.router.navigate(['/billing/invoices']);
  }
}


