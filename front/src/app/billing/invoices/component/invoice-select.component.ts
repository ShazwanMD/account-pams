import {Component, Input, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {Store} from "@ngrx/store";
import {FormControl} from "@angular/forms";
import { BillingModuleState } from "../../index";
import { Invoice } from "../invoice.interface";
import { InvoiceActions } from "../invoice.action";
import {Receipt} from "../../receipts/receipt.interface";

@Component({
  selector: 'pams-invoice-select',
  templateUrl: './invoice-select.component.html',
})
export class InvoiceSelectComponent implements OnInit {

  private INVOICES = "billingModuleState.invoices".split(".");
  @Input() placeholder: string;
  @Input() innerFormControl: FormControl;
  @Input() preSelected: Invoice;
  @Input() receipt: Receipt;
  private invoices$: Observable<Invoice[]>;
  private selected: Invoice;

  constructor(private store: Store<BillingModuleState>,
              private actions: InvoiceActions) {
    this.invoices$ = this.store.select(...this.INVOICES);
  }

  ngOnInit() {
    if(this.receipt)
        this.store.dispatch(this.actions.findUnpaidInvoices(this.receipt.account));
    else
        this.store.dispatch(this.actions.findCompletedInvoices());
    
    if(this.preSelected){
        this.invoices$.subscribe(invoices => {
            this.selected = invoices.find(invoice => invoice.id == this.preSelected.id);
        })
    }
  }

  selectChangeEvent(event: Invoice) {
    this.innerFormControl.setValue(event, {emitEvent: false});
  }
}

