import {Component, Input, EventEmitter, Output, ChangeDetectionStrategy, forwardRef, Provider} from '@angular/core';
import {Account} from "../account.interface";
import {Observable} from "rxjs";
import {FormControl, ControlValueAccessor, NG_VALUE_ACCESSOR} from "@angular/forms";
import {AccountService} from "../../../../services/account.service";

@Component({
  selector: 'pams-account-select',
  templateUrl: './account-select.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AccountSelectComponent {

  accounts: Account[] = <Account[]>[];
  constructor(private accountService: AccountService) {
  }

  ngOnInit() {
    this.accountService.findAccounts().subscribe(accounts => this.accounts = accounts);
  }
}
