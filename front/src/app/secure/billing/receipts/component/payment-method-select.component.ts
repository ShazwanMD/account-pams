import {PaymentMethod} from '../../../../shared/model/common/payment-method.enum';
import {Component, Input} from '@angular/core';
import {FormControl} from '@angular/forms';

@Component({
  selector: 'pams-payment-method-select',
  templateUrl: './payment-method-select.component.html',
  styleUrls: ['./payment-method-select.component.scss'],
})
export class PaymentMethodSelectComponent {

  private paymentMethods: PaymentMethod[] = <PaymentMethod[]>[];

  @Input() placeholder: string;
  @Input() innerFormControl: FormControl;

  constructor() {
    for (let n in PaymentMethod) {
      if (typeof PaymentMethod[n] === 'string')
        this.paymentMethods.push(PaymentMethod[n.toString()]);
    }
  }

  selectChangeEvent(event: PaymentMethod) {
    this.innerFormControl.setValue(event, {emitEvent: false});
  }
}
