import {Injectable} from '@angular/core';
import {Action} from '@ngrx/store';

@Injectable()
export class AdvancePaymentActions {
    
    static FIND_UNPAID_ADVANCE_PAYMENTS = '[Invoice] Find Unpaid Invoices';

    findUnpaidAdvancePayments(account): Action {
      return {
        type: AdvancePaymentActions.FIND_UNPAID_ADVANCE_PAYMENTS,
        payload: account,
      };
    }

    static FIND_UNPAID_ADVANCE_PAYMENTS_SUCCESS = '[Invoice] Find Unpaid Invoices Success';

    findUnpaidAdvancePaymentSuccess(payments): Action {
      console.log('findUnpaidAdvancePaymentSuccess');
      return {
        type: AdvancePaymentActions.FIND_UNPAID_ADVANCE_PAYMENTS_SUCCESS,
        payload: payments,
      };
    }
}