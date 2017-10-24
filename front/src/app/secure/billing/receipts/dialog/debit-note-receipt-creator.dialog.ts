import { DebitNote } from './../../../../shared/model/billing/debit-note.interface';
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
import { AccountCharge } from "../../../../shared/model/account/account-charge.interface";
import { ReceiptItemType } from '../../../../shared/model/billing/receipt-item-type.enum';

@Component( {
    selector: 'pams-debit-note-receipt-creator',
    templateUrl: './debit-note-receipt-creator.dialog.html',
} )

export class DebitNoteReceiptCreatorDialog implements OnInit {

    private createForm: FormGroup;
    private displayForm: FormGroup;
    private _receipt: Receipt;
    private _debitNote: DebitNote;
    private edit: boolean = false;

    constructor( private router: Router,
        private route: ActivatedRoute,
        private formBuilder: FormBuilder,
        private viewContainerRef: ViewContainerRef,
        private store: Store<BillingModuleState>,
        private actions: ReceiptActions,
        private _dialogService: TdDialogService,
        private dialog: MdDialogRef<DebitNoteReceiptCreatorDialog> ) {
    }

    set receipt( value: Receipt ) {
        this._receipt = value;
    }

    set debitNote( value: DebitNote ) {
        this._debitNote = value;
        this.edit = true;

    }

    ngOnInit(): void {
        this.createForm = this.formBuilder.group({
            id: [undefined],
            description: [''],
            dueAmount: [0],
            totalAmount:[0],
            adjustedAmount: [0],
            appliedAmount: [0],
            price: [0],
            unit: [0],
            receiptItemType: ReceiptItemType.DEBIT_NOTE,
            debitNote: [<DebitNote>{}]
        } );

        if ( this.edit )
            this.createForm.patchValue( { debitNote: this._debitNote } );
            this.createForm.patchValue( { invoice: this._debitNote.invoice } );
            this.createForm.patchValue( { chargeCode: this._debitNote.chargeCode } );
            this.createForm.patchValue( { description: this._debitNote.description } );
            this.createForm.patchValue( { dueAmount: this._debitNote.balanceAmount } );
            
            if(this._debitNote.balanceAmount <= this._receipt.totalPayment) {
                this.createForm.patchValue( { appliedAmount: this._debitNote.balanceAmount } );
                this.createForm.patchValue( { totalAmount: 0 } );
            } 
            
            if(this._debitNote.balanceAmount > this._receipt.totalPayment){
                this.createForm.patchValue( { appliedAmount: this._receipt.totalPayment } );
                this.createForm.patchValue( { totalAmount: this._debitNote.balanceAmount-this._receipt.totalPayment } );
            }
    }

    submit( item: ReceiptItem, isValid: boolean ) {
      
        console.log("hantar dh kan" + this._receipt.referenceNo);
        this.store.dispatch( this.actions.addReceiptItem( this._receipt, item ) );
        this.dialog.close();
        
        this._receipt.totalPayment = this._receipt.totalPayment - item.appliedAmount;
        this.store.dispatch( this.actions.updateReceipt(this._receipt) );
    }
}
