import {FlowState} from './flow-state.enum';
import {Document} from './document.interface';
export interface Task extends Document {
  taskId: string;
  taskName: string;
  assignee: string;
  flowState: FlowState;
}
