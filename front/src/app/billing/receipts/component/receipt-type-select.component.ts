import {ReceiptType} from '../../../shared/model/billing/receipt-type.enum';
import {Component, Input, OnInit} from '@angular/core';
import {FormControl} from '@angular/forms';

@Component({
 selector: 'pams-receipt-type-select',
 templateUrl: './receipt-type-select.component.html',
})
export class ReceiptTypeSelectComponent {

 private receiptTypes: ReceiptType[] = <ReceiptType[]>[];

 @Input() placeholder: string;
 @Input() innerFormControl: FormControl;

 constructor() {
   for (let n in ReceiptType) {
     if (typeof ReceiptType[n] === 'string')
       this.receiptTypes.push(ReceiptType[n.toString()]);
   }
 }

 selectChangeEvent(event: ReceiptType) {
   this.innerFormControl.setValue(event, {emitEvent: false});
 }
}
