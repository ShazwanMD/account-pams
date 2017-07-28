import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {InvoiceTask} from '../../../shared/model/billing/invoice-task.interface';
import {InvoiceActions} from './invoice.action';
import {Observable} from 'rxjs';
import {BillingModuleState} from '../index';
import {Store} from '@ngrx/store';
import {InvoiceItem} from '../../../shared/model/billing/invoice-item.interface';
import {MdDialog} from '@angular/material';
import {DebitNote} from '../../../shared/model/billing/debit-note.interface';
import {CreditNote} from '../../../shared/model/billing/credit-note.interface';

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
    this.router.navigate(['/secure/billing/invoices']);
  }

}
