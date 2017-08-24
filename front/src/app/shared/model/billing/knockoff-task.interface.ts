import {FlowState} from '../../../core/flow-state.enum';
import {Document} from '../../../core/document.interface';
import {Knockoff} from './knockoff.interface';

export interface KnockoffTask extends Document {
    taskId: string;
    referenceNo: string;
    sourceNo: string;
    description: string;
    taskName: string;
    amount: number;
    paymentNo: string;
    assignee: string;
    issuedDate: number;
    candidate: string;
    knockoff: Knockoff;
    flowState: FlowState;

}