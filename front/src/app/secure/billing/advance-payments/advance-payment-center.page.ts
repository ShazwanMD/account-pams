import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { MdDialog, MdDialogConfig, MdDialogRef } from "@angular/material";
import { KnockoffCreatorDialog } from "../knockoffs/dialog/knockoff-creator.dialog";
import { AdvancePaymentActions } from "./advance-payment.action";
import { BillingModuleState } from "../index";
import { AdvancePayment } from "../../../shared/model/billing/advance-payment.interface";


@Component( {
    selector: 'pams-advance-payment-center',
    templateUrl: './advance-payment-center.page.html',
} )

export class AdvancePaymentCenterPage implements OnInit {

    private ADVANCE_PAYMENTS = 'billingModuleState.advancePayments'.split('.');
    private advancePayments$: Observable<AdvancePayment[]>;
    
    private creatorDialogRef: MdDialogRef<KnockoffCreatorDialog>;
    
    constructor( private router: Router,
        private route: ActivatedRoute,
        private vcf: ViewContainerRef,
        private actions: AdvancePaymentActions,
        private store: Store<BillingModuleState>,
        private dialog: MdDialog ) {
        
        this.advancePayments$ = this.store.select(...this.ADVANCE_PAYMENTS);
    }

    ngOnInit(): void {
        this.store.dispatch( this.actions.findAdvancePayments());
    }
    
    knockoffCreateDialog(payment:AdvancePayment): void {
        this.showDialog(payment);
      }

    showDialog(payment:AdvancePayment): void {
        console.log( 'showDialog' );
        let config = new MdDialogConfig();
        config.viewContainerRef = this.vcf;
        config.role = 'dialog';
        config.width = '50%';
        config.height = '90%';
        config.position = { top: '0px' };
        this.creatorDialogRef = this.dialog.open( KnockoffCreatorDialog, config );
        if(payment) this.creatorDialogRef.componentInstance.advancePayment = payment;
        this.creatorDialogRef.afterClosed().subscribe(( res ) => {
            console.log( 'close dialog' );
            // load something here
        } );
    }
}

