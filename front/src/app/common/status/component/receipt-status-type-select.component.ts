import {Component, Input, OnInit} from '@angular/core';
import {FormControl} from '@angular/forms';
import { ReceiptStatusType } from '../../../shared/model/common/receipt-status-type';

@Component({
 selector: 'pams-receipt-status-type-select',
 templateUrl: './receipt-status-type-select.component.html',
 styleUrls: ['./receipt-status-type-select.component.scss'],
})
export class ReceiptStatusTypeSelectComponent implements OnInit {
 private receiptStatusTypes: ReceiptStatusType[] = <ReceiptStatusType[]>[];
 @Input() placeholder: string;
 @Input() innerFormControl: FormControl;
 constructor() {
  for (let n in ReceiptStatusType) {
    if (typeof ReceiptStatusType[n] === 'string')
      this.receiptStatusTypes.push(ReceiptStatusType[n.toString()]);
  }
}
 ngOnInit() {
 }
 selectChangeEvent(event: ReceiptStatusType) {
   this.innerFormControl.setValue(event, {emitEvent: false});
 }
}