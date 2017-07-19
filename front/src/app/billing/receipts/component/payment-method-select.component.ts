import {PaymentMethod} from "../../../common/payment-method.enum";
import {Component, Input, OnInit} from '@angular/core';
import {FormControl} from "@angular/forms";

@Component({
 selector: 'pams-payment-method-select',
 templateUrl: './payment-method-select.component.html',
})
export class PaymentMethodSelectComponent {

 private paymentMethods: PaymentMethod[] = <PaymentMethod[]>[];

 @Input() placeholder: string;
 @Input() innerFormControl: FormControl;

 constructor() {
   for (var n in PaymentMethod) {
     if(typeof PaymentMethod[n] === 'string')
       this.paymentMethods.push(PaymentMethod[n.toString()]);
   }
 }

 selectChangeEvent(event: PaymentMethod) {
   this.innerFormControl.setValue(event, {emitEvent: false});
 }
}