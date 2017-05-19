import {Component, Input, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {Account} from "../account.interface";
import {AccountActions} from "../account.action";
import {Store} from "@ngrx/store";
import {FormControl} from "@angular/forms";
import {AccountModuleState} from "../../index";


@Component({
  selector: 'pams-account-select',
  templateUrl: './account-select.component.html',
})
export class AccountSelectComponent implements OnInit {

  private ACCOUNTS: string[] = "accountModuleState.accounts".split(".");
  @Input() placeholder: string;
  @Input() innerFormControl: FormControl;
  private accounts$: Observable<Account[]>;

  constructor(private store: Store<AccountModuleState>,
              private actions: AccountActions) {
    this.accounts$ = this.store.select(...this.ACCOUNTS);
  }

  ngOnInit() {
    this.store.dispatch(this.actions.findAccounts());
  }

  selectChangeEvent(event: Account) {
    this.innerFormControl.setValue(event, {emitEvent: false});
  }
}

