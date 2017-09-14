import {WaiverApplication} from './waiver-application.interface';
import {FlowState} from '../../../core/flow-state.enum';
import {Document} from '../../../core/document.interface';
import { WaiverApplicationType } from "./waiver-application-type.enum";

export interface WaiverApplicationTask extends Document {
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
  waiverType: WaiverApplicationType;
}
