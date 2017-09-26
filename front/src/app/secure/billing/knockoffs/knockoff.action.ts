import { Injectable } from '@angular/core';
import { Action } from '@ngrx/store';

@Injectable()
export class KnockoffActions {

    static FIND_KNOCKOFFS = '[Knockoff] Find Knockoffs';

    findKnockoffs(): Action {
        return {
            type: KnockoffActions.FIND_KNOCKOFFS
        };
    }

    static FIND_KNOCKOFFS_SUCCESS = '[Knockoff] Find Knockoffs Success';

    findKnockoffsSuccess( knockoff ): Action {
        return {
            type: KnockoffActions.FIND_KNOCKOFFS_SUCCESS,
            payload: knockoff
        };
    }

    static FIND_KNOCKOFF_BY_REFERENCE_NO = '[Knockoff] Find Knockoff By Reference No';

    findKnockoffByReferenceNo( referenceNo ): Action {
        return {
            type: KnockoffActions.FIND_KNOCKOFF_BY_REFERENCE_NO,
            payload: referenceNo
        };
    }

    static FIND_KNOCKOFF_BY_REFERENCE_NO_SUCCESS = '[Knockoff] Find Knockoff By Reference No Success';

    findKnockoffByReferenceNoSuccess( knockoff ): Action {
        return {
            type: KnockoffActions.FIND_KNOCKOFF_BY_REFERENCE_NO_SUCCESS,
            payload: knockoff
        };
    }

    static FIND_KNOCKOFF_TASK_BY_TASK_ID = '[Knockoff] Find Knockoff Task By Task Id';

    findKnockoffTaskByTaskId( taskId ): Action {
        console.log( 'findKnockoffTaskByTaskId' );
        return {
            type: KnockoffActions.FIND_KNOCKOFF_TASK_BY_TASK_ID,
            payload: taskId,
        };
    }

    static FIND_KNOCKOFF_TASK_BY_TASK_ID_SUCCESS = '[Knockoff] Find Knockoff Task By Task Id Success';

    findKnockoffTaskByTaskIdSuccess( task ): Action {
        console.log( 'findKnockoffTaskByTaskIdSuccess' );
        return {
            type: KnockoffActions.FIND_KNOCKOFF_TASK_BY_TASK_ID_SUCCESS,
            payload: task,
        };
    }

    static START_KNOCKOFF_TASK = '[Knockoff] Start Knockoff Task';
    startKnockoffTask( knockoff ): Action {
        console.log( "kncokoff dlm action: " + knockoff );
        return {
            type: KnockoffActions.START_KNOCKOFF_TASK,
            payload: knockoff
        };
    }

    static START_KNOCKOFF_TASK_SUCCESS = '[Knockoff] Start Knockoff Task Success';

    startKnockoffTaskSuccess( referenceNo ): Action {
        return {
            type: KnockoffActions.START_KNOCKOFF_TASK_SUCCESS,
            payload: referenceNo,
        };
    }

    static COMPLETE_KNOCKOFF_TASK = '[Knockoff] Complete Knockoff Task';

    completeKnockoffTask( knockoff ): Action {
        return {
            type: KnockoffActions.COMPLETE_KNOCKOFF_TASK,
            payload: knockoff,
        };
    }

    static COMPLETE_KNOCKOFF_TASK_SUCCESS = '[Knockoff] Complete Knockoff Task Success';

    completeKnockoffTaskSuccess( message ): Action {
        return {
            type: KnockoffActions.COMPLETE_KNOCKOFF_TASK_SUCCESS,
            payload: message,
        };
    }

    static CLAIM_KNOCKOFF_TASK = '[Knockoff] Assign Knockoff Task';

    claimKnockoffTask( knockoff ): Action {
        return {
            type: KnockoffActions.CLAIM_KNOCKOFF_TASK,
            payload: knockoff,
        };
    }

    static CLAIM_KNOCKOFF_TASK_SUCCESS = '[Knockoff] Assign Knockoff Task Success';

    claimKnockoffTaskSuccess( message ): Action {
        return {
            type: KnockoffActions.CLAIM_KNOCKOFF_TASK_SUCCESS,
            payload: message,
        };
    }

    static RELEASE_KNOCKOFF_TASK = '[Knockoff] Release Knockoff Task';

    releaseKnockoffTask( knockoff ): Action {
        return {
            type: KnockoffActions.RELEASE_KNOCKOFF_TASK,
            payload: knockoff,
        };
    }

    static RELEASE_KNOCKOFF_TASK_SUCCESS = '[Knockoff] Release Knockoff Task Success';

    releaseKnockoffTaskSuccess( message ): Action {
        return {
            type: KnockoffActions.RELEASE_KNOCKOFF_TASK_SUCCESS,
            payload: message,
        };
    }

    static FIND_COMPLETED_KNOCKOFFS = '[Knockoff] Find Completed Knockoff';

    findCompletedKnockoffs(): Action {
        return {
            type: KnockoffActions.FIND_COMPLETED_KNOCKOFFS,
        };
    }

    static FIND_COMPLETED_KNOCKOFFS_SUCCESS = '[Knockoff] Find Completed Knockoff Success';

    findCompletedKnockoffsSuccess( knockoffs ): Action {
        console.log( 'findCompletedKnockoffsSuccess' );
        return {
            type: KnockoffActions.FIND_COMPLETED_KNOCKOFFS_SUCCESS,
            payload: knockoffs,
        };
    }

    static FIND_ARCHIVED_KNOCKOFFS = '[Knockoff] Find Archived Knockoff';

    findArchivedknockoffs(): Action {
        return {
            type: KnockoffActions.FIND_ARCHIVED_KNOCKOFFS,
        };
    }

    static FIND_ARCHIVED_KNOCKOFFS_SUCCESS = '[Knockoff] Find Archived Knockoff Success';

    findArchivedknockoffsSuccess( knockoffs ): Action {
        console.log( 'findArchivedInvoicesSuccess' );
        return {
            type: KnockoffActions.FIND_ARCHIVED_KNOCKOFFS_SUCCESS,
            payload: knockoffs,
        };
    }

    static FIND_ASSIGNED_KNOCKOFF_TASKS = '[Knockoff] Find Assigned Knockoff Tasks';

    findAssignedKnockoffTasks(): Action {
        console.log( 'find Assigned Knockoff Tasks before success' );
        return {
            type: KnockoffActions.FIND_ASSIGNED_KNOCKOFF_TASKS,
        };
    }

    static FIND_ASSIGNED_KNOCKOFF_TASKS_SUCCESS = '[Knockoff] Find Assigned Knockoff Tasks Success';

    findAssignedKnockoffTasksSuccess( tasks ): Action {
        console.log( 'findAssignedKnockoffTasksSuccess' );
        console.log( 'action knockoff' + tasks );
        return {
            type: KnockoffActions.FIND_ASSIGNED_KNOCKOFF_TASKS_SUCCESS,
            payload: tasks,
        };
    }

    static FIND_POOLED_KNOCKOFF_TASKS = '[Knockoff] Find Pooled Knockoff Tasks';

    findPooledKnockoffTasks(): Action {
        return {
            type: KnockoffActions.FIND_POOLED_KNOCKOFF_TASKS,
        };
    }

    static FIND_POOLED_KNOCKOFF_TASKS_SUCCESS = '[Knockoff] Find Pooled Knockoff Tasks Success';

    findPooledKnockoffTasksSuccess( knockoffs ): Action {
        console.log( 'findPooledKnockoffTasksSuccess' );
        return {
            type: KnockoffActions.FIND_POOLED_KNOCKOFF_TASKS_SUCCESS,
            payload: knockoffs,
        };
    }

    static FIND_INVOICE_BY_KNOCKOFF = '[Knockoff] Find Invoice By Knockoff';

    findKnockoffsByInvoice( knockoff ): Action {
        return {
            type: KnockoffActions.FIND_INVOICE_BY_KNOCKOFF,
            payload: knockoff
        };
    }

    static FIND_INVOICE_BY_KNOCKOFF_SUCCESS = '[Knockoff] Find Invoice By Knockoff Success';

    findKnockoffsByInvoiceSuccess( knockoff ): Action {
        console.log( "findKnockoffsByInvoiceSuccess" );
        return {
            type: KnockoffActions.FIND_INVOICE_BY_KNOCKOFF_SUCCESS,
            payload: knockoff
        };
    }

    static ADD_KNOCKOFF_INVOICE = '[Knockoff] Save Knockoff Invoice';

    addKnockoffInvoice( knockoff, invoice ): Action {
        return {
            type: KnockoffActions.ADD_KNOCKOFF_INVOICE,
            payload: { knockoff: knockoff, invoice: invoice },
        };
    }

    static ADD_KNOCKOFF_INVOICE_SUCCESS = '[Knockoff] Save Knockoff Invoice Success';

    addKnockoffInvoiceSuccess( message ): Action {
        return {
            type: KnockoffActions.ADD_KNOCKOFF_INVOICE_SUCCESS,
            payload: message
        };
    }

    static FIND_KNOCKOFF_ITEM = '[Knockoff] Find Knockoff Item';

    findKnockoffItems( knockoff ): Action {
        return {
            type: KnockoffActions.FIND_KNOCKOFF_ITEM,
            payload: knockoff
        };
    }

    static FIND_KNOCKOFF_ITEM_SUCCESS = '[Knockoff] Find Knockoff Item Success';

    findKnockoffItemsSuccess( knockoff ): Action {
        return {
            type: KnockoffActions.FIND_KNOCKOFF_ITEM_SUCCESS,
            payload: knockoff
        };
    }

    static FIND_KNOCKOFF_ITEM_BY_INVOICE = '[Knockoff] Find Knockoff Item By Invoice';

    findKnockoffItemsByInvoice( knockoff, invoice ): Action {
        return {
            type: KnockoffActions.FIND_KNOCKOFF_ITEM_BY_INVOICE,
            payload: { knockoff, invoice }
        };
    }

    static FIND_KNOCKOFF_ITEM_BY_INVOICE_SUCCESS = '[Knockoff] Find Knockoff Item By Invoice Success';

    findKnockoffItemsByInvoiceSuccess( knockoff ): Action {
        return {
            type: KnockoffActions.FIND_KNOCKOFF_ITEM_BY_INVOICE_SUCCESS,
            payload: knockoff
        };
    }

    static INVOICE_ITEM_TO_KNOCKOFF_ITEM = '[Knockoff] Invoice Item to Knockoff Item';

    itemToKnockoffItem( invoice, knockoff ): Action {
        return {
            type: KnockoffActions.INVOICE_ITEM_TO_KNOCKOFF_ITEM,
            payload: { invoice, knockoff }
        };
    }

    static INVOICE_ITEM_TO_KNOCKOFF_ITEM_SUCCESS = '[Knockoff] Invoice Item to Knockoff Item Success';

    itemToKnockoffItemSuccess( knockoff ): Action {
        return {
            type: KnockoffActions.INVOICE_ITEM_TO_KNOCKOFF_ITEM_SUCCESS,
            payload: knockoff
        };
    }

    static FIND_ACCOUNT_CHARGE_BY_KNOCKOFF = '[Knockoff] Find Account Charge By Knockoff';

    findKnockoffsByAccountCharge( accountCharge ): Action {
        return {
            type: KnockoffActions.FIND_ACCOUNT_CHARGE_BY_KNOCKOFF,
            payload: accountCharge
        };
    }

    static FIND_ACCOUNT_CHARGE_BY_KNOCKOFF_SUCCESS = '[Knockoff] Find Account Charge By Knockoff Success';

    findKnockoffsByAccountChargeSuccess( accountCharge ): Action {
        console.log( "findKnockoffsByAccountChargeSuccess" );
        return {
            type: KnockoffActions.FIND_ACCOUNT_CHARGE_BY_KNOCKOFF_SUCCESS,
            payload: accountCharge
        };
    }

     static FIND_DEBIT_NOTE_BY_KNOCKOFF = '[Knockoff] Find Debit Note By Knockoff';

    findKnockoffsByDebitNote( debitNote ): Action {
        return {
            type: KnockoffActions.FIND_DEBIT_NOTE_BY_KNOCKOFF,
            payload: debitNote
        };
    }

    static FIND_DEBIT_NOTE_BY_KNOCKOFF_SUCCESS = '[Knockoff] Find Debit Note By Knockoff Success';

    findKnockoffsByDebitNoteSuccess( debitNote ): Action {
        console.log( "findKnockoffsByDebitNoteSuccess" );
        return {
            type: KnockoffActions.FIND_DEBIT_NOTE_BY_KNOCKOFF_SUCCESS,
            payload: debitNote
        };
    }

    static ADD_KNOCKOFF_ACCOUNT_CHARGE = '[Knockoff] Save Knockoff Account Charge';

    addKnockoffAccountCharge( knockoff, accountCharge ): Action {
        return {
            type: KnockoffActions.ADD_KNOCKOFF_ACCOUNT_CHARGE,
            payload: { knockoff: knockoff, invoice: accountCharge },
        };
    }

    static ADD_KNOCKOFF_ACCOUNT_CHARGE_SUCCESS = '[Knockoff] Save Knockoff Account Charge Success';

    addKnockoffAccountChargeSuccess( message ): Action {
        return {
            type: KnockoffActions.ADD_KNOCKOFF_ACCOUNT_CHARGE_SUCCESS,
            payload: message
        };
    }
    
    static ADD_KNOCKOFF_DEBIT_NOTE = '[Knockoff] Save Knockoff Debit Note';

    addKnockoffDebitNote(knockoff, debitNote): Action {
      return {
        type: KnockoffActions.ADD_KNOCKOFF_DEBIT_NOTE,
        payload: {knockoff: knockoff, debitNote: debitNote},
      };
    }

    static ADD_KNOCKOFF_DEBIT_NOTE_SUCCESS = '[Knockoff] Save Knockoff Debit Note Success';

    addKnockoffDebitNoteSuccess(message): Action {
      return {
        type: KnockoffActions.ADD_KNOCKOFF_DEBIT_NOTE_SUCCESS,
        payload: message
      };
    }
    
    static UPDATE_KNOCKOFF = '[Knockoff] Update Knockoff';

    updateKnockoff(knockoff): Action {
      return {
        type: KnockoffActions.UPDATE_KNOCKOFF,
        payload: knockoff
      };
    }

    static UPDATE_KNOCKOFF_SUCCESS = '[Knockoff] Update Knockoff Success';

    updateKnockoffSuccess(message): Action {
      return {
        type: KnockoffActions.UPDATE_KNOCKOFF_SUCCESS,
        payload: message
      };
    }

}