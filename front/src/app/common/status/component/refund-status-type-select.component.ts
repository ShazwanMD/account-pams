import {Component, Input, OnInit} from '@angular/core';
import {FormControl} from '@angular/forms';
import { RefundPaymentStatusType } from '../../../shared/model/common/refund-status-type';

@Component({
 selector: 'pams-refund-status-type-select',
 templateUrl: './refund-status-type-select.component.html',
})
export class RefundStatusTypeSelectComponent implements OnInit {
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