import {Component, Input, OnInit, ViewContainerRef, Output, EventEmitter} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Observable} from 'rxjs';
import {Store} from '@ngrx/store';
import {MdDialog} from '@angular/material';
import {Invoice} from '../../../../shared/model/billing/invoice.interface';
import {InvoiceItem} from '../../../../shared/model/billing/invoice-item.interface';
import {InvoiceActions} from '../../../billing/invoices/invoice.action';
import {BillingModuleState} from '../../../billing/index';
import {AccountActivity} from '../../../../shared/model/account/account-activity.interface';
import { FormGroup } from "@angular/forms";

@Component({
  selector: 'pams-invoice-item',
  templateUrl: './invoice-item.dialog.html',
})
export class InvoiceItemDialog implements OnInit {

  private edit: boolean = false;
  private _invoice: Invoice;
  private _accountActivity: AccountActivity;
  private _invoiceItems: InvoiceItem;


  private INVOICE: string[] = 'billingModuleState.invoice'.split('.');
  private INVOICE_ITEMS: string[] = 'billingModuleState.invoiceItems'.split('.');
  private invoice$: Observable<Invoice>;
  private invoiceItems$: Observable<InvoiceItem[]>;
  constructor(private router: Router,
              private route: ActivatedRoute,
              private store: Store<BillingModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog,
              private actions: InvoiceActions) {
      this.invoice$ = this.store.select(...this.INVOICE);
      this.invoiceItems$ = this.store.select(...this.INVOICE_ITEMS);
  }
  
  set activity(value: AccountActivity) {
      this._accountActivity = value;
    }

  ngOnInit(): void {
      console.log("sourceNo" + this._accountActivity.sourceNo);
      
      this.invoice$.subscribe((invoice: Invoice) => {
        invoice.referenceNo = this._accountActivity.sourceNo;

        console.log("invoiceNo" + invoice.referenceNo);
        if (invoice.referenceNo) this.store.dispatch(this.actions.findInvoiceItems(invoice));
      });
  }


}
