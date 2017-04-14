import {Component, OnInit, ChangeDetectionStrategy} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {IdentityService} from '../../../services';
import {CommonService} from '../../../services';
import {Store} from "@ngrx/store";
import {Observable} from "rxjs";
import {Account} from "./account.interface";
import {AccountActions} from "./account.action";
import {AccountState} from "./account.reducer";

@Component({
  selector: 'pams-account-center',
  templateUrl: './account-center.page.html',
})

export class AccountCenterPage implements OnInit {

  private _identityService: IdentityService;
  private _commonService: CommonService;
  private _router: Router;
  private _route: ActivatedRoute;
  private _actions: AccountActions;
  private store: Store<AccountState>;
  private accounts$: Observable<Account[]>;

  constructor(router: Router,
              route: ActivatedRoute,
              actions: AccountActions,
              store: Store<AccountState>,
              identityService: IdentityService,
              commonService: CommonService) {

    this._router = router;
    this._route = route;
    this._identityService = identityService;
    this._commonService = commonService;
    this._actions = actions;
    this.store = store;
    this.accounts$ = this.store.select('accounts');
  }

  goBack(route: string): void {
    this._router.navigate(['/accounts']);
  }

  viewAccount(account: Account) {
    console.log("account: " + account.id);
    this._router.navigate(['/accounts-detail', account.id]);
  }

  ngOnInit(): void {
    console.log("find accounts");
    this.store.dispatch(this._actions.findAccounts());
  }
}

