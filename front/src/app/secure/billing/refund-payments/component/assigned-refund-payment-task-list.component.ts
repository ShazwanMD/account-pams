import {ChangeDetectionStrategy, Component, EventEmitter, Input, Output} from '@angular/core';
import {RefundPaymentTask} from '../../../../shared/model/billing/refund-payment-task.interface';
import {MdSnackBar, MdSnackBarConfig} from '@angular/material';

@Component({
  selector: 'pams-assigned-refund-payment-task-list',
  templateUrl: './assigned-refund-payment-task-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AssignedRefundPaymentTaskListComponent {

    private columns: any[] = [
                              {name: 'issuedDate', label: 'Date'},                        
                              {name: 'referenceNo', label: 'Reference No'},
                              {name: 'description', label: 'Description'},
                              {name: 'amount', label: 'Total Amount'},
                              {name: 'refundPayment.creatorUsername', label: 'Creator'},
                              {name: 'refundPayment.createdDate', label: 'Created Date'},
                              {name: 'flowState', label: 'Status'},
                              {name: 'action', label: ''},
                            ];

  @Input() refundPaymentTasks: RefundPaymentTask[];
  @Output() view = new EventEmitter<RefundPaymentTask>();

  constructor(private snackBar: MdSnackBar) {
  }

  viewTask(task: RefundPaymentTask): void {
    console.log('Emitting task');
    let config: MdSnackBarConfig = new MdSnackBarConfig();
    config.duration = 2000;
    let snackBarRef = this.snackBar.open('Viewing refundPayment', 'OK', config);
    snackBarRef.afterDismissed().subscribe(() => {
      this.view.emit(task);
    });
  }
}
