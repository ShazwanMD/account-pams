import {FlowState} from "../../core/flow-state.enum";
import {WaiverApplication} from "./waiver-application.interface";

export interface WaiverApplicationTask extends Task {
  taskId: string;
  taskName: string;
  candidate: string;
  assignee: string;
  referenceNo: string;
  sourceNo: string;
  accountCode: string;
  description: string;
  balance: number;
  gracedAmount: number;
  waivedAmount: number;
  application: WaiverApplication;
  flowState: FlowState;
}
