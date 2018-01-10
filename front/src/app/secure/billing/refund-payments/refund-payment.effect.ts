import { Injectable } from '@angular/core';
import { Actions, Effect } from '@ngrx/effects';
import { from } from 'rxjs/observable/from';
import { Store } from '@ngrx/store';
import { BillingService } from '../../../../services/billing.service';
import { BillingModuleState } from '../index';
import { RefundPaymentActions } from './refund-payment.action';
import { NotificationService } from "../../../../services/notification.service";

@Injectable()
export class RefundPaymentEffects {

    //private REFUND_PAYMENT: string[] = 'billingModuleState.refundPayments'.split( '.' );
    //private REFUND_PAYMENT_TASK: string[] = 'billingModuleState.refundPayments'.split('.');

    constructor( private actions$: Actions,
        private refundPaymentActions: RefundPaymentActions,
        private billingService: BillingService,
        private store$: Store<BillingModuleState> ) {
    }

    @Effect() findRefundPayments$ = this.actions$
        .ofType( RefundPaymentActions.FIND_REFUND_PAYMENTS )
        .map(( action ) => action.payload )
        .switchMap(() => this.billingService.findRefundPayments() )
        .map(( refundPayment ) => this.refundPaymentActions.findRefundPaymentsSuccess( refundPayment ) );

    @Effect() findRefundPaymentByReferenceNo$ = this.actions$
        .ofType( RefundPaymentActions.FIND_REFUND_PAYMENT_BY_REFERENCE_NO )
        .map(( action ) => action.payload )
        .switchMap(( referenceNo ) => this.billingService.findRefundPaymentByReferenceNo( referenceNo ) )
        .map(( refundPayment ) => this.refundPaymentActions.findRefundPaymentByReferenceNoSuccess( refundPayment ) );

    @Effect() findCompletedRefundPayments$ = this.actions$
        .ofType( RefundPaymentActions.FIND_COMPLETED_REFUND_PAYMENTS )
        .switchMap(() => this.billingService.findCompletedRefundPayments() )
        .map(( refundPayments ) => this.refundPaymentActions.findCompletedRefundPaymentsSuccess( refundPayments ) );

    @Effect() findArchivedRefundPayments$ = this.actions$
        .ofType( RefundPaymentActions.FIND_ARCHIVED_REFUND_PAYMENTS )
        .switchMap(() => this.billingService.findArchivedRefundPayments() )
        .map(( refundPayments ) => this.refundPaymentActions.findArchivedRefundPaymentsSuccess( refundPayments ) );

    @Effect() findAssignedRefundPaymentTasks$ = this.actions$
    .ofType(RefundPaymentActions.FIND_ASSIGNED_REFUND_PAYMENT_TASKS)
    .switchMap(() => this.billingService.findAssignedRefundPaymentTasks())
    .map((refundPayments) => this.refundPaymentActions.findAssignedRefundPaymentTasksSuccess(refundPayments));

    
    @Effect() findPooledRefundPaymentTasks$ = this.actions$
        .ofType( RefundPaymentActions.FIND_POOLED_REFUND_PAYMENT_TASKS )
        .switchMap(() => this.billingService.findPooledRefundPaymentTasks() )
        .map(( refundPayments ) => this.refundPaymentActions.findPooledRefundPaymentTasksSuccess(refundPayments) );

    @Effect() findRefundPaymentTaskByTaskId$ = this.actions$
        .ofType( RefundPaymentActions.FIND_REFUND_PAYMENT_TASK_BY_TASK_ID )
        .map(( action ) => action.payload )
        .switchMap(( taskId ) => this.billingService.findRefundPaymentTaskByTaskId(taskId))
        .map(( task ) => this.refundPaymentActions.findRefundPaymentTaskByTaskIdSuccess(task) );
    
    @Effect() updateRefundPayments$ = this.actions$
    .ofType( RefundPaymentActions.UPDATE_REFUND_PAYMENT_VOUCHER )
    .map(( action ) => action.payload )
    .switchMap(( refundPayment ) => this.billingService.updateRefundPayments(refundPayment))
    .map(( message ) => this.refundPaymentActions.updateRefundPaymentsSuccess(message) );
    
    @Effect() startRefundPaymentTask$ = this.actions$
    .ofType(RefundPaymentActions.START_REFUND_PAYMENT_TASK)
    .map((action) => action.payload)
    .switchMap((payload) => this.billingService.startRefundPaymentTask(payload.refundPayment, payload.referenceNo))
    .map((referenceNo) => this.refundPaymentActions.startRefundPaymentTaskSuccess(referenceNo))
    .mergeMap((action) => from([action,
        this.refundPaymentActions.findAssignedRefundPaymentTasks(),
        this.refundPaymentActions.findPooledRefundPaymentTasks(),
      ],
    ));

    @Effect() removeRefundPaymentTask$ = this.actions$
    .ofType(RefundPaymentActions.REMOVE_REFUND_PAYMENT_TASK)
    .map((action) => action.payload)
    .switchMap((refundPaymentTask) => this.billingService.removeRefundPaymentTask(refundPaymentTask))
    .map((task) => this.refundPaymentActions.removeRefundPaymentTaskSuccess(task))
    .mergeMap((action) => from([action,
                                this.refundPaymentActions.findAssignedRefundPaymentTasks(),
                                this.refundPaymentActions.findPooledRefundPaymentTasks(),
                                this.refundPaymentActions.findArchivedRefundPayments(),
      ],
    ));
    
  @Effect() completeRefundPaymentTask$ = this.actions$
    .ofType(RefundPaymentActions.COMPLETE_REFUND_PAYMENT_TASK)
    .map((action) => action.payload)
    .switchMap((refundPaymentTask) => this.billingService.completeRefundPaymentTask(refundPaymentTask))
    .map((message) => this.refundPaymentActions.completeRefundPaymentTaskSuccess(message))
    .mergeMap((action) => from([action,
        this.refundPaymentActions.findAssignedRefundPaymentTasks(),
        this.refundPaymentActions.findPooledRefundPaymentTasks(),
        this.refundPaymentActions.findArchivedRefundPayments(),
      ],
    ));

  @Effect() claimRefundPaymentTask$ = this.actions$
    .ofType(RefundPaymentActions.CLAIM_REFUND_PAYMENT_TASK)
    .map((action) => action.payload)
    .switchMap((refundPaymentTask) => this.billingService.claimRefundPaymentTask(refundPaymentTask))
    .map((message) => this.refundPaymentActions.claimRefundPaymentTaskSuccess(message))
    .mergeMap((action) => from([action,
    this.refundPaymentActions.findAssignedRefundPaymentTasks(),
     this.refundPaymentActions.findPooledRefundPaymentTasks(),
      ],
    ));

  @Effect() releaseRefundPaymentTask$ = this.actions$
    .ofType(RefundPaymentActions.RELEASE_REFUND_PAYMENT_TASK)
    .map((action) => action.payload)
    .switchMap((refundPaymentTask) => this.billingService.releaseRefundPaymentTask(refundPaymentTask))
    .map((message) => this.refundPaymentActions.releaseRefundPaymentTaskSuccess(message))
    .mergeMap((action) => from([action,
    this.refundPaymentActions.findAssignedRefundPaymentTasks(),
    this.refundPaymentActions.findPooledRefundPaymentTasks(),
      ],
    ));
}