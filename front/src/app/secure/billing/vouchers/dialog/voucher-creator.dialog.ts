import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Store } from '@ngrx/store';
import { MdDialogRef } from '@angular/material';
import { AcademicSession } from '../../../../shared/model/account/academic-session.interface';
import { Account } from '../../../../shared/model/account/account.interface';
import { BillingModuleState } from '../../index';
import { Invoice } from '../../../../shared/model/billing/invoice.interface';
import { RefundPayment } from "../../../../shared/model/billing/refund-payment.interface";
import { RefundPaymentActions } from "../../refund-payments/refund-payment.action";

@Component( {
    selector: 'pams-voucher-creator',
    templateUrl: './voucher-creator.dialog.html',
} )

export class VoucherCreatorDialog implements OnInit {

    private _refundPayment : RefundPayment;
    private createForm: FormGroup;

    constructor( private formBuilder: FormBuilder,
        private store: Store<BillingModuleState>,
        private actions: RefundPaymentActions,
        private dialog: MdDialogRef<VoucherCreatorDialog> ) {
    }

    set refundPayment( value: RefundPayment ) {
        this._refundPayment = value;
    }

    ngOnInit(): void {
        this.createForm = this.formBuilder.group( {
            id: [undefined],
            voucherNo: [''],
        } );
    }

    save( refundPayment: RefundPayment, isValid: boolean ) {
        console.log('payment ref no' + this._refundPayment.referenceNo);
        this.store.dispatch( this.actions.updateRefundPayments(this._refundPayment) );
        this.dialog.close();
    }
}
