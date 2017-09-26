import {Component, Input, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Store} from '@ngrx/store';
import {FormControl} from '@angular/forms';
import {BillingModuleState} from '../../index';

import {Receipt} from '../../../../shared/model/billing/receipt.interface';

import { AccountCharge } from '../../../../shared/model/account/account-charge.interface';
import { AccountActions } from '../../../account/accounts/account.action';

@Component({
  selector: 'pams-account-charge-select-unpaid',
  templateUrl: './account-charge-select-unpaid.component.html',
})
export class AccountChargeUnpaidSelectComponent implements OnInit {

  private ACCOUNT_CHARGES = 'billingModuleState.accountCharges'.split('.');
  private accountCharges$: Observable<AccountCharge[]>;
  private selected: AccountCharge;

  @Input() placeholder: string;
  @Input() innerFormControl: FormControl;
  @Input() preSelected: AccountCharge;
  @Input() receipt: Receipt;

  constructor(private store: Store<BillingModuleState>,
              private actions: AccountActions) {
    this.accountCharges$ = this.store.select(...this.ACCOUNT_CHARGES);
  }

  ngOnInit() {
    if (this.preSelected) {
      this.accountCharges$.subscribe((accountCharges) => {
        this.selected = accountCharges.find((accountCharge) => accountCharge.id == this.preSelected.id);
      });
    }
  }

  selectChangeEvent(event: AccountCharge) {
    this.innerFormControl.setValue(event, {emitEvent: false});
    this.selected = event;
  }
}

