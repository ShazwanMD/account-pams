import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy} from '@angular/core';
import {ReceiptTask} from "../receipt-task.interface";

@Component({
  selector: 'pams-receipt-task-list',
  templateUrl: './receipt-task-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ReceiptTaskListComponent {

  @Input() receiptTasks: ReceiptTask[];
  @Output() view = new EventEmitter<ReceiptTask>();

  private columns: any[] = [
    {name: 'referenceNo', label: 'ReferenceNo'},
    {name: 'description', label: 'Description'},
    {name: 'flowState', label: 'Status'},
    {name: 'action', label: ''}
  ];
}
