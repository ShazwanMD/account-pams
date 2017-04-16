import {Invoice} from "./invoice.interface";
import {FlowState} from "../../core/flow-state.enum";
export interface InvoiceTask {
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
  invoice: Invoice;
  flowState:FlowState;
}
