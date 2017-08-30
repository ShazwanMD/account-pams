import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {RefundPaymentActions} from './refund-payment.action';
import {Observable} from 'rxjs';
import {BillingModuleState} from '../index';
import {Store} from '@ngrx/store';
import {RefundPaymentTask} from '../../../shared/model/billing/refund-payment-task.interface';

@Component({
  selector: 'pams-refund-payment-task-detail',
  templateUrl: './refund-payment-task-detail.page.html',
})
export class RefundPaymentTaskDetailPage implements OnInit {

  private REFUND_PAYMENT_TASK: string[] = 'billingModuleState.refundPaymentTask'.split('.');
  private refundPaymentTask$: Observable<RefundPaymentTask>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private store: Store<BillingModuleState>,
              private actions: RefundPaymentActions) {
    this.refundPaymentTask$ = this.store.select(...this.REFUND_PAYMENT_TASK);
  }

  ngOnInit(): void {
    this.route.params.subscribe((params: { taskId: string }) => {
      let taskId: string = params.taskId;
      this.store.dispatch(this.actions.findRefundPaymentTaskByTaskId(taskId));
    });
  }

  goBack(): void {
    this.router.navigate(['/secure/billing/refundPaymentTasks']);
  }
}

