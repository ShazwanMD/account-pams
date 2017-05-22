import {FlowState} from "../../core/flow-state.enum";
import { Document } from "../../core/document.interface";
import { CreditNote } from "./credit-note.interface";
export interface CreditNoteTask extends Document {
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
  creditNote: CreditNote;
  flowState: FlowState;
}
