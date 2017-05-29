import {Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {InvoiceTask} from "./invoice-task.interface";
import {InvoiceActions} from "./invoice.action";
import {Observable} from "rxjs";
import {BillingModuleState} from "../index";
import {Store} from "@ngrx/store";
import {InvoiceItem} from "./invoice-item.interface";


@Component({
  selector: 'pams-invoice-detail',
  templateUrl: './invoice-detail.page.html',
})
export class InvoiceDetailPage implements OnInit {

  private INVOICE = "billingModuleState.invoice".split(".");
  private INVOICE_ITEMS = "billingModuleState.invoiceItems".split(".");
  private invoice$: Observable<InvoiceTask>;
  private invoiceItems$: Observable<InvoiceItem[]>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private store: Store<BillingModuleState>,
              private actions: InvoiceActions) {
    this.invoice$ = this.store.select(...this.INVOICE)
    this.invoiceItems$ = this.store.select(...this.INVOICE_ITEMS);
  }

  ngOnInit(): void {
    this.route.params.subscribe((params: { referenceNo: string }) => {
      let referenceNo: string = params.referenceNo;
      this.store.dispatch(this.actions.findInvoiceByReferenceNo(referenceNo));
    });
  }

  goBack(): void {
    this.router.navigate(['/billing/invoices']);
  }
}


