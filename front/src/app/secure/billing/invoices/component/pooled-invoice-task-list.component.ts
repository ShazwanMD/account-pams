import {ChangeDetectionStrategy, Component, EventEmitter, Input, Output} from '@angular/core';
import {InvoiceTask} from '../../../../shared/model/billing/invoice-task.interface';
import {MdSnackBar, MdSnackBarRef, SimpleSnackBar} from '@angular/material';

@Component({
  selector: 'pams-pooled-invoice-task-list',
  templateUrl: './pooled-invoice-task-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class PooledInvoiceTaskListComponent {

  private columns: any[] = [
    {name: 'issuedDate', label: 'Date'},
    {name: 'referenceNo', label: 'ReferenceNo'},
    {name: 'accountCode', label: 'Account'},
    {name: 'description', label: 'Description'},
    {name: 'totalAmount', label: 'Total Amount'},
    {name: 'flowState', label: 'Status'},
    {name: 'creatorUsername', label: 'Creator'},
    {name: 'action', label: ''},
  ];

  @Input() invoiceTasks: InvoiceTask[];
  @Output() claim: EventEmitter<InvoiceTask> = new EventEmitter<InvoiceTask>();

  constructor(private snackBar: MdSnackBar) {
  }

  claimTask(task: InvoiceTask): void {
    console.log('Emitting task');
    let snackBarRef: MdSnackBarRef<SimpleSnackBar> = this.snackBar.open('Claiming invoice');
    snackBarRef.dismiss();
    snackBarRef.afterDismissed().subscribe(() => {
      this.claim.emit(task);
    });
  }
}
