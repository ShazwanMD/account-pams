import {Component, Input, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Store} from '@ngrx/store';
import {FormControl} from '@angular/forms';
import { AccountCharge } from "../../../../shared/model/account/account-charge.interface";
import { AccountModuleState } from "../../../account/index";
import { AccountActions } from "../../../account/accounts/account.action";
import { ActivatedRoute } from "@angular/router";

@Component({
  selector: 'pams-account-charge-select',
  templateUrl: './account-charge-select.component.html',
  styleUrls: ['./account-charge-select.scss'],
})
export class AccountChargeSelectComponent implements OnInit {

  private ACCOUNT_CHARGES: string[] = 'accountModuleState.accountCharges'.split('.');
  private accountCharges$: Observable<AccountCharge[]>

  @Input() placeholder: string;
  @Input() innerFormControl: FormControl;

  constructor(private store: Store<AccountModuleState>,
    private route: ActivatedRoute,
    private actions: AccountActions) {
    this.accountCharges$ = this.store.select(...this.ACCOUNT_CHARGES);
}
  ngOnInit(): void {
    this.route.params.subscribe((params: { account: string }) => {
      let account: string = params.account;
      this.store.dispatch(this.actions.findAccountCharges(account));
    });
  }
  selectChangeEvent(event: AccountCharge) {
    this.innerFormControl.setValue(event, {emitEvent: false});
  }
}

