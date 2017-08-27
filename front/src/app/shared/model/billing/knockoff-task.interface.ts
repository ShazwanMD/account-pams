import {FlowState} from '../../../core/flow-state.enum';
import {Document} from '../../../core/document.interface';
import {Knockoff} from './knockoff.interface';
import { AdvancePayment } from "./advance-payment.interface";

export interface KnockoffTask extends Document {
    taskId: string;
    referenceNo: string;
    sourceNo: string;
    description: string;
    taskName: string;
    amount: number;
    //paymentNo: AdvancePayment;
    assignee: string;
    issuedDate: number;
    candidate: string;
    knockoff: Knockoff;
    flowState: FlowState;

}