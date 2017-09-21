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

@Component( {
    selector: 'pams-item-applicator',
    templateUrl: './item-applicator.dialog.html',
} )

export class ItemApplicatorDialog {

    private _invoice: Invoice;
    private _waiverFinanceApplication: WaiverFinanceApplication;
    
    showHide: boolean;

    constructor( private router: Router,
        private route: ActivatedRoute,
        private formBuilder: FormBuilder,
        private viewContainerRef: ViewContainerRef,
        private store: Store<BillingModuleState>,
        private actions: WaiverFinanceApplicationActions,
        private dialog: MdDialogRef<ItemApplicatorDialog> ) {

        this.showHide = true;
    }

    set invoice( value: Invoice ) {
        this._invoice = value;
    }

    set waiverFinanceApplication( value: WaiverFinanceApplication ) {
        this._waiverFinanceApplication = value;
    }

    Apply(): void {
        this.showHide = !this.showHide;
        console.log("waiver ref no " + this._waiverFinanceApplication.referenceNo);
        this.store.dispatch( this.actions.itemToWaiverItem(this._invoice, this._waiverFinanceApplication));
    }
}
