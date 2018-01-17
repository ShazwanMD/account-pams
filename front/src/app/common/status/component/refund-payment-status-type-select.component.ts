import {Component, Input, OnInit} from '@angular/core';
import {FormControl} from '@angular/forms';
import { RefundPaymentStatusType } from '../../../shared/model/common/refund-status-type';

@Component({
 selector: 'pams-refund-payment-status-type-select',
 templateUrl: './refund-payment-status-type-select.component.html',
 styleUrls: ['./refund-payment-status-type-select.component.scss'],
})
export class RefundPaymentStatusTypeSelectComponent implements OnInit {
 private refundPaymentStatusTypes: RefundPaymentStatusType[] = <RefundPaymentStatusType[]>[];
 @Input() placeholder: string;
 @Input() innerFormControl: FormControl;
 constructor() {
  for (let n in RefundPaymentStatusType) {
    if (typeof RefundPaymentStatusType[n] === 'string')
      this.refundPaymentStatusTypes.push(RefundPaymentStatusType[n.toString()]);
  }
}
 ngOnInit() {
 }
 selectChangeEvent(event: RefundPaymentStatusType) {
   this.innerFormControl.setValue(event, {emitEvent: false});
 }
}