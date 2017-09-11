import {Component, OnInit, ViewContainerRef, ElementRef, Input, ChangeDetectionStrategy} from '@angular/core';
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
import { ReceiptItem } from "../../../../shared/model/billing/receipt-item.interface";

@Component({
  selector: 'pams-invoice-applicator',
  templateUrl: './invoice-applicator.dialog.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})

export class InvoiceApplicatorDialog implements OnInit {

  private RECEIPT_ITEMS: string[] = 'billingModuleState.receiptItems'.split('.');
  private _invoice: Invoice;
  private _receipt: Receipt;

  private receiptItems$: Observable<ReceiptItem[]>;

  showHide: boolean;
  readonly: boolean;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              private viewContainerRef: ViewContainerRef,
              private store: Store<BillingModuleState>,
              private actions: ReceiptActions,
              private dialog: MdDialogRef<InvoiceApplicatorDialog>) {
      this.receiptItems$ = this.store.select(...this.RECEIPT_ITEMS);
      this.showHide = true;
      this.readonly = true;
  }

  set invoice(value: Invoice) {
      this._invoice = value;
    }
  
  set receipt(value: Receipt) {
      this._receipt = value;
    }

  ngOnInit(): void {
     console.log("refNo" + this._invoice.referenceNo);
  }
  
  private columns: any[] = [
                            {name: 'chargeCode.code', label: 'Charge Code'},
                            {name: 'description', label: 'Charge Code Description'},
                            {name: 'dueAmount', label: 'Amount'},
                            {name: 'appliedAmount', label: 'Received Amount'},
                            {name: 'totalAmount', label: 'Balance Amount'},
                            {name: 'action', label: ''},
                          ];

  Apply(): void {
      this.showHide = !this.showHide;
      this.store.dispatch(this.actions.itemToReceiptItem(this._invoice, this._receipt));
  }

}
