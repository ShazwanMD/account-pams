import { TdDialogService } from '@covalent/core';
import { Component, OnInit, ViewContainerRef, Input } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { BillingModuleState } from '../../index';
import { MdDialogRef } from '@angular/material';
import { Store } from '@ngrx/store';
import { Receipt } from '../../../../shared/model/billing/receipt.interface';
import { ReceiptItem } from '../../../../shared/model/billing/receipt-item.interface';
import { ReceiptInvoice } from '../../../../shared/model/billing/receipt-invoice.interface';
import { ChargeCode } from '../../../../shared/model/account/charge-code.interface';
import { Invoice } from '../../../../shared/model/billing/invoice.interface';
import { Knockoff } from "../../../../shared/model/billing/knockoff.interface";
import { KnockoffActions } from "../knockoff.action";
import { Observable } from "rxjs/Observable";
import { KnockoffItem } from "../../../../shared/model/billing/knockoff-item.interface";
import { AccountCharge } from '../../../../shared/model/account/account-charge.interface';
import { KnockoffItemType } from '../../../../shared/model/billing/knockoff-item-type.enum';

@Component( {
    selector: 'pams-knockoff-account-charge-item',
    templateUrl: './knockoff-account-charge-item.dialog.html',
} )

export class KnockoffAccountChargeItemDialog implements OnInit {

    private createForm: FormGroup;
    private displayForm: FormGroup;
    private _knockoff: Knockoff;
    private _accountCharge: AccountCharge;
    private edit: boolean = false;

    // private KNOCKOFF_ITEM: string[] = 'billingModuleState.knockoffItems'.split('.');
    // private knockoffItem$: Observable<KnockoffItem[]>;

    constructor( private router: Router,
        private route: ActivatedRoute,
        private formBuilder: FormBuilder,
        private viewContainerRef: ViewContainerRef,
        private store: Store<BillingModuleState>,
        private actions: KnockoffActions,
        private _dialogService: TdDialogService,
        private dialog: MdDialogRef<KnockoffAccountChargeItemDialog> ) {
        // this.knockoffItem$ = this.store.select(...this.KNOCKOFF_ITEM);
    }

    set knockoff( value: Knockoff ) {
        this._knockoff = value;
    }

    set accountCharge( value: AccountCharge ) {
        this._accountCharge = value;
        this.edit = true;
    }

    ngOnInit(): void {
        this.createForm = this.formBuilder.group( {
            id: [undefined],
            description: [''],
            dueAmount: [0],
            totalAmount: [0],
            appliedAmount: [0],
            accountCharge: [<AccountCharge>{}],
            knockoffItemType: KnockoffItemType.ACCOUNT_CHARGE,
           // knockoff: [<Knockoff>{}],
            
        } );

        


        if ( this.edit )
        this.createForm.patchValue( { accountCharge: this._accountCharge } );
        this.createForm.patchValue( { description: this._accountCharge.description } );
        this.createForm.patchValue( { dueAmount: this._accountCharge.balanceAmount } );

        if ( this._accountCharge.balanceAmount <= this._knockoff.payments.amount ) {
            this.createForm.patchValue( { appliedAmount: this._accountCharge.balanceAmount } );
            this.createForm.patchValue( { totalAmount: 0 } );
        }

        if ( this._accountCharge.balanceAmount > this._knockoff.payments.amount ) {
            this.createForm.patchValue( { appliedAmount: this._knockoff.payments.amount } );
            this.createForm.patchValue( { totalAmount: this._accountCharge.balanceAmount-this._knockoff.payments.amount } );
        }
     }

    submit( item: KnockoffItem, isValid: boolean ) {

        console.log( "hantar dh kan" + this._knockoff.referenceNo );
        console.log( "hantar dh kan item" + item );
        this.store.dispatch( this.actions.addKnockoffItem( this._knockoff, item ) );
        this.dialog.close();

        this._knockoff.totalAmount = this._knockoff.totalAmount - item.appliedAmount;
        this.store.dispatch( this.actions.updateKnockoff( this._knockoff ) );
    }
}
