import { Component, OnInit, ViewContainerRef, ElementRef, Input, ChangeDetectionStrategy, Output, EventEmitter } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Receipt } from '../../../../shared/model/billing/receipt.interface';
import { BillingModuleState } from '../../index';
import { MdDialogRef } from '@angular/material';
import { ReceiptActions } from '../receipt.action';
import { Store } from '@ngrx/store';
import { Invoice } from "../../../../shared/model/billing/invoice.interface";
import { InvoiceActions } from "../../invoices/invoice.action";
import { Observable } from "rxjs/Observable";
import { InvoiceItem } from "../../../../shared/model/billing/invoice-item.interface";
import { ReceiptItem } from "../../../../shared/model/billing/receipt-item.interface";
import { DebitNote } from "../../../../shared/model/billing/debit-note.interface";

@Component( {
    selector: 'pams-debit-note-applicator',
    templateUrl: './debit-note-applicator.dialog.html',
    changeDetection: ChangeDetectionStrategy.OnPush,
} )

export class DebitNoteApplicatorDialog implements OnInit {

    private RECEIPT_ITEMS: string[] = 'billingModuleState.receiptItems'.split( '.' );
    private _debitNote: DebitNote;
    private _receipt: Receipt;
    private edit: boolean = false;
    private receiptItems$: Observable<ReceiptItem[]>;
    
    showHide: boolean;

    constructor( private router: Router,
        private route: ActivatedRoute,
        private formBuilder: FormBuilder,
        private viewContainerRef: ViewContainerRef,
        private store: Store<BillingModuleState>,
        private actions: ReceiptActions,
        private dialog: MdDialogRef<DebitNoteApplicatorDialog> ) {
        this.receiptItems$ = this.store.select( ...this.RECEIPT_ITEMS );
        this.showHide = true;
    }

    set debitNote( value: DebitNote ) {
        this._debitNote = value;
        this.edit = true;
    }

    set receipt( value: Receipt ) {
        this._receipt = value;
    }

    ngOnInit(): void {
       // console.log( "refNo" + this._invoice.referenceNo );
        this.store.dispatch( this.actions.findDebitNoteReceiptItems(this._receipt, this._debitNote));
    }

    private columns: any[] = [
        { name: 'chargeCode.code', label: 'Charge Code' },
        { name: 'description', label: 'Charge Code Description' },
        { name: 'dueAmount', label: 'Amount' },
        { name: 'appliedAmount', label: 'Received Amount' },
        { name: 'totalAmount', label: 'Balance Amount' },
        { name: 'action', label: '' },
    ];

    Apply(): void {
            this.store.dispatch( this.actions.debitItemToReceiptItem(this._debitNote, this._receipt) );
    }
}
