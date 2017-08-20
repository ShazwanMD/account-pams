import {Injectable} from '@angular/core';
import {Action} from '@ngrx/store';

@Injectable()
export class KnockoffActions {
    
    static FIND_KNOCKOFFS = '[Knockoff] Find Knockoffs';

    findKnockoffs(): Action {
      return {
        type: KnockoffActions.FIND_KNOCKOFFS
      };
    }

    static FIND_KNOCKOFFS_SUCCESS = '[Knockoff] Find Knockoffs Success';

    findKnockoffsSuccess(knockoff): Action {
      return {
        type: KnockoffActions.FIND_KNOCKOFFS_SUCCESS,
        payload: knockoff
      };
    }
      
    static FIND_KNOCKOFF_BY_REFERENCE_NO = '[Knockoff] Find Knockoff By Reference No';

    findKnockoffByReferenceNo(referenceNo): Action {
      return {
        type: KnockoffActions.FIND_KNOCKOFF_BY_REFERENCE_NO,
        payload: referenceNo
      };
    }

    static FIND_KNOCKOFF_BY_REFERENCE_NO_SUCCESS = '[Knockoff] Find Knockoff By Reference No Success';

    findKnockoffByReferenceNoSuccess(knockoff): Action {
      return {
        type: KnockoffActions.FIND_KNOCKOFF_BY_REFERENCE_NO_SUCCESS,
        payload: knockoff
      };
    }
    
    static SAVE_KNOCKOFF = '[Knockoff] Save Knockoff';

    saveKnockoff(knockoff): Action {
      return {
        type: KnockoffActions.SAVE_KNOCKOFF,
        payload: knockoff
      };
    }

    static SAVE_KNOCKOFF_SUCCESS = '[Knockoff] Save Knockoff Success';

    saveKnockoffSuccess(knockoff): Action {
      return {
        type: KnockoffActions.SAVE_KNOCKOFF_SUCCESS,
        payload: knockoff
      };
    }
}