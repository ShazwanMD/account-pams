import { DebitNote } from './debit-note.interface';
import {Knockoff} from './knockoff.interface';
import {Document} from '../../../core/document.interface';

export interface KnockoffDebitNote extends Document {
    id: number;
    debitNote: DebitNote;
    knockoff: Knockoff;

selected?: boolean;
}