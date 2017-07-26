import {Receipt} from './receipt.interface';
import {ReceiptType} from './receipt-type.enum';
import {FlowState} from '../../../core/flow-state.enum';
import {Document} from '../../../core/document.interface';
import {PaymentMethod} from '../common/payment-method.enum';

export interface ReceiptTask extends Document {
  taskId: string;
  taskName: string;
  candidate: string;
  assignee: string;
  referenceNo: string;
  sourceNo: string;
  description: string;
  receipt: Receipt;
  flowState: FlowState;
  receivedDate: number;
  receiptType: ReceiptType;
  paymentMethod: PaymentMethod;
}
