import {ChangeDetectionStrategy, Component, EventEmitter, Input, Output} from '@angular/core';
import {MdSnackBar} from '@angular/material';
import {ReceiptTask} from '../../../../shared/model/billing/receipt-task.interface';

@Component({
  selector: 'pams-pooled-receipt-task-list',
  templateUrl: './pooled-receipt-task-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class PooledReceiptTaskListComponent {

  @Input() receiptTasks: ReceiptTask[];
  @Output() claim = new EventEmitter<ReceiptTask>();

  private columns: any[] = [
    {name: 'referenceNo', label: 'ReferenceNo'},
    {name: 'description', label: 'Description'},
    {name: 'receipt.creatorUsername', label: 'Creator'},
    {name: 'receipt.createdDate', label: 'Created Date'},
    {name: 'flowState', label: 'Status'},
    {name: 'action', label: ''},
  ];

  constructor(private snackBar: MdSnackBar) {
  }

  claimTask(task: ReceiptTask): void {
    console.log('Emitting task');
    let snackBarRef = this.snackBar.open('Claiming receipt', 'OK');
    snackBarRef.afterDismissed().subscribe(() => {
      this.claim.emit(task);
    });
  }
}
