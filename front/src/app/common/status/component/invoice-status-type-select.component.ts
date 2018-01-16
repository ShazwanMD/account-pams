import {Component, Input, OnInit} from '@angular/core';
import {FormControl} from '@angular/forms';
import { InvoiceStatusType } from '../../../shared/model/common/invoice-status-type';

@Component({
 selector: 'pams-invoice-status-type-select',
 templateUrl: './invoice-status-type-select.component.html',
})
export class InvoiceStatusTypeSelectComponent implements OnInit {
 private invoiceStatusTypes: InvoiceStatusType[] = <InvoiceStatusType[]>[];
 @Input() placeholder: string;
 @Input() innerFormControl: FormControl;
 constructor() {
  for (let n in InvoiceStatusType) {
    if (typeof InvoiceStatusType[n] === 'string')
      this.invoiceStatusTypes.push(InvoiceStatusType[n.toString()]);
  }
}
 ngOnInit() {
 }
 selectChangeEvent(event: InvoiceStatusType) {
   this.innerFormControl.setValue(event, {emitEvent: false});
 }
}