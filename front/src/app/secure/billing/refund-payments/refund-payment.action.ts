import {Injectable} from '@angular/core';
import {Action} from '@ngrx/store';

@Injectable()
export class RefundPaymentActions {
    
    static FIND_REFUND_PAYMENTS = '[RefundPayment] Find RefundPayments';

    findRefundPayments(): Action {
      return {
        type: RefundPaymentActions.FIND_REFUND_PAYMENTS
      };
    }

    static FIND_REFUND_PAYMENTS_SUCCESS = '[RefundPayment] Find RefundPayments Success';

    findRefundPaymentsSuccess(refundPayment): Action {
      return {
        type: RefundPaymentActions.FIND_REFUND_PAYMENTS_SUCCESS,
        payload: refundPayment
      };
    }
      
    static FIND_REFUND_PAYMENT_BY_REFERENCE_NO = '[RefundPayment] Find RefundPayment By Reference No';

    findRefundPaymentByReferenceNo(referenceNo): Action {
      return {
        type: RefundPaymentActions.FIND_REFUND_PAYMENT_BY_REFERENCE_NO,
        payload: referenceNo
      };
    }

    static FIND_REFUND_PAYMENT_BY_REFERENCE_NO_SUCCESS = '[RefundPayment] Find RefundPayment By Reference No Success';

    findRefundPaymentByReferenceNoSuccess(refundPayment): Action {
      return {
        type: RefundPaymentActions.FIND_REFUND_PAYMENT_BY_REFERENCE_NO_SUCCESS,
        payload: refundPayment
      };
    }
    
    static FIND_REFUND_PAYMENT_TASK_BY_TASK_ID = '[RefundPayment] Find RefundPayment Task By Task Id';

    findRefundPaymentTaskByTaskId(taskId): Action {
      console.log('findRefundPaymentTaskByTaskId');
      return {
        type: RefundPaymentActions.FIND_REFUND_PAYMENT_TASK_BY_TASK_ID,
        payload: taskId,
      };
    }

    static FIND_REFUND_PAYMENT_TASK_BY_TASK_ID_SUCCESS = '[RefundPayment] Find RefundPayment Task By Task Id Success';

    findRefundPaymentTaskByTaskIdSuccess(task): Action {
      console.log('findRefundPaymentTaskByTaskIdSuccess');
      return {
        type: RefundPaymentActions.FIND_REFUND_PAYMENT_TASK_BY_TASK_ID_SUCCESS,
        payload: task,
      };
    }
    
    static START_REFUND_PAYMENT_TASK = '[RefundPayment] Start RefundPayment Task';
    startRefundPaymentTask(refundPayment, referenceNo): Action {
        console.log("RefundPayment dlm action: " + refundPayment);
      return {
        type: RefundPaymentActions.START_REFUND_PAYMENT_TASK,
        payload: {refundPayment, referenceNo}
      };
    }

    static START_REFUND_PAYMENT_TASK_SUCCESS = '[RefundPayment] Start RefundPayment Task Success';

    startRefundPaymentTaskSuccess(referenceNo): Action {
      return {
        type: RefundPaymentActions.START_REFUND_PAYMENT_TASK_SUCCESS,
        payload: referenceNo,
      };
    }

    static COMPLETE_REFUND_PAYMENT_TASK = '[RefundPayment] Complete RefundPayment Task';

    completeRefundPaymentTask(refundPayment): Action {
      return {
        type: RefundPaymentActions.COMPLETE_REFUND_PAYMENT_TASK,
        payload: refundPayment,
      };
    }

    static COMPLETE_REFUND_PAYMENT_TASK_SUCCESS = '[RefundPayment] Complete RefundPayment Task Success';

    completeRefundPaymentTaskSuccess(message): Action {
      return {
        type: RefundPaymentActions.COMPLETE_REFUND_PAYMENT_TASK_SUCCESS,
        payload: message,
      };
    }

    static CLAIM_REFUND_PAYMENT_TASK = '[RefundPayment] Assign RefundPayment Task';

    claimRefundPaymentTask(refundPayment): Action {
      return {
        type: RefundPaymentActions.CLAIM_REFUND_PAYMENT_TASK,
        payload: refundPayment,
      };
    }

    static CLAIM_REFUND_PAYMENT_TASK_SUCCESS = '[RefundPayment] Assign RefundPayment Task Success';

    claimRefundPaymentTaskSuccess(message): Action {
      return {
        type: RefundPaymentActions.CLAIM_REFUND_PAYMENT_TASK_SUCCESS,
        payload: message,
      };
    }

    static RELEASE_REFUND_PAYMENT_TASK = '[RefundPayment] Release RefundPayment Task';

    releaseRefundPaymentTask(refundPayment): Action {
      return {
        type: RefundPaymentActions.RELEASE_REFUND_PAYMENT_TASK,
        payload: refundPayment,
      };
    }

    static RELEASE_REFUND_PAYMENT_TASK_SUCCESS = '[RefundPayment] Release RefundPayment Task Success';

    releaseRefundPaymentTaskSuccess(message): Action {
      return {
        type: RefundPaymentActions.RELEASE_REFUND_PAYMENT_TASK_SUCCESS,
        payload: message,
      };
    }
    
    static FIND_COMPLETED_REFUND_PAYMENTS = '[RefundPayment] Find Completed RefundPayment';

    findCompletedRefundPayments(): Action {
      return {
        type: RefundPaymentActions.FIND_COMPLETED_REFUND_PAYMENTS,
      };
    }

    static FIND_COMPLETED_REFUND_PAYMENTS_SUCCESS = '[RefundPayment] Find Completed RefundPayment Success';

    findCompletedRefundPaymentsSuccess(refundPayments): Action {
      console.log('findCompletedRefundPaymentsSuccess');
      return {
        type: RefundPaymentActions.FIND_COMPLETED_REFUND_PAYMENTS_SUCCESS,
        payload: refundPayments,
      };
    }

    static FIND_ARCHIVED_REFUND_PAYMENTS = '[RefundPayment] Find Archived RefundPayment';

    findArchivedRefundPayments(): Action {
      return {
        type: RefundPaymentActions.FIND_ARCHIVED_REFUND_PAYMENTS,
      };
    }

    static FIND_ARCHIVED_REFUND_PAYMENTS_SUCCESS = '[RefundPayment] Find Archived RefundPayment Success';

    findArchivedRefundPaymentsSuccess(refundPayments): Action {
      console.log('findArchivedInvoicesSuccess');
      return {
        type: RefundPaymentActions.FIND_ARCHIVED_REFUND_PAYMENTS_SUCCESS,
        payload: refundPayments,
      };
    }

    static FIND_ASSIGNED_REFUND_PAYMENT_TASKS = '[RefundPayment] Find Assigned RefundPayment Tasks';

    findAssignedRefundPaymentTasks(): Action {
        console.log('find Assigned RefundPayment Tasks before success');
      return {
        type: RefundPaymentActions.FIND_ASSIGNED_REFUND_PAYMENT_TASKS,
      };
    }

    static FIND_ASSIGNED_REFUND_PAYMENT_TASKS_SUCCESS = '[RefundPayment] Find Assigned RefundPayment Tasks Success';

    findAssignedRefundPaymentTasksSuccess(tasks): Action {
      console.log('findAssignedRefundPaymentTasksSuccess');
      console.log('action refund payment' + tasks);
      return {
        type: RefundPaymentActions.FIND_ASSIGNED_REFUND_PAYMENT_TASKS_SUCCESS,
        payload: tasks,
      };
    }

    static FIND_POOLED_REFUND_PAYMENT_TASKS = '[RefundPayment] Find Pooled RefundPayment Tasks';

    findPooledRefundPaymentTasks(): Action {
      return {
        type: RefundPaymentActions.FIND_POOLED_REFUND_PAYMENT_TASKS,
      };
    }

    static FIND_POOLED_REFUND_PAYMENT_TASKS_SUCCESS = '[RefundPayment] Find Pooled RefundPayment Tasks Success';

    findPooledRefundPaymentTasksSuccess(refundPayments): Action {
      console.log('findPooledRefundPaymentsTasksSuccess');
      return {
        type: RefundPaymentActions.FIND_POOLED_REFUND_PAYMENT_TASKS_SUCCESS,
        payload: refundPayments,
      };
    }

    static REMOVE_REFUND_PAYMENT_TASK = '[RefundPayment] Remove RefundPayment Task';
    removeRefundPaymentTask(refundPaymentTask): Action {
        return {
            type: RefundPaymentActions.REMOVE_REFUND_PAYMENT_TASK,
            payload: refundPaymentTask,
      };
    }

    static REMOVE_REFUND_PAYMENT_TASK_SUCCESS = '[RefundPayment] Remove RefundPayment Task Success';
    removeRefundPaymentTaskSuccess(task): Action {
        return {
            type: RefundPaymentActions.REMOVE_REFUND_PAYMENT_TASK_SUCCESS,
            payload: task,
      };
    }
    
    static UPDATE_REFUND_PAYMENT_VOUCHER = '[RefundPayment] Update Refund Payment for voucher';
    updateRefundPayments(refundPayment): Action {
        console.log('RefundPaymentsVoucherSuccess');
        return {
            type: RefundPaymentActions.UPDATE_REFUND_PAYMENT_VOUCHER,
            payload: refundPayment,
      };
    }

    static UPDATE_REFUND_PAYMENT_VOUCHER_SUCCESS = '[RefundPayment] Remove RefundPayment Task Success';
    updateRefundPaymentsSuccess(message): Action {
        return {
            type: RefundPaymentActions.UPDATE_REFUND_PAYMENT_VOUCHER_SUCCESS,
            payload: message,
      };
    }
}