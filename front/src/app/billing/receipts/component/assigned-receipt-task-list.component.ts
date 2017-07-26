import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy, ViewContainerRef} from '@angular/core';
import {ReceiptTask} from '../../../shared/model/billing/receipt-task.interface';
import {MdSnackBar} from '@angular/material';

@Component({
  selector: 'pams-assigned-receipt-task-list',
  templateUrl: './assigned-receipt-task-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AssignedReceiptTaskListComponent {

  private columns: any[] = [
    {name: 'receivedDate', label: 'Date'},
    {name: 'referenceNo', label: 'Reference No'},
    {name: 'description', label: 'Description'},
    {name: 'receiptType', label: 'Receipt Type'},
    {name: 'paymentMethod', label: 'Payment Method'},
    {name: 'sourceNo', label: 'Source No'},
    {name: 'flowState', label: 'Status'},
    {name: 'action', label: ''},
  ];

  @Input() receiptTasks: ReceiptTask[];
  @Output() view = new EventEmitter<ReceiptTask>();

  constructor(private snackBar: MdSnackBar) {
  }

  viewTask(task: ReceiptTask): void {
    console.log('Emitting task');
    let snackBarRef = this.snackBar.open('Viewing receipt', 'OK');
    snackBarRef.afterDismissed().subscribe(() => {
      this.view.emit(task);
    });
  }
}
