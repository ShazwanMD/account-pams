import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { MdDialog, MdDialogConfig, MdDialogRef } from "@angular/material";
import { KnockoffCreatorDialog } from "../knockoffs/dialog/knockoff-creator.dialog";
import { AdvancePaymentActions } from "./advance-payment.action";
import { BillingModuleState } from "../index";
import { AdvancePayment } from "../../../shared/model/billing/advance-payment.interface";
import { Knockoff } from "../../../shared/model/billing/knockoff.interface";
import { KnockoffActions } from "../knockoffs/knockoff.action";
import { RefundPaymentTaskCreatorDialog } from "../refund-payments/dialog/refund-payment-task-creator.dialog";


@Component( {
    selector: 'pams-advance-payment-center',
    templateUrl: './advance-payment-center.page.html',
} )

export class AdvancePaymentCenterPage implements OnInit {

    private ADVANCE_PAYMENTS = 'billingModuleState.advancePayments'.split('.');
    //private KNOCKOFF = 'billingModuleState.knockoffs'.split('.');
    private advancePayments$: Observable<AdvancePayment[]>;
    //private knockoff$: Observable<Knockoff[]>;
    private creatorDialogRef: MdDialogRef<KnockoffCreatorDialog>;
    private dialogRef: MdDialogRef<RefundPaymentTaskCreatorDialog>;
    
    constructor( private router: Router,
        private route: ActivatedRoute,
        private vcf: ViewContainerRef,
        private actions: AdvancePaymentActions,
        private action: KnockoffActions,
        private store: Store<BillingModuleState>,
        private dialog: MdDialog ) {
        
        this.advancePayments$ = this.store.select(...this.ADVANCE_PAYMENTS);
        //this.knockoff$ = this.store.select(...this.KNOCKOFF);
    }

    ngOnInit(): void {
        this.store.dispatch( this.actions.findAdvancePayments());
        this.store.dispatch( this.action.findKnockoffs());
    }
    
    knockoffCreateDialog(payment:AdvancePayment): void {
        this.showDialog(payment);
      }

    showDialog(payment:AdvancePayment): void {
        console.log( 'showDialog' );
        console.log('payment' + payment.referenceNo);
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

    refundPaymentCreateDialog(payment:AdvancePayment): void {
        this.showDialogRefundPayment(payment);
      }

    showDialogRefundPayment(payment:AdvancePayment): void {
        console.log( 'showDialog' );
        console.log('payment' + payment.referenceNo);
        let config = new MdDialogConfig();
        config.viewContainerRef = this.vcf;
        config.role = 'dialog';
        config.width = '50%';
        config.height = '90%';
        config.position = { top: '0px' };
        this.dialogRef = this.dialog.open( RefundPaymentTaskCreatorDialog, config );
        if(payment) this.dialogRef.componentInstance.advancePayment = payment;
        this.dialogRef.afterClosed().subscribe(( res ) => {
            console.log( 'close dialog' );
            // load something here
        } );
    }
}

