import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy} from '@angular/core';
import {InvoiceTask} from "../invoice-task.interface";

@Component({
  selector: 'pams-invoice-task-list',
  templateUrl: './invoice-task-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class InvoiceTaskListComponent {

  @Input() invoiceTasks: InvoiceTask[];
  @Output() view = new EventEmitter<InvoiceTask>();

  private columns: any[] = [
    {name: 'referenceNo', label: 'ReferenceNo'},
    {name: 'description', label: 'Description'},
    {name: 'action', label: ''}
  ];
}
