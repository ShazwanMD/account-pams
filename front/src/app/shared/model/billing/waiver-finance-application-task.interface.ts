import {FlowState} from '../../../core/flow-state.enum';
import {Document} from '../../../core/document.interface';
import { WaiverFinanceApplication } from "./waiver-finance-application.interface";
import { WaiverApplicationType } from "../financialaid/waiver-application-type.enum";
import { GraduateCenterType } from '../financialaid/graduate-center-type.enum';
export interface WaiverFinanceApplicationTask extends Document {
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
  application: WaiverFinanceApplication;
  flowState: FlowState;
  waiverType: WaiverApplicationType;
  graduateCenterType: GraduateCenterType;
}
