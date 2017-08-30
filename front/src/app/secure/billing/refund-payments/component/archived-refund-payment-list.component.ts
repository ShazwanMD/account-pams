import {ChangeDetectionStrategy, Component, EventEmitter, Input, Output} from '@angular/core';
import {MdSnackBar} from '@angular/material';
import {RefundPayment} from '../../../../shared/model/billing/refund-payment.interface';

@Component({
  selector: 'pams-archived-refund-payment-list',
  templateUrl: './archived-refund-payment-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ArchivedRefundPaymentListComponent {

    private columns: any[] = [
                              {name: 'issuedDate', label: 'Date'},                        
                              {name: 'referenceNo', label: 'Reference No'},
                              {name: 'invoice.referenceNo', label: 'Invoice'},
                              {name: 'description', label: 'Description'},
                              {name: 'amount', label: 'Total Amount'},
                              {name: 'action', label: ''},
                            ];

  @Input() refundPayment: RefundPayment[];
  @Output() view = new EventEmitter<RefundPayment>();

  constructor(private snackBar: MdSnackBar) {
  }

  viewRefundPayment(invoice: RefundPayment): void {
    console.log('Emitting task');
    let snackBarRef = this.snackBar.open('Viewing invoice', 'OK');
    snackBarRef.afterDismissed().subscribe(() => {
      this.view.emit(invoice);
    });
  }
}