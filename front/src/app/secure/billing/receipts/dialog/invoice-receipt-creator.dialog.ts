import { Component, OnInit, ViewContainerRef, Input } from '@angular/core';
import { FormBuilder, FormGroup, FormArray } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ReceiptItem } from '../../../../shared/model/billing/receipt-item.interface';
import { Receipt } from '../../../../shared/model/billing/receipt.interface';
import { BillingModuleState } from '../../index';
import { MdDialogRef } from '@angular/material';
import { ReceiptActions } from '../receipt.action';
import { Store } from '@ngrx/store';
import { Invoice } from "../../../../shared/model/billing/invoice.interface";
import { InvoiceItem } from "../../../../shared/model/billing/invoice-item.interface";
import { Account } from "../../../../shared/model/account/account.interface";
import { TdDialogService, ITdDataTableColumn } from "@covalent/core";
import { ChargeCode } from "../../../../shared/model/account/charge-code.interface";
import { Observable } from "rxjs/Observable";

@Component( {
    selector: 'pams-invoice-receipt-creator',
    templateUrl: './invoice-receipt-creator.dialog.html',
} )

export class InvoiceReceiptCreatorDialog implements OnInit {

    private createForm: FormGroup;
    private displayForm: FormGroup;
    private _receipt: Receipt;
    private _invoice: Invoice;
    private edit: boolean = false;

    private RECEIPT_ITEMS: string[] = 'billingModuleState.receiptItems'.split('.');
    private receiptItems$: Observable<ReceiptItem[]>;

    constructor( private router: Router,
        private route: ActivatedRoute,
        private formBuilder: FormBuilder,
        private viewContainerRef: ViewContainerRef,
        private store: Store<BillingModuleState>,
        private actions: ReceiptActions,
        private _dialogService: TdDialogService,
        private dialog: MdDialogRef<InvoiceReceiptCreatorDialog> ) {
        
        this.receiptItems$ = this.store.select(...this.RECEIPT_ITEMS);
    }

    set receipt( value: Receipt ) {
        this._receipt = value;
    }

    set invoice( value: Invoice ) {
        this._invoice = value;
        this.edit = true;

    }

    ngOnInit(): void {
        this.displayForm = this.formBuilder.group( {
            invoiceItem: <InvoiceItem>{},
        } );
        this.createForm = this.formBuilder.group({
            id: [undefined],
            description: [''],
            dueAmount: [0],
            totalAmount:[0],
            adjustedAmount: [0],
            appliedAmount: [0],
            price: [0],
            unit: [0],
            chargeCode: [<ChargeCode>{}],
            invoice: [<Invoice>{}],
        } );

        if ( this.edit )
            this.createForm.patchValue( { invoice: this._invoice } );
    }

    display( invoiceItem: InvoiceItem, isValid: boolean ) {
        console.log( "Invc Item from select component" +
            this.displayForm.get( 'invoiceItem' ).value.chargeCode.code );

        let x = this.displayForm.get( 'invoiceItem' ).value;

        if ( isValid ) {
            this.createForm.patchValue( { description: x.chargeCode.description } );
            this.createForm.patchValue( { dueAmount: x.amount } );
            this.createForm.patchValue( { chargeCode: x.chargeCode } );
        }

    }

    submit( item: ReceiptItem, isValid: boolean ) {
      
        console.log("hantar dh kan" + this._receipt.referenceNo);
        this.store.dispatch( this.actions.addReceiptItem( this._receipt, item ) );
        this.dialog.close();
    }
}
