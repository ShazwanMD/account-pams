import {Component, OnInit, ChangeDetectionStrategy} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {IdentityService} from '../../../services';
import {CommonService} from '../../../services';
import {Store} from "@ngrx/store";
import {Observable} from "rxjs";
import {Account} from "./account.interface";
import {AccountActions} from "./account.action";
import {AccountState} from "./account.reducer";
import {AccountListState} from "./account-list.reducer";
import {AccountModuleState} from "../index";

@Component({
  selector: 'pams-account-center',
  templateUrl: './account-center.page.html',
})

export class AccountCenterPage implements OnInit {

  private accounts$: Observable<Account[]>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: AccountActions,
              private store: Store<AccountListState>) {
    this.accounts$ = this.store.select(state => state);
  }

  goBack(route: string): void {
    this.router.navigate(['/accounts']);
  }

  viewAccount(account: Account) {
    console.log("account: " + account.id);
    this.router.navigate(['/accounts-detail', account.id]);
  }

  ngOnInit(): void {
    this.store.dispatch(this.actions.findAccounts());
  }
}

