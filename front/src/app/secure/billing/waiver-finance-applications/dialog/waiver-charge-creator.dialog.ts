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
import { WaiverItemType } from '../../../../shared/model/billing/waiver-item-type.enum';

@Component( {
    selector: 'pams-waiver-charge-creator',
    templateUrl: './waiver-charge-creator.dialog.html',
} )

export class WaiverChargeCreatorDialog implements OnInit {

    private createForm: FormGroup;
    private displayForm: FormGroup;
    private __waiverApplication: WaiverFinanceApplication;
    private _accountCharge: AccountCharge;
    private edit: boolean = false;

    constructor( private router: Router,
        private route: ActivatedRoute,
        private formBuilder: FormBuilder,
        private viewContainerRef: ViewContainerRef,
        private store: Store<BillingModuleState>,
        private actions: WaiverFinanceApplicationActions,
        private _dialogService: TdDialogService,
        private dialog: MdDialogRef<WaiverChargeCreatorDialog> ) {
    }

    set waiverFinanceApplication(value: WaiverFinanceApplication) {
        this.__waiverApplication = value;
      }

    set accountCharge( value: AccountCharge ) {
        this._accountCharge = value;
        this.edit = true;

    }

    ngOnInit(): void {
        this.createForm = this.formBuilder.group({
            id: [undefined],
            description: [''],
            dueAmount: [0],
            totalAmount:[0],
            appliedAmount: [0],
            waiverItemType: WaiverItemType.ACCOUNT_CHARGE,
            accountCharge: [<AccountCharge>{}]
        } );

        if ( this.edit )
            this.createForm.patchValue( { accountCharge: this._accountCharge } );
            this.createForm.patchValue( { description: this._accountCharge.description } );
            this.createForm.patchValue( { dueAmount: this._accountCharge.balanceAmount } );
            
            if(this._accountCharge.balanceAmount <= this.__waiverApplication.gracedAmount) {
                this.createForm.patchValue( { appliedAmount: this._accountCharge.balanceAmount } );
                this.createForm.patchValue( { totalAmount: 0 } );
            } 
            
            if(this._accountCharge.balanceAmount > this.__waiverApplication.gracedAmount){
                this.createForm.patchValue( { appliedAmount: this.__waiverApplication.gracedAmount } );
                this.createForm.patchValue( { totalAmount: this._accountCharge.balanceAmount-this.__waiverApplication.gracedAmount } );
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
