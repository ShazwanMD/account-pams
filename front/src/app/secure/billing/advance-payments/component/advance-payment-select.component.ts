import {Component, Input, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Store} from '@ngrx/store';
import {FormControl} from '@angular/forms';
import {BillingModuleState} from '../../index';
import {AdvancePayment} from '../../../../shared/model/billing/advance-payment.interface';
import {Receipt} from '../../../../shared/model/billing/receipt.interface';
import { AdvancePaymentActions } from "../../advance-payments/advance-payment.action";
import {Account} from '../../../../shared/model/account/account.interface';

@Component({
  selector: 'pams-advance-payment-select',
  templateUrl: './advance-payment-select.component.html',
})
export class AdvancePaymentSelectComponent implements OnInit {

  private ADVANCE_PAYMENTS = 'billingModuleState.advancePayments'.split('.');
  private advancePayments$: Observable<AdvancePayment[]>;
  private selected: AdvancePayment;

  @Input() placeholder: string;
  @Input() innerFormControl: FormControl;
  @Input() preSelected: AdvancePayment;
  @Input() account: Account;

  constructor(private store: Store<BillingModuleState>,
              private actions: AdvancePaymentActions) {
    this.advancePayments$ = this.store.select(...this.ADVANCE_PAYMENTS);
  }

  ngOnInit() {
    if (this.preSelected) {
      this.advancePayments$.subscribe((advancePayments) => {
        this.selected = advancePayments.find((advancePayment) => advancePayment.id == this.preSelected.id);
      });
    }
  }

  selectChangeEvent(event: AdvancePayment) {
    this.innerFormControl.setValue(event, {emitEvent: false});
    this.selected = event;
  }
}

