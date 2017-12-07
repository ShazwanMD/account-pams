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
import { DebitNote } from "../../../../shared/model/billing/debit-note.interface";

@Component( {
    selector: 'pams-debit-note-applicator',
    templateUrl: './debit-note-applicator.dialog.html',
} )

export class DebitNoteApplicatorDialog implements OnInit {

    private WAIVER_ITEMS: string[] = 'billingModuleState.waiverItem'.split( '.' );
    private _debitNote: DebitNote;
    private _waiverFinanceApplication: WaiverFinanceApplication;
    private waiverItems$: Observable<WaiverItem[]>;

    constructor( private router: Router,
        private route: ActivatedRoute,
        private formBuilder: FormBuilder,
        private viewContainerRef: ViewContainerRef,
        private store: Store<BillingModuleState>,
        private actions: WaiverFinanceApplicationActions,
        private dialog: MdDialogRef<DebitNoteApplicatorDialog> ) {
        
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
        this.store.dispatch( this.actions.findDebitWaiverItems(this._waiverFinanceApplication, this._debitNote));
    }

    set debitNote( value: DebitNote ) {
        this._debitNote = value;
    }

    set waiverFinanceApplication( value: WaiverFinanceApplication ) {
        this._waiverFinanceApplication = value;
    }

    Apply(): void {
        console.log("waiver ref no " + this._waiverFinanceApplication.referenceNo);
        this.store.dispatch( this.actions.debitToWaiverItem(this._debitNote, this._waiverFinanceApplication));
    }
}
