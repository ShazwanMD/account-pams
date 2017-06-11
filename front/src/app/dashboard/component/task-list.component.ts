import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy} from '@angular/core';
import { Task } from "../../core/task.interface";

@Component({
  selector: 'pams-task-list',
  templateUrl: './task-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class TaskListComponent {

  @Input() tasks: Task[];
  @Output() view = new EventEmitter<Task>();

  constructor() {
  }

  viewTask(task: Task): void {
    this.view.emit(task);
  }
}
