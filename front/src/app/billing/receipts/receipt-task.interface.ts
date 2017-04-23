import {Receipt} from "./receipt.interface";
import {FlowState} from "../../core/flow-state.enum";
import {Document} from "../../core/document.interface";
export interface ReceiptTask extends Document{
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
