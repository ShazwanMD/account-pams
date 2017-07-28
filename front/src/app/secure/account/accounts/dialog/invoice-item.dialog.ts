import {Component, Input, OnInit, ViewContainerRef} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Observable} from 'rxjs';
import {Store} from '@ngrx/store';
import {MdDialog} from '@angular/material';
import {Invoice} from '../../../../shared/model/billing/invoice.interface';
import {InvoiceItem} from '../../../../shared/model/billing/invoice-item.interface';
import {InvoiceActions} from '../../../billing/invoices/invoice.action';
import {BillingModuleState} from '../../../billing/index';
import {AccountActivity} from '../../../../shared/model/account/account-activity.interface';

@Component({
  selector: 'pams-invoice-item',
  templateUrl: './invoice-item.dialog.html',
})
export class InvoiceItemDialog implements OnInit {

  @Input() invoice: Invoice;
  @Input() invoiceItems: InvoiceItem[];
  @Input() activity: AccountActivity[];

  private INVOICE: string[] = 'billingModuleState.invoice'.split('.');
  private INVOICE_ITEMS: string[] = 'billingModuleState.invoiceItems'.split('.');
  private ACCOUNT_ACTIVITY: string[] = 'accountModuleState.accountActivities'.split('.');
  private invoice$: Observable<Invoice>;
  private invoiceItems$: Observable<InvoiceItem[]>;
  private accountActivities$: Observable<AccountActivity[]>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private store: Store<BillingModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog,
              private actions: InvoiceActions) {
    this.invoice$ = this.store.select(...this.INVOICE);
    this.invoiceItems$ = this.store.select(...this.INVOICE_ITEMS);
    this.accountActivities$ = this.store.select(...this.ACCOUNT_ACTIVITY);
  }

  ngOnInit(): void {
    this.route.params.subscribe((params: { referenceNo: string }) => {
      let referenceNo: string = params.referenceNo;
      this.store.dispatch(this.actions.findInvoiceByReferenceNo(referenceNo));
    });
  }


}
