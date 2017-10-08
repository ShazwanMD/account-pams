import { ReceiptActions } from './receipt.action';
import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Observable} from 'rxjs';
import {BillingModuleState} from '../index';
import {Store} from '@ngrx/store';
import {MdDialog, MdDialogConfig} from '@angular/material';
import {Invoice} from '../../../shared/model/billing/invoice.interface';
import {InvoiceItem} from '../../../shared/model/billing/invoice-item.interface';
import {InvoiceActions} from '../invoices/invoice.action';
import {PromoCodeApplicatorDialog} from './dialog/promo-code-applicator.dialog';
import {Receipt} from '../../../shared/model/billing/receipt.interface';

@Component({
  selector: 'pams-receipt-item-detail',
  templateUrl: './receipt-item-detail.page.html',
})
export class ReceiptItemDetailPage implements OnInit {

  private INVOICE: string[] = 'billingModuleState.invoice'.split('.');
  private INVOICE_ITEMS: string[] = 'billingModuleState.invoiceItems'.split('.');
  private RECEIPTS: string[] = 'billingModuleState.receipts'.split('.');
  private invoice$: Observable<Invoice>;
  private invoiceItems$: Observable<InvoiceItem[]>;
  private receipts$: Observable<Receipt>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private store: Store<BillingModuleState>,
              private viewContainerRef: ViewContainerRef,
              private dialog: MdDialog,
              private actions: ReceiptActions) {
    this.invoice$ = this.store.select(...this.INVOICE);
    this.invoiceItems$ = this.store.select(...this.INVOICE_ITEMS);
    this.receipts$ = this.store.select(...this.RECEIPTS);
  }

  ngOnInit(): void {
    this.route.params.subscribe((params: { referenceNo: string }) => {
      let referenceNo: string = params.referenceNo;
      this.store.dispatch(this.actions.findReceiptByReferenceNo(referenceNo));
    });
  }

  applyPromoCode() {
    let config = new MdDialogConfig();
    config.viewContainerRef = this.viewContainerRef;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '60%';
    config.position = {top: '0px'};
    let editorDialogRef = this.dialog.open(PromoCodeApplicatorDialog, config);
    //editorDialogRef.componentInstance.receipt = this.receipt;
  }

  goBack(): void {
    this.router.navigate(['/secure/billing/invoices']);
  }

}

