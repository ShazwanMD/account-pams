import {ChangeDetectionStrategy, Component, EventEmitter, Input, Output} from '@angular/core';
import {InvoiceTask} from '../../../../shared/model/billing/invoice-task.interface';
import {MdSnackBar, MdSnackBarConfig} from '@angular/material';

@Component({
  selector: 'pams-assigned-invoice-task-list',
  templateUrl: './assigned-invoice-task-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AssignedInvoiceTaskListComponent {

  private columns: any[] = [
    {name: 'issuedDate', label: 'Date'},
    {name: 'referenceNo', label: 'ReferenceNo'},
    {name: 'accountCode', label: 'Account'},
    {name: 'description', label: 'Description'},
    {name: 'totalAmount', label: 'Total Amount'},
    {name: 'flowState', label: 'Status'},
    {name: 'assignee', label: 'Creator'},
    {name: 'action', label: ''},
  ];

  @Input() invoiceTasks: InvoiceTask[];
  @Output() view = new EventEmitter<InvoiceTask>();

  constructor(private snackBar: MdSnackBar) {
  }

  viewTask(task: InvoiceTask): void {
    console.log('Emitting task');
    let config: MdSnackBarConfig = new MdSnackBarConfig();
    config.duration = 2000;
    let snackBarRef = this.snackBar.open('Viewing invoice', 'OK', config);
    snackBarRef.afterDismissed().subscribe(() => {
      this.view.emit(task);
    });
  }
}
