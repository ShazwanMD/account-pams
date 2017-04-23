import {Component, OnInit, ChangeDetectionStrategy} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {IdentityService} from '../../../services';
import {CommonService} from '../../../services';
import {Store} from "@ngrx/store";
import {Observable} from "rxjs";
import {Account} from "./account.interface";
import {AccountActions} from "./account.action";
import {AccountTransaction} from "./account-transaction.interface";
import {AccountModuleState} from "../index";

@Component({
  selector: 'pams-account-detail',
  templateUrl: './account-detail.page.html',
})

export class AccountDetailPage implements OnInit {

  private ACCOUNT = "accountModuleState.account".split(".");
  private ACCOUNT_TRANSACTIONS = "accountModuleState.accountTransactions".split(".");
  private account$: Observable<Account>;
  private accountTransactions$: Observable<AccountTransaction[]>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: AccountActions,
              private store: Store<AccountModuleState>) {

    this.account$ = this.store.select(...this.ACCOUNT);
    this.accountTransactions$ = this.store.select(...this.ACCOUNT_TRANSACTIONS);
  }

  ngOnInit(): void {
    this.route.params.subscribe((params: {code: string}) => {
      let code: string = params.code;
      this.store.dispatch(this.actions.findAccount(code));
    });
  }

  goBack(route: string): void {
    this.router.navigate(['/accounts']);
  }
}

