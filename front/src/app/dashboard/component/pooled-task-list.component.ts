import {ChangeDetectionStrategy, Component, EventEmitter, Input, Output} from '@angular/core';
import {Task} from '../../core/task.interface';

@Component({
  selector: 'pams-pooled-task-list',
  templateUrl: './pooled-task-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class PooledTaskListComponent {

  @Input() tasks: Task[];
  @Output() view = new EventEmitter<Task>();
  @Output() claim = new EventEmitter<Task>();

  constructor() {
  }

  viewPooledTask(task: Task): void {
    this.view.emit(task);
  }

  claimTask(task: Task) {
    this.claim.emit(task);
  }
}
