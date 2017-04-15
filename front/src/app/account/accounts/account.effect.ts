import {Injectable} from '@angular/core';
import {Effect, Actions} from '@ngrx/effects';
import {AccountActions} from "./account.action";
import {AccountService} from "../../../services/account.service";
import {from} from "rxjs/observable/from";


@Injectable()
export class AccountEffects {
  constructor(private actions$: Actions,
              private accountActions: AccountActions,
              private accountService: AccountService) {
  }

  @Effect() findAccounts$ = this.actions$
    .ofType(AccountActions.FIND_ACCOUNTS)
    .switchMap(() => this.accountService.findAccounts())
    .map(accounts => this.accountActions.findAccountsSuccess(accounts));

  @Effect() findAccount$ = this.actions$
    .ofType(AccountActions.FIND_ACCOUNT)
    .map(action => action.payload)
    .switchMap(code => this.accountService.findAccountByCode(code))
    .map(account => this.accountActions.findAccountSuccess(account))
    .mergeMap(action => from([action, this.accountActions.findAccountTransactions(action.payload)]));

  @Effect() findAccountTransactions$ = this.actions$
    .ofType(AccountActions.FIND_ACCOUNT_TRANSACTIONS)
    .map(action => action.payload)
    .switchMap(account => this.accountService.findAccountTransactions(account))
    .map(transactions => this.accountActions.findAccountTransactionsSuccess(transactions));

  @Effect() saveAccount$ = this.actions$
    .ofType(AccountActions.SAVE_ACCOUNT)
    .map(action => action.payload)
    .switchMap(account => this.accountService.saveAccount(account))
    .map(account => this.accountActions.saveAccountSuccess(account));

  @Effect() updateAccount$ = this.actions$
    .ofType(AccountActions.UPDATE_ACCOUNT)
    .map(action => action.payload)
    .switchMap(account => this.accountService.updateAccount(account))
    .map(account => this.accountActions.updateAccountSuccess(account));

  // @Effect() createAccount = this.actions$
  //   .ofType(AccountActions.CREATE_ACCOUNT)
  //   .map(action => action.payload)
  //   .switchMap(account => this.accountService.saveAccount(account))
  //   .map(account => this.accountActions.addAccountSuccess(account));
  //
  // @Effect() deleteAccount$ = this.actions$
  //   .ofType(AccountActions.DELETE_ACCOUNT)
  //   .map(action => action.payload)
  //   .switchMap(account => this.accountService.deleteAccount(account))
  //   .map(account => this.accountActions.deleteAccountSuccess(account));
}
