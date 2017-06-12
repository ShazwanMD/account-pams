import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy} from '@angular/core';
import { Task } from "../../core/task.interface";

@Component({
  selector: 'pams-pooled-task-list',
  templateUrl: './pooled-task-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class PooledTaskListComponent {

  @Input() tasks: Task[];
  @Output() view = new EventEmitter<Task>();

  constructor() {
  }

  viewPooledTask(task: Task): void {
    this.view.emit(task);
  }
}
