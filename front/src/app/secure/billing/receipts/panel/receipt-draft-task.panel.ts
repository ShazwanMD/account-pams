import { ReceiptDebitNote } from './../../../../shared/model/billing/receipt-debit_note.interface';
import {Component, Input, OnInit, ViewContainerRef} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ReceiptItem} from '../../../../shared/model/billing/receipt-item.interface';
import {MdDialog, MdDialogConfig, MdSnackBar} from '@angular/material';
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
import { InvoiceReceiptDialog } from "../dialog/invoice-receipt.dialog";
import { ReceiptInvoice } from "../../../../shared/model/billing/receipt-invoice.interface";
import { InvoiceReceiptCreatorDialog } from "../dialog/invoice-receipt-creator.dialog";
import { AccountActions } from '../../../account/accounts/account.action';
import { ReceiptAccountCharge } from '../../../../shared/model/billing/receipt-account-charge.interface';
import { Receipt } from '../../../../shared/model/billing/receipt.interface';
import { AccountCharge } from "../../../../shared/model/account/account-charge.interface";

@Component({
  selector: 'pams-receipt-draft-task',
  templateUrl: './receipt-draft-task.panel.html',
})

export class ReceiptDraftTaskPanel implements OnInit {

  private RECEIPT_ITEMS: string[] = 'billingModuleState.receiptItems'.split('.');
  private ACCOUNT: string[] = 'accountModuleState.account'.split('.');
  private INVOICES: string[] = 'billingModuleState.invoices'.split('.');
  private RECEIPT_INVOICE: string[] = 'billingModuleState.receiptInvoice'.split('.');
  private RECEIPT_ACCOUNT_CHARGE: string[] = 'billingModuleState.receiptAccountCharge'.split('.');
  private RECEIPT_DEBIT_NOTE: string[] = 'billingModuleState.receiptDebitNote'.split('.');
  private ACCOUNT_CHARGES: string[] = 'accountModuleState.accountCharges'.split('.');
  private account$: Observable<Account>;
  private invoices$: Observable<Invoice[]>;
  private receipt$: Observable<Receipt[]>;
  private receiptItems$: Observable<ReceiptItem[]>;
  private receiptInvoice$: Observable<ReceiptInvoice[]>;
  private receiptAccountCharge$: Observable<ReceiptAccountCharge[]>;
  private receiptDebitNote$: Observable<ReceiptDebitNote[]>;
  private accountCharges$: Observable<AccountCharge[]>;

  @Input() receiptTask: ReceiptTask;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private viewContainerRef: ViewContainerRef,
              private actions: ReceiptActions,
              private action: InvoiceActions,
              private accountAction: AccountActions,
              private store: Store<BillingModuleState>,
              private stores: Store<AccountModuleState>,
              private dialog: MdDialog,
              private snackBar: MdSnackBar) {
    this.receiptItems$ = this.store.select(...this.RECEIPT_ITEMS);
    this.account$ = this.stores.select(...this.ACCOUNT);
    this.invoices$ = this.store.select(...this.INVOICES);
    this.receiptInvoice$ = this.store.select(...this.RECEIPT_INVOICE);
    this.receiptAccountCharge$ = this.store.select(...this.RECEIPT_ACCOUNT_CHARGE);
    this.receiptDebitNote$ = this.store.select(...this.RECEIPT_DEBIT_NOTE);
    this.accountCharges$ = this.stores.select(...this.ACCOUNT_CHARGES);
  }

  ngOnInit(): void {
    this.store.dispatch(this.action.findUnpaidInvoices(this.receiptTask.receipt.account));
    this.stores.dispatch(this.accountAction.findUnpaidAccountCharges(this.receiptTask.receipt.account));
    this.store.dispatch(this.actions.findUnpaidDebitNotes(this.receiptTask.receipt.account));
    this.store.dispatch(this.actions.findReceiptsByInvoice(this.receiptTask.receipt));
    
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

//  applyPromoCode() {
//    let config = new MdDialogConfig();
//    config.viewContainerRef = this.viewContainerRef;
//    config.role = 'dialog';
//    config.width = '70%';
//    config.height = '60%';
//    config.position = {top: '0px'};
//    let editorDialogRef = this.dialog.open(PromoCodeApplicatorDialog, config);
//    editorDialogRef.componentInstance.receipt = this.receiptTask.receipt;
//  }
//  
//  paidInvoices() {
//      console.log("ReceiptDialog "+ this.receiptTask.receipt.referenceNo);
//      let config = new MdDialogConfig();
//      config.viewContainerRef = this.viewContainerRef;
//      config.role = 'dialog';
//      config.width = '70%';
//      config.height = '60%';
//      config.position = {top: '0px'};
//      let editorDialogRef = this.dialog.open(InvoiceReceiptDialog, config);
//      editorDialogRef.componentInstance.receipt = this.receiptTask.receipt;
//    }

  register() {
    this.store.dispatch(this.actions.completeReceiptTask(this.receiptTask));
    this.goBack();
  }

  goBack(): void {
    this.router.navigate(['/secure/billing/receipts']);
  }


}
