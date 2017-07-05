import {Component, OnInit, ViewContainerRef, Input} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {InvoiceTask} from './invoice-task.interface';
import {InvoiceActions} from './invoice.action';
import {Observable} from 'rxjs';
import {BillingModuleState} from '../index';
import {Store} from '@ngrx/store';
import {InvoiceItem} from './invoice-item.interface';
import {MdDialogRef, MdDialog} from '@angular/material';
import {DebitNote} from '../debit-notes/debit-note.interface';
import {CreditNote} from "../credit-notes/credit-note.interface";
import { TdDialogService } from "@covalent/core";
import { Invoice } from "./invoice.interface";

@Component({
  selector: 'pams-invoice-detail',
  templateUrl: './invoice-detail.page.html',
})
export class InvoiceDetailPage implements OnInit {

  private INVOICE: string[] = 'billingModuleState.invoice'.split('.');
  private INVOICE_ITEMS: string[] = 'billingModuleState.invoiceItems'.split('.');
  private DEBIT_NOTES: string[] = 'billingModuleState.invoiceDebitNotes'.split('.');
  private CREDIT_NOTES: string[] = 'billingModuleState.invoiceCreditNotes'.split('.');
  private invoice$: Observable<InvoiceTask>;
  private invoiceItems$: Observable<InvoiceItem[]>;
  private debitNotes$: Observable<DebitNote[]>;
  private creditNotes$: Observable<CreditNote[]>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private store: Store<BillingModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog,
              private _dialogService: TdDialogService,
              private actions: InvoiceActions) {
    this.invoice$ = this.store.select(...this.INVOICE);
    this.invoiceItems$ = this.store.select(...this.INVOICE_ITEMS);
    this.debitNotes$ = this.store.select(...this.DEBIT_NOTES);
    this.creditNotes$ = this.store.select(...this.CREDIT_NOTES);
  }

  ngOnInit(): void {
    this.route.params.subscribe((params: { referenceNo: string }) => {
      let referenceNo: string = params.referenceNo;
      this.store.dispatch(this.actions.findInvoiceByReferenceNo(referenceNo));
    });
  }

  goBack(): void {
    this.router.navigate(['/billing/invoices']);
  }
  referenceNo:Invoice;
  
  cancelDialog(): void {
      this._dialogService.openConfirm({
        message: 'Cancel Invoice '+this.referenceNo+'?',
        disableClose: false, // defaults to false
        title: 'Cancel', //OPTIONAL, hides if not provided
        cancelButton: 'No', //OPTIONAL, defaults to 'CANCEL'
        acceptButton: 'Yes', //OPTIONAL, defaults to 'ACCEPT'
      }).afterClosed().subscribe((accept: boolean) => {
        if (accept) {
          this.store.dispatch(this.actions.cancelInvoice(this.actions.findInvoiceByReferenceNo(this.referenceNo)));
          this.goBack();
        } else {
          // DO SOMETHING ELSE
        }
      });
    
  }
}

