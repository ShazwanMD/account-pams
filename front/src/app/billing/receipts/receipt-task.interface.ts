import {Receipt} from "./receipt.interface";
import {FlowState} from "../../core/flow-state.enum";
export interface ReceiptTask {
  taskId:string;
  taskName:string;
  candidate:string;
  assignee:string;
  referenceNo: string;
  sourceNo: string;
  accountCode: string;
  description: string;
  totalAmount: number;
  balanceAmount: number;
  issuedDate: number;
  receipt: Receipt;
  flowState:FlowState;
}
