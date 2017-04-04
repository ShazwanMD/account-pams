import {Component, OnInit, ChangeDetectionStrategy} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {IdentityService} from '../../../services';
import {CommonService} from '../../../services';
import {Store} from "@ngrx/store";
import {Observable} from "rxjs";
import {Account} from "./account.interface";
import {AccountActions} from "./account.action";
import {AccountState} from "../index";

@Component({
  selector: 'pams-account-detail',
  templateUrl: './account-detail.page.html',
})

export class AccountDetailPage implements OnInit {

  private _identityService: IdentityService;
  private _commonService: CommonService;
  private _router: Router;
  private _route: ActivatedRoute;
  private _actions: AccountActions;
  private store: Store<AccountState>;
  private account$: Observable<Account>;

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
    this.account$ = this.store.select('account');
  }

  ngOnInit(): void {
    this._route.params.subscribe((params: {code: string}) => {
      let code: string = params.code;
      this.store.dispatch(this._actions.findAccount(code));
    });
  }

  goBack(route: string): void {
    this._router.navigate(['/accounts']);
  }
}

