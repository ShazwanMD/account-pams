import {Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {IdentityService} from '../../services';
import {AccountService} from "../../services/account.service";

@Component({
  selector: 'pams-account-page',
  templateUrl: './account.page.html',
})

export class AccountPage implements OnInit {

  private _router: Router;
  private _route: ActivatedRoute;
  private _identityService: IdentityService;
  private _accountService: AccountService;

  constructor(router: Router,
              route: ActivatedRoute,
              identityService: IdentityService,
              accountService: AccountService) {
    this._router = router;
    this._route = route;
    this._accountService = accountService;
  }

  ngOnInit(): void {
    this._route.params.subscribe(() => {
    });
  }
}
