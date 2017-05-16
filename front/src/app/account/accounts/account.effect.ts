import {Injectable} from '@angular/core';
import {Effect, Actions} from '@ngrx/effects';
import {AccountActions} from "./account.action";
import {AccountService} from "../../../services/account.service";
import {from} from "rxjs/observable/from";
import {AccountModuleState} from "../index";
import {Store} from "@ngrx/store";


@Injectable()
export class AccountEffects {

  private ACCOUNT = "accountModuleState.account".split(".");

  constructor(private actions$: Actions,
              private accountActions: AccountActions,
              private accountService: AccountService,
              private store$: Store<AccountModuleState>) {
  }

  @Effect() findAccounts$ = this.actions$
    .ofType(AccountActions.FIND_ACCOUNTS)
    .map(action => action.payload)
    .switchMap(() => this.accountService.findAccounts())
    .map(accounts => this.accountActions.findAccountsSuccess(accounts));

  @Effect() findAccountsByFilter$ = this.actions$
    .ofType(AccountActions.FIND_ACCOUNTS_BY_FILTER)
    .map(action => action.payload)
    .switchMap(filter => this.accountService.findAccountsByFilter(filter))
    .map(accounts => this.accountActions.findAccountsSuccess(accounts));

  @Effect() findAccount$ = this.actions$
    .ofType(AccountActions.FIND_ACCOUNT)
    .map(action => action.payload)
    .switchMap(code => this.accountService.findAccountByCode(code))
    .map(account => this.accountActions.findAccountSuccess(account))
    .mergeMap(action => from([action,
      this.accountActions.findAccountTransactions(action.payload),
      this.accountActions.findAccountCharges(action.payload)
    ]));

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

  @Effect() addAdmissionCharge$ = this.actions$
    .ofType(AccountActions.ADD_ADMISSION_CHARGE)
    .map(action => action.payload)
    .switchMap(payload => this.accountService.addAdmissionCharge(payload.account, payload.charge))
    .map(message => this.accountActions.addAdmissionChargeSuccess(message))
    .withLatestFrom(this.store$.select(...this.ACCOUNT))
    .map(state => state[1])
    .mergeMap(account => from([account,
      this.accountActions.findAccountTransactions(account),
      this.accountActions.findAccountCharges(account)
    ]));
}
