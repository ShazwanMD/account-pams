import { KnockoffActions } from './../knockoff.action';
import { KnockoffItem } from './../../../../shared/model/billing/knockoff-item.interface';
import { Knockoff } from './../../../../shared/model/billing/knockoff.interface';
import { DebitNoteReceiptCreatorDialog } from './../../receipts/dialog/debit-note-receipt-creator.dialog';
import { DebitNote } from './../../../../shared/model/billing/debit-note.interface';
import { Component, OnInit, ViewContainerRef, Input } from '@angular/core';
import { FormBuilder, FormGroup, FormArray } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ReceiptItem } from '../../../../shared/model/billing/receipt-item.interface';
import { Receipt } from '../../../../shared/model/billing/receipt.interface';
import { BillingModuleState } from '../../index';
import { MdDialogRef } from '@angular/material';
import { Store } from '@ngrx/store';
import { Invoice } from "../../../../shared/model/billing/invoice.interface";
import { InvoiceItem } from "../../../../shared/model/billing/invoice-item.interface";
import { Account } from "../../../../shared/model/account/account.interface";
import { TdDialogService, ITdDataTableColumn } from "@covalent/core";
import { ChargeCode } from "../../../../shared/model/account/charge-code.interface";
import { Observable } from "rxjs/Observable";
import { AccountCharge } from "../../../../shared/model/account/account-charge.interface";
import { KnockoffItemType } from '../../../../shared/model/billing/knockoff-item-type.enum';

@Component( {
    selector: 'pams-debit-note-knockoff-creator',
    templateUrl: './debit-note-knockoff-creator.dialog.html',
} )

export class DebitNoteKnockoffCreatorDialog implements OnInit {

    private createForm: FormGroup;
    private displayForm: FormGroup;
    private _knockoff: Knockoff;
    private _debitNote: DebitNote;
    private edit: boolean = false;

    constructor( private router: Router,
        private route: ActivatedRoute,
        private formBuilder: FormBuilder,
        private viewContainerRef: ViewContainerRef,
        private store: Store<BillingModuleState>,
        private actions: KnockoffActions,
        private _dialogService: TdDialogService,
        private dialog: MdDialogRef<DebitNoteKnockoffCreatorDialog> ) {
    }

    set knockoff( value: Knockoff ) {
        this._knockoff = value;
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
            chargeCode: [<ChargeCode>{}],
            invoice: [<Invoice>{}],
            knockoffItemType: KnockoffItemType.DEBIT_NOTE,
            debitNote: [<DebitNote>{}]
        } );

        if ( this.edit )
            this.createForm.patchValue( { debitNote: this._debitNote } );
            this.createForm.patchValue( { invoice: this._debitNote.invoice } );
            this.createForm.patchValue( { chargeCode: this._debitNote.chargeCode } );
            this.createForm.patchValue( { description: this._debitNote.description } );
            this.createForm.patchValue( { dueAmount: this._debitNote.balanceAmount } );
            
            if(this._debitNote.balanceAmount <= this._knockoff.payments.amount) {
                this.createForm.patchValue( { appliedAmount: this._debitNote.balanceAmount } );
                this.createForm.patchValue( { totalAmount: 0 } );
            } 
            
            if(this._debitNote.balanceAmount > this._knockoff.payments.amount){
                this.createForm.patchValue( { appliedAmount: this._knockoff.payments.amount} );
                this.createForm.patchValue( { totalAmount: this._debitNote.balanceAmount-this._knockoff.payments.amount } );
            }
    }

    submit( item: KnockoffItem, isValid: boolean ) {
      
        console.log("hantar dh kan" + this._knockoff.referenceNo);
        this.store.dispatch( this.actions.addKnockoffItem( this._knockoff, item ) );
        this.dialog.close();
        
        this._knockoff.totalAmount = this._knockoff.totalAmount - item.appliedAmount;
        this.store.dispatch( this.actions.updateKnockoff(this._knockoff) );
    }
}
