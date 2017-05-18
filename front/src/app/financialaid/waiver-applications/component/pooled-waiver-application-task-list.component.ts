import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy, ViewContainerRef} from '@angular/core';
import {MdDialogRef, MdDialog, MdDialogConfig, MdSnackBar} from "@angular/material";
import {WaiverApplicationTask} from "../waiver-application-task.interface";

@Component({
  selector: 'pams-pooled-waiver-application-task-list',
  templateUrl: './pooled-waiver-application-task-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class PooledWaiverApplicationTaskListComponent {

  @Input() waiverApplicationTasks: WaiverApplicationTask[];
  @Output() claim = new EventEmitter<WaiverApplicationTask>();

  private columns: any[] = [
    {name: 'referenceNo', label: 'ReferenceNo'},
    {name: 'accountCode', label: 'Account'},
    {name: 'description', label: 'Description'},
    {name: 'totalAmount', label: 'Total Amount'},
    {name: 'balanceAmount', label: 'Balance Amount'},
    {name: 'flowState', label: 'Status'},
    {name: 'action', label: ''}
  ];

  constructor(private snackBar: MdSnackBar) {
  }

  claimTask(task: WaiverApplicationTask): void {
    console.log("Emitting task");
    let snackBarRef = this.snackBar.open("Claiming waiverApplication", "OK");
    snackBarRef.afterDismissed().subscribe(() => {
      this.claim.emit(task);
    });
  }
}