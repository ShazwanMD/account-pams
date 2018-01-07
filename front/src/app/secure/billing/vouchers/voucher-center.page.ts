import {Component, OnInit, ViewContainerRef, Input} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import {Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {RefundPaymentTask} from '../../../shared/model/billing/refund-payment-task.interface';
import {BillingModuleState} from '../index';
import {MdDialog, MdDialogConfig, MdDialogRef} from '@angular/material';
import { RefundPayment } from "../../../shared/model/billing/refund-payment.interface";
import { RefundPaymentActions } from "../refund-payments/refund-payment.action";
import { VoucherCreatorDialog } from "./dialog/voucher-creator.dialog";

@Component({
  selector: 'pams-voucher-center',
  templateUrl: './voucher-center.page.html',
})

export class VoucherCenterPage implements OnInit {
  
  private REFUND_PAYMENT = 'billingModuleState.refundPayments'.split('.');
  private refundPayment$: Observable<RefundPayment[]>;

  private creatorDialogRef: MdDialogRef<VoucherCreatorDialog>;
  
  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: RefundPaymentActions,
              private store: Store<BillingModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {
    this.refundPayment$ = this.store.select(...this.REFUND_PAYMENT);
  }

  goBack(route: string): void {
    this.router.navigate(['/vouchers']);
  }

  ngOnInit(): void {
    console.log('find assigned refund payment tasks');
    this.store.dispatch(this.actions.findCompletedRefundPayments());
  }
  
  VoucherCreatorDialog(payment:RefundPayment): void {
      this.showDialog(payment);
    }
  
  showDialog(payment:RefundPayment): void {
      console.log( 'showDialog' );
      console.log('payment' + payment.referenceNo);
      let config = new MdDialogConfig();
      config.viewContainerRef = this.vcf;
      config.role = 'dialog';
      config.width = '50%';
      config.height = '90%';
      config.position = { top: '0px' };
      this.creatorDialogRef = this.dialog.open( VoucherCreatorDialog, config );
      if(payment) this.creatorDialogRef.componentInstance.refundPayment = payment;
      this.creatorDialogRef.afterClosed().subscribe(( res ) => {
          console.log( 'close dialog' );
          // load something here
      } );
  }
}

