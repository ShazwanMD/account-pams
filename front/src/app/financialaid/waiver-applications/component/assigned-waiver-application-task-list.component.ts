import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy, ViewContainerRef} from '@angular/core';
import {MdSnackBar} from "@angular/material";
import {WaiverApplicationTask} from "../waiver-application-task.interface";

@Component({
  selector: 'pams-assigned-waiver-application-task-list',
  templateUrl: './assigned-waiver-application-task-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AssignedWaiverApplicationTaskListComponent {

  @Input() waiverApplicationTasks: WaiverApplicationTask[];
  @Output() view = new EventEmitter<WaiverApplicationTask>();

  private columns: any[] = [
    {name: 'referenceNo', label: 'ReferenceNo'},
    {name: 'account.code', label: 'Account'},
    {name: 'description', label: 'Description'},
    {name: 'flowState', label: 'Status'},
    {name: 'action', label: ''}
  ];

  constructor(private snackBar: MdSnackBar) {
  }

  viewTask(task: WaiverApplicationTask): void {
    console.log("Emitting task");
    let snackBarRef = this.snackBar.open("Viewing waiverApplication", "OK");
    snackBarRef.afterDismissed().subscribe(() => {
      this.view.emit(task);
    });
  }
}
