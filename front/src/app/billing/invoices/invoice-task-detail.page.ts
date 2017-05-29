import {
  Component, OnInit, ViewChild, ViewContainerRef,
  ComponentFactoryResolver, ComponentRef
} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {InvoiceTask} from "./invoice-task.interface";
import {InvoiceActions} from "./invoice.action";
import {Observable} from "rxjs";
import {BillingModuleState} from "../index";
import {Store} from "@ngrx/store";


@Component({
  selector: 'pams-invoice-iew',
  templateUrl: './invoice-view.page.html',
})
export class InvoiceTaskViewPage implements OnInit {

  private INVOICE = "billingModuleState.invoice".split(".");
  private invoiceTask$: Observable<InvoiceTask>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private store: Store<BillingModuleState>,
              private actions: InvoiceActions) {
    this.invoiceTask$ = this.store.select(...this.INVOICE)
  }

  ngOnInit(): void {
    this.route.params.subscribe((params: {taskId: string}) => {
      let taskId: string = params.taskId;
      this.store.dispatch(this.actions.findInvoiceTaskByTaskId(taskId));
    });
  }

  goBack(): void {
    this.router.navigate(['/billing/invoices']);
  }
}


