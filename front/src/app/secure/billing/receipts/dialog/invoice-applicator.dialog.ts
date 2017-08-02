import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {Receipt} from '../../../../shared/model/billing/receipt.interface';
import {BillingModuleState} from '../../index';
import {MdDialogRef} from '@angular/material';
import {ReceiptActions} from '../receipt.action';
import {Store} from '@ngrx/store';
import { Invoice } from "../../../../shared/model/billing/invoice.interface";
import { InvoiceActions } from "../../invoices/invoice.action";
import { Observable } from "rxjs/Observable";
import { InvoiceItem } from "../../../../shared/model/billing/invoice-item.interface";

@Component({
  selector: 'pams-invoice-applicator',
  templateUrl: './invoice-applicator.dialog.html',
})

export class InvoiceApplicatorDialog implements OnInit {

  private SELECTED_INVOICE: string[] = 'billingModuleState.selectedInvoice'.split('.');
  private SELECTED_INVOICE_ITEMS: string[] = 'billingModuleState.selectedInvoiceItems'.split('.');
  private INVOICE: string[] = 'billingModuleState.invoice'.split('.');
  private INVOICE_ITEMS: string[] = 'billingModuleState.invoiceItems'.split('.');

  private applyForm: FormGroup;
  private _invoice: Invoice;

  private invoice$: Observable<Invoice>;
  private invoiceItems$: Observable<InvoiceItem[]>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<BillingModuleState>,
              private actions: InvoiceActions,
              private dialog: MdDialogRef<InvoiceApplicatorDialog>) {
      this.invoice$ = this.store.select(...this.INVOICE);
      this.invoiceItems$ = this.store.select(...this.INVOICE_ITEMS);
  }

  set invoice(value: Invoice) {
      this._invoice = value;
    }

  ngOnInit(): void {
     console.log("refNo" + this._invoice.referenceNo);
     this.store.dispatch(this.actions.findInvoiceItems(this._invoice));

  }

  submit(isValid: boolean): void {
    // do something
  }
}
