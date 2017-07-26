import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy, ViewContainerRef} from '@angular/core';
import {InvoiceTask} from '../../../shared/model/billing/invoice-task.interface';

@Component({
  selector: 'pams-invoice-task-list',
  templateUrl: './invoice-task-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})

// DEPRECATED
export class InvoiceTaskListComponent {

  private columns: any[] = [
    {name: 'referenceNo', label: 'ReferenceNo'},
    {name: 'accountCode', label: 'Account'},
    {name: 'description', label: 'Description'},
    {name: 'totalAmount', label: 'Total Amount'},
    {name: 'balanceAmount', label: 'Balance Amount'},
    {name: 'flowState', label: 'Status'},
    {name: 'action', label: ''}
  ];

  @Input() invoiceTasks: InvoiceTask[];
  @Output() view = new EventEmitter<InvoiceTask>();

}
