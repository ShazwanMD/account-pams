import {Component, Input, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {Account} from '../../../../shared/model/account/account.interface';
import {AccountActions} from '../account.action';
import {Store} from '@ngrx/store';
import {FormControl} from '@angular/forms';
import {AccountModuleState} from '../../index';

@Component({
  selector: 'pams-account-select',
  templateUrl: './account-select.component.html',
  styleUrls: ['./account-select.scss'],
})
export class AccountSelectComponent implements OnInit {

  private ACCOUNTS: string[] = 'accountModuleState.accounts'.split('.');
  private accounts$: Observable<Account[]>;
  private selected: Account;
  @Input() placeholder: string;
  @Input() innerFormControl: FormControl;
  @Input() preSelected: Account;

  constructor(private store: Store<AccountModuleState>,
              private actions: AccountActions) {
    this.accounts$ = this.store.select(...this.ACCOUNTS);
  }

  ngOnInit(): void {
    this.store.dispatch(this.actions.findAccounts());

    if (this.preSelected) {
      this.accounts$.subscribe((accounts) => {
        this.selected = accounts.find((account) => account.id == this.preSelected.id);
      });
    }
  }

  selectChangeEvent(event: Account) {
    this.innerFormControl.setValue(event, {emitEvent: false});
    this.selected = event;
  }
}

