import {FlowState} from '../../../core/flow-state.enum';
import {Document} from '../../../core/document.interface';
import {Knockoff} from './knockoff.interface';
import { AdvancePayment } from "./advance-payment.interface";
import { Invoice } from "./invoice.interface";

export interface KnockoffTask extends Document {
  taskId: string;
  taskName: string;
  candidate: string;
  assignee: string
  referenceNo: string;
  sourceNo: string;
  description: string;
  knockoff: Knockoff;
  receivedDate: number;
  totalAmount: number;
  payments?: AdvancePayment;
  flowState: FlowState;

}