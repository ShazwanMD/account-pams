import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy} from '@angular/core';
import { Task } from "../../core/task.interface";

@Component({
  selector: 'pams-assigned-task-list',
  templateUrl: './assigned-task-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AssignedTaskListComponent {

  @Input() tasks: Task[];
  @Output() view = new EventEmitter<Task>();

  constructor() {
  }

  viewAssignedTask(task: Task): void {
    this.view.emit(task);
  }
}
