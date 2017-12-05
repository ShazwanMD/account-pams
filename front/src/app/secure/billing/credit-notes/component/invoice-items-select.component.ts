import { Component, Input, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Store } from '@ngrx/store';
import { FormControl } from '@angular/forms';
import { BillingModuleState } from '../../index';
import { Invoice } from '../../../../shared/model/billing/invoice.interface';
import { Receipt } from '../../../../shared/model/billing/receipt.interface';
import { InvoiceItem } from "../../../../shared/model/billing/invoice-item.interface";
import { InvoiceActions } from "../../invoices/invoice.action";
import { ChargeCodeActions } from "../../../account/charge-codes/charge-code.action";
import { ChargeCode } from "../../../../shared/model/account/charge-code.interface";
import { AccountModuleState } from "../../../account/index";

@Component( {
    selector: 'pams-invoice-items-select',
    templateUrl: './invoice-items-select.component.html',
} )
export class InvoiceItemSelectComponent implements OnInit {

    private INVOICE_ITEMS: string[] = 'billingModuleState.invoiceItems'.split( '.' );
    private invoiceItems$: Observable<InvoiceItem[]>;
    private selected: InvoiceItem;

    @Input() placeholder: string;
    @Input() innerFormControl: FormControl;
    @Input() invoice: Invoice;
    @Input() preSelected: InvoiceItem;

    constructor( private store: Store<BillingModuleState>,
                 private actions: InvoiceActions) {
        this.invoiceItems$ = this.store.select( ...this.INVOICE_ITEMS );
    }

    ngOnInit() {
        if (this.preSelected) {
            this.invoiceItems$.subscribe((invoiceItems) => {
              this.selected = invoiceItems.find((invoiceItem) => invoiceItem.id == this.preSelected.id);
            });
          }
    }

    selectChangeEvent(event: InvoiceItem) {
        this.innerFormControl.setValue(event, {emitEvent: false});
        this.selected = event;
      }
}

