import {Component, ViewContainerRef, OnInit} from '@angular/core';
import {FormGroup, FormControl} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {ReceiptItem} from '../receipt-item.interface';
import {Receipt} from '../receipt.interface';
import {BillingModuleState} from '../../index';
import {MdDialogRef} from '@angular/material';
import {ReceiptActions} from '../receipt.action';
import {Store} from '@ngrx/store';
import {Invoice} from "../../invoices/invoice.interface";

@Component({
  selector: 'pams-invoice-applicator',
  templateUrl: './invoice-applicator.dialog.html',
})

export class InvoiceApplicatorDialog implements OnInit {

  private SELECTED_INVOICE: string[] = 'billingModuleState.selectedInvoice'.split('.');
  private SELECTED_INVOICE_ITEMS: string[] = 'billingModuleState.selectedInvoiceItems'.split('.');
  private applyForm: FormGroup;
  private _receipt: Receipt;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<BillingModuleState>,
              private actions: ReceiptActions,
              private dialog: MdDialogRef<InvoiceApplicatorDialog>) {
  }

  set receipt(value: Receipt) {
    this._receipt = value;
  }

  ngOnInit(): void {
    this.applyForm = this.formBuilder.group({});
  }

  submit(isValid: boolean): void {
    // do something
  }
}
