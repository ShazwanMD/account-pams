import { Component, OnInit, ViewContainerRef, ElementRef, Input, ChangeDetectionStrategy, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Receipt } from '../../../../shared/model/billing/receipt.interface';
import { BillingModuleState } from '../../index';
import { MdDialogRef } from '@angular/material';
import { Store } from '@ngrx/store';
import { Invoice } from "../../../../shared/model/billing/invoice.interface";
import { InvoiceActions } from "../../invoices/invoice.action";
import { Observable } from "rxjs/Observable";
import { InvoiceItem } from "../../../../shared/model/billing/invoice-item.interface";
import { ReceiptItem } from "../../../../shared/model/billing/receipt-item.interface";
import { WaiverFinanceApplicationActions } from "../waiver-finance-application.action";
import { WaiverFinanceApplication } from "../../../../shared/model/billing/waiver-finance-application.interface";
import { WaiverItem } from "../../../../shared/model/billing/waiver-item.interface";

@Component( {
    selector: 'pams-item-applicator',
    templateUrl: './item-applicator.dialog.html',
} )

export class ItemApplicatorDialog implements OnInit {

    private WAIVER_ITEMS: string[] = 'billingModuleState.waiverItem'.split( '.' );
    private _invoice: Invoice;
    private _waiverFinanceApplication: WaiverFinanceApplication;
    private waiverItems$: Observable<WaiverItem[]>;

    constructor( private router: Router,
        private route: ActivatedRoute,
        private formBuilder: FormBuilder,
        private viewContainerRef: ViewContainerRef,
        private store: Store<BillingModuleState>,
        private actions: WaiverFinanceApplicationActions,
        private dialog: MdDialogRef<ItemApplicatorDialog> ) {
        
        this.waiverItems$ = this.store.select( ...this.WAIVER_ITEMS );

    }
    
    private columns: any[] = [
                              { name: 'chargeCode.code', label: 'Charge Code' },
                              { name: 'description', label: 'Charge Code Description' },
                              { name: 'dueAmount', label: 'Amount' },
                              { name: 'appliedAmount', label: 'Received Amount' },
                              { name: 'totalAmount', label: 'Balance Amount' },
                              { name: 'action', label: '' },
                          ];
    
    ngOnInit(): void {
        console.log( "refNo" + this._invoice.referenceNo );
        this.store.dispatch( this.actions.findInvoiceWaiverItems(this._waiverFinanceApplication, this._invoice));
    }

    set invoice( value: Invoice ) {
        this._invoice = value;
    }

    set waiverFinanceApplication( value: WaiverFinanceApplication ) {
        this._waiverFinanceApplication = value;
    }

    Apply(): void {
        console.log("waiver ref no " + this._waiverFinanceApplication.referenceNo);
        this.store.dispatch( this.actions.itemToWaiverItem(this._invoice, this._waiverFinanceApplication));
    }
}
