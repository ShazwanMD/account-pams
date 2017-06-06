import {FlowState} from "../../core/flow-state.enum";
import { Document } from "../../core/document.interface";
import { DebitNote } from "./debit-note.interface";
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
  flowState: FlowState;
}
