import {ChangeDetectionStrategy, Component, EventEmitter, Input, Output} from '@angular/core';
import {MdSnackBar} from '@angular/material';
import {AdvancePayment} from '../../../../shared/model/billing/advance-payment.interface';

@Component({
  selector: 'pams-advance-payment-list',
  templateUrl: './advance-payment-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AdvancePaymentListComponent {

  private columns: any[] = [
    {name: 'referenceNo', label: 'ReferenceNo'},
    {name: 'account.code', label: 'Account'},
    {name: 'description', label: 'Description'},
    {name: 'amount', label: 'Total Amount'},
    {name: 'balanceAmount', label: 'Balance Amount'},
    {name: 'receipt.referenceNo', label: 'Receipt'},
    {name: 'action', label: ''},
  ];

  @Input() advancePayments: AdvancePayment[];
  @Output() knockoffCreateDialog: EventEmitter<AdvancePayment> = new EventEmitter<AdvancePayment>();
  @Output() refundPaymentCreateDialog: EventEmitter<AdvancePayment> = new EventEmitter<AdvancePayment>();

}
