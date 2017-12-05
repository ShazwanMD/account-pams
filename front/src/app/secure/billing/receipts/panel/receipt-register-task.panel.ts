import {Component, Input, OnInit, ViewContainerRef} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MdDialog, MdDialogConfig, MdSnackBar} from '@angular/material';
import {ReceiptItemEditorDialog} from '../dialog/receipt-item-editor.dialog';
import {ReceiptActions} from '../receipt.action';
import {Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {BillingModuleState} from '../../index';
import {ReceiptTask} from '../../../../shared/model/billing/receipt-task.interface';
import {ReceiptItem} from '../../../../shared/model/billing/receipt-item.interface';
import { TdDialogService } from "@covalent/core";
import { Invoice } from "../../../../shared/model/billing/invoice.interface";
import { Receipt } from "../../../../shared/model/billing/receipt.interface";
import { ReceiptInvoice } from "../../../../shared/model/billing/receipt-invoice.interface";
import { ReceiptAccountCharge } from "../../../../shared/model/billing/receipt-account-charge.interface";
import { ReceiptDebitNote } from "../../../../shared/model/billing/receipt-debit_note.interface";
import { DebitNote } from "../../../../shared/model/billing/debit-note.interface";
import { AccountCharge } from "../../../../shared/model/account/account-charge.interface";
import { AccountModuleState } from "../../../account/index";
import { InvoiceActions } from "../../invoices/invoice.action";
import { AccountActions } from "../../../account/accounts/account.action";
import { DebitNoteActions } from "../../debit-notes/debit-note.action";
import {Account} from '../../../../shared/model/account/account.interface';

@Component({
  selector: 'pams-receipt-register-task',
  templateUrl: './receipt-register-task.panel.html',
})

export class ReceiptRegisterTaskPanel implements OnInit {

    private RECEIPT_ITEMS: string[] = 'billingModuleState.receiptItems'.split('.');
    private ACCOUNT: string[] = 'accountModuleState.account'.split('.');
    private INVOICES: string[] = 'billingModuleState.invoices'.split('.');
    private RECEIPT_INVOICE: string[] = 'billingModuleState.receiptInvoice'.split('.');
    private RECEIPT_ACCOUNT_CHARGE: string[] = 'billingModuleState.receiptAccountCharge'.split('.');
    private RECEIPT_DEBIT_NOTE: string[] = 'billingModuleState.receiptDebitNote'.split('.');
    private DEBIT_NOTE: string[] = 'billingModuleState.debitNoteList'.split('.');
    private ACCOUNT_CHARGES: string[] = 'accountModuleState.accountCharges'.split('.');
    private account$: Observable<Account>;
    private invoices$: Observable<Invoice[]>;
    private receipt$: Observable<Receipt[]>;
    private receiptItems$: Observable<ReceiptItem[]>;
    private receiptInvoice$: Observable<ReceiptInvoice[]>;
    private receiptAccountCharge$: Observable<ReceiptAccountCharge[]>;
    private receiptDebitNote$: Observable<ReceiptDebitNote[]>;
    private debitNotes$: Observable<DebitNote[]>;
    private accountCharges$: Observable<AccountCharge[]>;

    @Input() receiptTask: ReceiptTask;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private viewContainerRef: ViewContainerRef,
              private actions: ReceiptActions,
              private action: InvoiceActions,
              private accountAction: AccountActions,
              private dbtAction: DebitNoteActions,
              private store: Store<BillingModuleState>,
              private stores: Store<AccountModuleState>,
              private dialog: MdDialog,
              private _dialogService: TdDialogService,
              private snackBar: MdSnackBar) {
      this.receiptItems$ = this.store.select(...this.RECEIPT_ITEMS);
      this.account$ = this.stores.select(...this.ACCOUNT);
      this.invoices$ = this.store.select(...this.INVOICES);
      this.receiptInvoice$ = this.store.select(...this.RECEIPT_INVOICE);
      this.receiptAccountCharge$ = this.store.select(...this.RECEIPT_ACCOUNT_CHARGE);
      this.receiptDebitNote$ = this.store.select(...this.RECEIPT_DEBIT_NOTE);
      this.debitNotes$ = this.store.select(...this.DEBIT_NOTE);
      this.accountCharges$ = this.stores.select(...this.ACCOUNT_CHARGES);
  }

  ngOnInit(): void {
      this.store.dispatch(this.action.findUnpaidInvoices(this.receiptTask.receipt.account));
      this.stores.dispatch(this.accountAction.findUnpaidAccountCharges(this.receiptTask.receipt.account));
      this.store.dispatch(this.dbtAction.findUnpaidDebitNotes(this.receiptTask.receipt.account));
      this.store.dispatch(this.actions.findReceiptsByInvoice(this.receiptTask.receipt));
      this.store.dispatch(this.actions.findReceiptsByAccountCharge(this.receiptTask.receipt));
      this.store.dispatch(this.actions.findReceiptsByDebitNote(this.receiptTask.receipt));
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

  approve() {
    this.store.dispatch(this.actions.completeReceiptTask(this.receiptTask));
    this.goBack();
  }

  goBack(): void {
    this.router.navigate(['/secure/billing/receipts']);
  }
  
  cancel(): void {
      console.log("Receipt " + this.receiptTask.receipt);
      this._dialogService.openConfirm({
        message: 'Cancel Receipt ' + this.receiptTask.receipt.referenceNo + ' ?',
        disableClose: false, // defaults to false
        viewContainerRef: this.viewContainerRef,
        cancelButton: 'No', //OPTIONAL, defaults to 'CANCEL'
        acceptButton: 'Yes', //OPTIONAL, defaults to 'ACCEPT'
      }).afterClosed().subscribe((accept: boolean) => {
        if (accept) {
          this.store.dispatch(this.actions.removeReceiptTask(this.receiptTask));
          this.router.navigate(['/secure/billing/receipts']);
        } else {
          // DO SOMETHING ELSE
        }
      });
    }
  
}
