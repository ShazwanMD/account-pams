import { WaiverFinanceApplication } from './waiver-finance-application.interface';
import {DebitNote} from './debit-note.interface';
import { Document } from '../../../core/document.interface';

export interface WaiverDebitNote extends Document {
    id: number;
    debitNote: DebitNote;
    waiverApplication: WaiverFinanceApplication;

    // selection
    selected?: boolean;
}
