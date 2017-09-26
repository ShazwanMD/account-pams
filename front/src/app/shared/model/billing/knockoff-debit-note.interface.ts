import {DebitNote} from './debit-note.interface';
import {Knockoff} from './knockoff.interface';
import {Document} from '../../../core/document.interface';

export interface KnockoffDebitNote extends Document {
    debitNote: DebitNote;
    knockoff: Knockoff;
}