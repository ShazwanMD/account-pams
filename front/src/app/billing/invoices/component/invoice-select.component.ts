import {Component, Input, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {Store} from "@ngrx/store";
import {FormControl} from "@angular/forms";
import { BillingModuleState } from "../../index";
import { Invoice } from "../invoice.interface";
import { InvoiceActions } from "../invoice.action";


@Component({
  selector: 'pams-invoice-select',
  templateUrl: './invoice-select.component.html',
})
export class InvoiceSelectComponent implements OnInit {

  private INVOICES = "billingModuleState.invoices".split(".");
  @Input() placeholder: string;
  @Input() innerFormControl: FormControl;
  private invoices$: Observable<Invoice[]>;

  constructor(private store: Store<BillingModuleState>,
              private actions: InvoiceActions) {
    this.invoices$ = this.store.select(...this.INVOICES);
  }

  ngOnInit() {
    this.store.dispatch(this.actions.findCompletedInvoices());
  }

  selectChangeEvent(event: Invoice) {
    this.innerFormControl.setValue(event, {emitEvent: false});
  }
}

