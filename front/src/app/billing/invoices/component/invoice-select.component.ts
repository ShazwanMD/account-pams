import {Component, Input, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Store} from '@ngrx/store';
import {FormControl} from '@angular/forms';
import {BillingModuleState} from '../../index';
import {InvoiceActions} from '../invoice.action';
import {Invoice} from '../../../shared/model/billing/invoice.interface';
import {Receipt} from '../../../shared/model/billing/receipt.interface';

@Component({
  selector: 'pams-invoice-select',
  templateUrl: './invoice-select.component.html',
})
export class InvoiceSelectComponent implements OnInit {

  private INVOICES = 'billingModuleState.invoices'.split('.');
  private invoices$: Observable<Invoice[]>;
  private selected: Invoice;

  @Input() placeholder: string;
  @Input() innerFormControl: FormControl;
  @Input() preSelected: Invoice;
  @Input() receipt: Receipt;

  constructor(private store: Store<BillingModuleState>,
              private actions: InvoiceActions) {
    this.invoices$ = this.store.select(...this.INVOICES);
  }

  ngOnInit() {
    if (this.receipt)
      this.store.dispatch(this.actions.findUnpaidInvoices(this.receipt.account));
    else
      this.store.dispatch(this.actions.findCompletedInvoices());

    if (this.preSelected) {
      this.invoices$.subscribe((invoices) => {
        this.selected = invoices.find((invoice) => invoice.id == this.preSelected.id);
      });
    }
  }

  selectChangeEvent(event: Invoice) {
    this.innerFormControl.setValue(event, {emitEvent: false});
  }
}
