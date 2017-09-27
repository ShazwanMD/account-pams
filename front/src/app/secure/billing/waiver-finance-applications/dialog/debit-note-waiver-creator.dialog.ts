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
import { WaiverFinanceApplication } from "../../../../shared/model/billing/waiver-finance-application.interface";
import { WaiverFinanceApplicationActions } from "../waiver-finance-application.action";
import { WaiverItem } from "../../../../shared/model/billing/waiver-item.interface";

@Component( {
    selector: 'pams-debit-note-waiver-creator',
    templateUrl: './debit-note-waiver-creator.dialog.html',
} )

export class DebitNoteWaiverCreatorDialog implements OnInit {

    private createForm: FormGroup;
    private __waiverApplication: WaiverFinanceApplication;
    private _debitNote: DebitNote;
    private edit: boolean = false;

    constructor( private router: Router,
        private route: ActivatedRoute,
        private formBuilder: FormBuilder,
        private viewContainerRef: ViewContainerRef,
        private store: Store<BillingModuleState>,
        private actions: WaiverFinanceApplicationActions,
        private _dialogService: TdDialogService,
        private dialog: MdDialogRef<DebitNoteWaiverCreatorDialog> ) {
    }

    set waiverFinanceApplication(value: WaiverFinanceApplication) {
        this.__waiverApplication = value;
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
            appliedAmount: [0],
            chargeCode: [<ChargeCode>{}],
            invoice: [<Invoice>{}],
            debitNote: [<DebitNote>{}]
        } );

        if ( this.edit )
            this.createForm.patchValue( { debitNote: this._debitNote } );
            this.createForm.patchValue( { invoice: this._debitNote.invoice } );
            this.createForm.patchValue( { chargeCode: this._debitNote.chargeCode } );
            this.createForm.patchValue( { description: this._debitNote.description } );
            this.createForm.patchValue( { dueAmount: this._debitNote.balanceAmount } );
            
            console.log("Debit note" + this._debitNote.balanceAmount);
            console.log("wf" + this.__waiverApplication);
            if(this._debitNote.balanceAmount <= this.__waiverApplication.gracedAmount) {
                this.createForm.patchValue( { appliedAmount: this._debitNote.balanceAmount } );
                this.createForm.patchValue( { totalAmount: 0 } );
            } 
            
            if(this._debitNote.balanceAmount > this.__waiverApplication.gracedAmount){
                this.createForm.patchValue( { appliedAmount: this.__waiverApplication.gracedAmount } );
                this.createForm.patchValue( { totalAmount: this._debitNote.balanceAmount-this.__waiverApplication.gracedAmount } );
            }
    }

    submit( item: WaiverItem, isValid: boolean ) {
      
        console.log("hantar dh kan" + this.__waiverApplication.referenceNo);
        this.store.dispatch( this.actions.addWaiverItem( this.__waiverApplication, item) );
        this.dialog.close();
        
        this.__waiverApplication.gracedAmount  = this.__waiverApplication.gracedAmount - item.appliedAmount;
        this.store.dispatch( this.actions.updateWaivers(this.__waiverApplication) );
    }
}
