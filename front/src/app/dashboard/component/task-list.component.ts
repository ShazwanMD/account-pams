import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy, OnChanges, SimpleChanges} from '@angular/core';
import { Task } from "../../core/task.interface";
import { TaskList } from "../task-list.interface";

@Component({
  selector: 'pams-task-list',
  templateUrl: './task-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class TaskListComponent implements OnChanges {

  @Input() tasks: TaskList[];
  @Output() view = new EventEmitter<TaskList>();

  constructor() {
  }

  viewTask(task: TaskList): void {
//    console.log("Emitting task");
//    let snackBarRef = this.snackBar.open("Viewing credit note", "OK");
//    snackBarRef.afterDismissed().subscribe(() => {
//      this.view.emit(task);
//    });
  }
  
  ngOnChanges(changes: SimpleChanges) {
      console.log(changes['tasks'].currentValue);
  }
}
