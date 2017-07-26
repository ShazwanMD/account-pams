import {FlowState} from '../../../core/flow-state.enum';
import {Document} from '../../../core/document.interface';
import {DebitNote} from './debit-note.interface';
import {ChargeCode} from '../account/charge-code.interface';
export interface DebitNoteTask extends Document {
  taskId: string;
  taskName: string;
  candidate: string;
  assignee: string;
  referenceNo: string;
  sourceNo: string;
  accountCode: string;
  description: string;
  totalAmount: number;
  issuedDate: number;
  debitNote: DebitNote;
  accountName: string;
  flowState: FlowState;
  chargeCode: ChargeCode;
}
