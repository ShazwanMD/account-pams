import {Injectable} from '@angular/core';
import {Action} from '@ngrx/store';

@Injectable()
export class AdvancePaymentActions {
    
    static FIND_ADVANCE_PAYMENTS = '[AdvancePayment] Find advance payments';

    findAdvancePayments(): Action {
      return {
        type: AdvancePaymentActions.FIND_ADVANCE_PAYMENTS
      };
    }

    static FIND_ADVANCE_PAYMENTS_SUCCESS = '[AdvancePayment] Find advance payments Success';

    findAdvancePaymentsSuccess(payments): Action {
      return {
        type: AdvancePaymentActions.FIND_ADVANCE_PAYMENTS_SUCCESS,
        payload: payments
      };
    }
    
    static FIND_UNPAID_ADVANCE_PAYMENTS = '[AdvancePayment] Find Unpaid Advance Payments';

    findUnpaidAdvancePayments(account): Action {
      return {
        type: AdvancePaymentActions.FIND_UNPAID_ADVANCE_PAYMENTS,
        payload: account,
      };
    }

    static FIND_UNPAID_ADVANCE_PAYMENTS_SUCCESS = '[AdvancePayment] Find Unpaid Advance Payments';

    findUnpaidAdvancePaymentSuccess(payments): Action {
      console.log('findUnpaidAdvancePaymentSuccess');
      return {
        type: AdvancePaymentActions.FIND_UNPAID_ADVANCE_PAYMENTS_SUCCESS,
        payload: payments,
      };
    }
}