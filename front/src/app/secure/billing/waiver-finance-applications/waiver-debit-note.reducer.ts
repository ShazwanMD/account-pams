import {Action} from '@ngrx/store';
import { WaiverDebitNote } from "../../../shared/model/billing/waiver-debit-note.interface";
import { WaiverFinanceApplicationActions } from "./waiver-finance-application.action";

export type WaiverDebitNoteState = WaiverDebitNote[];

const initialState: WaiverDebitNoteState = <WaiverDebitNote[]>[];

export function waiverDebitNoteReducer(state = initialState, action: Action): WaiverDebitNoteState {
  switch (action.type) {
    case WaiverFinanceApplicationActions.FIND_WAIVER_DEBIT_NOTE_SUCCESS: {
      return action.payload;
    }
    default: {
      return state;
    }
  }
}
