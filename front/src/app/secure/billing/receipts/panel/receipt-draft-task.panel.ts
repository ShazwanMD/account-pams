import {Component, OnInit, ViewContainerRef, Input} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {ReceiptItem} from '../../../../shared/model/billing/receipt-item.interface';
import {MdSnackBar, MdDialog, MdDialogRef, MdDialogConfig} from '@angular/material';
import {ReceiptItemEditorDialog} from '../dialog/receipt-item-editor.dialog';
import {ReceiptTask} from '../../../../shared/model/billing/receipt-task.interface';
import {ReceiptActions} from '../receipt.action';
import {Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {BillingModuleState} from '../../index';
import {PromoCodeApplicatorDialog} from '../dialog/promo-code-applicator.dialog';
import {AccountModuleState} from '../../../account/index';
import {Invoice} from '../../../../shared/model/billing/invoice.interface';
import {Account} from '../../../../shared/model/account/account.interface';
import {InvoiceActions} from '../../invoices/invoice.action';

@Component({
  selector: 'pams-receipt-draft-task',
  templateUrl: './receipt-draft-task.panel.html',
})

export class ReceiptDraftTaskPanel implements OnInit {

  private RECEIPT_ITEMS: string[] = 'billingModuleState.receiptItems'.split('.');
  private ACCOUNT: string[] = 'accountModuleState.account'.split('.');
  private INVOICES: string[] = 'billingModuleState.invoices'.split('.');
  private account$: Observable<Account>;
  private invoices$: Observable<Invoice[]>;
  private receiptItems$: Observable<ReceiptItem[]>;

  @Input() receiptTask: ReceiptTask;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private viewContainerRef: ViewContainerRef,
              private actions: ReceiptActions,
              private action: InvoiceActions,
              private store: Store<BillingModuleState>,
              private stores: Store<AccountModuleState>,
              private dialog: MdDialog,
              private snackBar: MdSnackBar) {
    this.receiptItems$ = this.store.select(...this.RECEIPT_ITEMS);
    this.account$ = this.stores.select(...this.ACCOUNT);
    this.invoices$ = this.store.select(...this.INVOICES);
  }

  ngOnInit(): void {
    this.store.dispatch(this.actions.findReceiptItems(this.receiptTask.receipt));
    this.store.dispatch(this.action.findUnpaidInvoices(this.receiptTask.receipt.account));
  }

  editItem(item: ReceiptItem) {
    let config = new MdDialogConfig();
    config.viewContainerRef = this.viewContainerRef;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '60%';
    config.position = {top: '0px'};
    let editorDialogRef = this.dialog.open(ReceiptItemEditorDialog, config);
    editorDialogRef.componentInstance.receiptItem = item;
  }

  applyPromoCode() {
    let config = new MdDialogConfig();
    config.viewContainerRef = this.viewContainerRef;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '60%';
    config.position = {top: '0px'};
    let editorDialogRef = this.dialog.open(PromoCodeApplicatorDialog, config);
    editorDialogRef.componentInstance.receipt = this.receiptTask.receipt;
  }

  register() {
    this.store.dispatch(this.actions.completeReceiptTask(this.receiptTask));
    this.goBack();
  }

  goBack(): void {
    this.router.navigate(['/billing/receipts']);
  }

  viewInvoice(invoice: Invoice) {

    this.store.dispatch(this.actions.addReceiptInvoiceItems(this.receiptTask.receipt, invoice));

    console.log('invoice: ' + invoice.referenceNo);
    this.router.navigate(['billing/receipts/view-item', invoice.referenceNo]);
  }
}
