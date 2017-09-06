import {ChangeDetectionStrategy, Component, EventEmitter, Input, Output} from '@angular/core';
import {RefundPaymentTask} from '../../../../shared/model/billing/refund-payment-task.interface';
import {MdSnackBar, MdSnackBarConfig, MdSnackBarRef, SimpleSnackBar} from '@angular/material';

@Component({
  selector: 'pams-pooled-refund-payment-task-list',
  templateUrl: './pooled-refund-payment-task-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class PooledRefundPaymentTaskListComponent {

    private columns: any[] = [
                              {name: 'issuedDate', label: 'Date'},                        
                              {name: 'referenceNo', label: 'Reference No'},
                              {name: 'description', label: 'Description'},
                              {name: 'amount', label: 'Total Amount'},
                              {name: 'refundPayment.creatorUsername', label: 'Creator'},
                              {name: 'refundPayment.createdDate', label: 'Created Date'},
                              {name: 'action', label: ''},
                            ];

  @Input() refundPaymentTask: RefundPaymentTask[];
  @Output() claim: EventEmitter<RefundPaymentTask> = new EventEmitter<RefundPaymentTask>();

  constructor(private snackBar: MdSnackBar) {
  }

  claimTask(task: RefundPaymentTask): void {
      console.log('Emitting task');
      let snackBarRef: MdSnackBarRef<SimpleSnackBar> = this.snackBar.open('Claiming invoice');
      snackBarRef.dismiss();
      snackBarRef.afterDismissed().subscribe(() => {
        this.claim.emit(task);
      });
    }
}
