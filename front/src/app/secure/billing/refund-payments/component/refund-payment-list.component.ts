import {ChangeDetectionStrategy, Component, EventEmitter, Input, Output} from '@angular/core';
import {MdSnackBar} from '@angular/material';
import {RefundPayment} from '../../../../shared/model/billing/refund-payment.interface';

@Component({
  selector: 'pams-refund-payment-list',
  templateUrl: './refund-payment-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class RefundPaymentListComponent {

  private columns: any[] = [
    {name: 'issuedDate', label: 'Date'},                        
    {name: 'referenceNo', label: 'Reference No'},
    {name: 'description', label: 'Description'},
    {name: 'amount', label: 'Total Amount'},
    {name: 'action', label: ''},
  ];

  @Input() refundPayment: RefundPayment[];

}
