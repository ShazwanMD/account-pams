import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
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
        this.edit = true;
    }

    ngOnInit(): void {
        this.createForm = this.formBuilder.group( <Knockoff>{
            id: null,
            referenceNo: '',
            sourceNo: '',
            auditNo: '',
            description: '',
            amount: 0,
            issuedDate: undefined,
            invoice: <Invoice>{},
            advancePayment: <AdvancePayment>{},
        } );

        if ( this.edit ) this.createForm.patchValue( { advancePayment: this._advancePayment } );
    }

    save( knockoff: Knockoff, isValid: boolean ) {
        this.store.dispatch( this.actions.saveKnockoff( knockoff, this._advancePayment) );
        
        let calculateBalance = 0;
        calculateBalance = this._advancePayment.balanceAmount -  knockoff.amount;
        
        this._advancePayment.balanceAmount = calculateBalance;
        if(this._advancePayment.balanceAmount == 0)
            this._advancePayment.status = true;
        
        this.store.dispatch( this.action.updateAdvancePayment(this._advancePayment));
        this.dialog.close();
    }

}
