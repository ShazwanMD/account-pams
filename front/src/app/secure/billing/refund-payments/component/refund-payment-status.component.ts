import {ChangeDetectionStrategy, Component, Input} from '@angular/core';
import {RefundPayment} from '../../../../shared/model/billing/refund-payment.interface';

@Component({
  selector: 'pams-refund-payment-status',
  templateUrl: './refund-payment-status.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,

})
export class RefundPaymentStatusComponent {
  @Input() refundPayment: RefundPayment;
}
