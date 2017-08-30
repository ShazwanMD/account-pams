import {FlowState} from '../../../core/flow-state.enum';
import {Document} from '../../../core/document.interface';
import {RefundPayment} from './refund-payment.interface';
import { AdvancePayment } from "./advance-payment.interface";

export interface RefundPaymentTask extends Document {
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
    refundPayment: RefundPayment;
    flowState: FlowState;
}