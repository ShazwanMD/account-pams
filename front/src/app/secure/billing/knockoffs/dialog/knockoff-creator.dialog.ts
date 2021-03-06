import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Store } from '@ngrx/store';
import { MdDialogRef } from '@angular/material';
import { KnockoffActions } from "../../knockoffs/knockoff.action";
import { BillingModuleState } from '../../index';
import { Knockoff } from "../../../../shared/model/billing/knockoff.interface";
import { Invoice } from "../../../../shared/model/billing/invoice.interface";
import { AdvancePayment } from "../../../../shared/model/billing/advance-payment.interface";
import { AdvancePaymentActions } from "../../advance-payments/advance-payment.action";

@Component( {
    selector: 'pams-knockoff-creator',
    templateUrl: './knockoff-creator.dialog.html',
} )

export class KnockoffCreatorDialog {

    private createForm: FormGroup;
    private _advancePayment: AdvancePayment;
    private edit: boolean = false;

    constructor( private formBuilder: FormBuilder,
        private store: Store<BillingModuleState>,
        private actions: KnockoffActions,
        private action: AdvancePaymentActions,
        private dialog: MdDialogRef<KnockoffCreatorDialog> ) {
    }

    set advancePayment( value: AdvancePayment ) {
        this._advancePayment = value;
    }

    ngOnInit(): void {
        this.createForm = this.formBuilder.group({
            id: [null],
            referenceNo: [''],
            sourceNo: [''],
            auditNo: [''],
            description: [''],
            amount: [0],
            balanceAmount: [0],
            totalAmount: 0,
            issuedDate: [undefined,Validators.required],
            invoice: [<Invoice>{}],
            payments: [<AdvancePayment>{}],
        } );

        this.createForm.patchValue( { payments: this._advancePayment} );
    }

    save( knockoff: Knockoff, isValid: boolean ) {
        if(isValid)
            knockoff.payments = this._advancePayment;
            knockoff.amount = this._advancePayment.balanceAmount;
            knockoff.totalAmount = this._advancePayment.balanceAmount;
            knockoff.balanceAmount = this._advancePayment.balanceAmount;
        
        if(isValid)
        this.store.dispatch( this.actions.startKnockoffTask(knockoff) );
        this.dialog.close();
    }

}
