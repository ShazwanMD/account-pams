import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Observable} from 'rxjs';
import {Store} from '@ngrx/store';
import {MdDialog} from '@angular/material';
import { BillingModuleState } from "../index";
import { RefundPayment } from "../../../shared/model/billing/refund-payment.interface";
import { RefundPaymentActions } from "./refund-payment.action";

@Component({
  selector: 'pams-refund-payment-detail',
  templateUrl: './refund-payment-detail.page.html',
})
export class RefundPaymentDetailPage implements OnInit {

  private REFUND_PAYMENT: string[] = 'billingModuleState.refundPayment'.split('.');

  private refundPayments$: Observable<RefundPayment>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private store: Store<BillingModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog,
              private actions: RefundPaymentActions) {
    this.refundPayments$ = this.store.select(...this.REFUND_PAYMENT);
  }

  ngOnInit(): void {
    this.route.params.subscribe((params: { referenceNo: string }) => {
      let referenceNo: string = params.referenceNo;
      this.store.dispatch(this.actions.findRefundPaymentByReferenceNo(referenceNo));
    });
  }

  goBack(): void {
    this.router.navigate(['/secure/billing/refund-payments']);
  }
}

