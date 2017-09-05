import {Component, OnInit, ViewContainerRef, Input} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import {Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {RefundPaymentActions} from './refund-payment.action';
import {RefundPaymentTask} from '../../../shared/model/billing/refund-payment-task.interface';
import {BillingModuleState} from '../index';
import {MdDialog, MdDialogConfig, MdDialogRef} from '@angular/material';
import { RefundPayment } from "../../../shared/model/billing/refund-payment.interface";

@Component({
  selector: 'pams-refund-payment-center',
  templateUrl: './refund-payment-center.page.html',
})

export class RefundPaymentCenterPage implements OnInit {

  private ASSIGNED_REFUND_PAYMENT_TASKS : string[] = 'billingModuleState.assignedRefundPaymentTasks'.split('.');
  private POOLED_REFUND_PAYMENT_TASKS = 'billingModuleState.pooledRefundPaymentTasks'.split('.');
  private ARCHIVED_REFUND_PAYMENT = 'billingModuleState.archivedRefundPayments'.split('.');
  
  private REFUND_PAYMENT = 'billingModuleState.refundPayments'.split('.');
  private refundPayment$: Observable<RefundPayment[]>;

  private assignedRefundPaymentTasks$: Observable<RefundPaymentTask[]>;
  private pooledRefundPaymentTasks$: Observable<RefundPaymentTask[]>;
  private archivedRefundPayments$: Observable<RefundPayment[]>;

  
  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: RefundPaymentActions,
              private store: Store<BillingModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {
    this.assignedRefundPaymentTasks$ = this.store.select(...this.ASSIGNED_REFUND_PAYMENT_TASKS);
    this.pooledRefundPaymentTasks$ = this.store.select(...this.POOLED_REFUND_PAYMENT_TASKS);
    this.archivedRefundPayments$ = this.store.select(...this.ARCHIVED_REFUND_PAYMENT);
    this.refundPayment$ = this.store.select(...this.REFUND_PAYMENT);
  }

  goBack(route: string): void {
    this.router.navigate(['/refund-payments']);
  }

  claimTask(task: RefundPaymentTask) {
    console.log('invoice: ' + task.taskId);
    //this.store.dispatch(this.actions.claimRefundPaymentTask(task));
  }

  viewTask(task: RefundPaymentTask) {
    console.log('Task: ' + task.taskId);
    this.router.navigate(['/secure/billing/refund-payments/refund-payment-task-detail', task.taskId]);
  }

//  viewInvoice(invoice: Invoice) {
//    console.log('invoice: ' + invoice.referenceNo);
//    this.router.navigate(['/secure/billing/invoices/invoice-detail', invoice.referenceNo]);
//  }
//
//  showDialog(): void {
//    console.log('showDialog');
//    let config: MdDialogConfig = new MdDialogConfig();
//    config.viewContainerRef = this.vcf;
//    config.role = 'dialog';
//    config.width = '50%';
//    config.height = '65%';
//    config.position = {top: '0px'};
//    this.creatorDialogRef = this.dialog.open(InvoiceTaskCreatorDialog, config);
//    this.creatorDialogRef.afterClosed().subscribe((res) => {
//      console.log('close dialog');
//      // load something here
//    });
//  }

  ngOnInit(): void {
    console.log('find assigned refund payment tasks');
    this.store.dispatch(this.actions.findAssignedRefundPaymentTasks());
    this.store.dispatch(this.actions.findPooledRefundPaymentTasks());
    this.store.dispatch(this.actions.findArchivedRefundPayments());
    // this.store.dispatch(this.actions.findRefundPayments());
  }
}

