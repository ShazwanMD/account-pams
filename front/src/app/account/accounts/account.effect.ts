import {Injectable} from '@angular/core';
import {Effect, Actions} from '@ngrx/effects';
import {AccountActions} from "./account.action";
import {AccountService} from "../../../services/account.service";
import {from} from "rxjs/observable/from";
import {AccountModuleState} from "../index";
import {Store} from "@ngrx/store";
import {Account} from "./account.interface";


@Injectable()
export class AccountEffects {

  private ACCOUNT: string[] = "accountModuleState.account".split(".");

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

  @Effect() findAccountByCode$ = this.actions$
    .ofType(AccountActions.FIND_ACCOUNT_BY_CODE)
    .map(action => action.payload)
    .switchMap(code => this.accountService.findAccountByCode(code))
    .map(account => this.accountActions.findAccountByCodeSuccess(account))
    .mergeMap(action => from([action,
      this.accountActions.findAccountTransactions(action.payload),
      this.accountActions.findAccountCharges(action.payload),
      this.accountActions.findAccountWaivers(action.payload),
    ]));

  @Effect() findAccountTransactions$ = this.actions$
    .ofType(AccountActions.FIND_ACCOUNT_TRANSACTIONS)
    .map(action => action.payload)
    .switchMap(account => this.accountService.findAccountTransactions(account))
    .map(transactions => this.accountActions.findAccountTransactionsSuccess(transactions));

  @Effect() findAccountCharges$ = this.actions$
    .ofType(AccountActions.FIND_ACCOUNT_CHARGES)
    .map(action => action.payload)
    .switchMap(account => this.accountService.findAccountCharges(account))
    .map(charges => this.accountActions.findAccountChargesSuccess(charges));
  
  @Effect() findAccountWaivers$ = this.actions$
  .ofType(AccountActions.FIND_ACCOUNT_WAIVERS)
  .map(action => action.payload)
  .switchMap(account => this.accountService.findAccountWaivers(account))
  .map(accountWaivers => this.accountActions.findAccountWaiversSuccess(accountWaivers));

  @Effect() saveAccount$ = this.actions$
    .ofType(AccountActions.SAVE_ACCOUNT)
    .map(action => action.payload)
    .switchMap(account => this.accountService.saveAccount(account))
    .map(account => this.accountActions.saveAccountSuccess(account))
    .mergeMap(action => from([action, this.accountActions.findAccounts()]));

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
    .map((account:Account) => this.accountActions.findAccountByCode(account.code));

  @Effect() removeAdmissionCharge$ = this.actions$
    .ofType(AccountActions.REMOVE_ADMISSION_CHARGE)
   .map(action => action.payload)
    .switchMap(payload => this.accountService.removeAdmissionCharge(payload.account, payload.charge))
    .map(message => this.accountActions.removeAdmissionChargeSuccess(message))
    .withLatestFrom(this.store$.select(...this.ACCOUNT))
    .map(state => state[1])
    .map((account:Account) => this.accountActions.findAccountByCode(account.code));


  @Effect() updateAdmissionCharge$ = this.actions$
    .ofType(AccountActions.UPDATE_ADMISSION_CHARGE)
   .map(action => action.payload)
    .switchMap(payload => this.accountService.updateAdmissionCharge(payload.account, payload.charge))
    .map(message => this.accountActions.updateAdmissionChargeSuccess(message))
    .withLatestFrom(this.store$.select(...this.ACCOUNT))
    .map(state => state[1])
    .map((account:Account) => this.accountActions.findAccountByCode(account.code));

    @Effect() addCompoundCharge$ = this.actions$
    .ofType(AccountActions.ADD_COMPOUND_CHARGE)
    .map(action => action.payload)
    .switchMap(payload => this.accountService.addCompoundCharge(payload.account, payload.charge))
    .map(message => this.accountActions.addCompoundChargeSuccess(message))
    .withLatestFrom(this.store$.select(...this.ACCOUNT))
    .map(state => state[1])
    .map((account:Account) => this.accountActions.findAccountByCode(account.code));

  @Effect() removeCompoundCharge$ = this.actions$
    .ofType(AccountActions.REMOVE_COMPOUND_CHARGE)
   .map(action => action.payload)
    .switchMap(payload => this.accountService.removeCompoundCharge(payload.account, payload.charge))
    .map(message => this.accountActions.removeCompoundChargeSuccess(message))
    .withLatestFrom(this.store$.select(...this.ACCOUNT))
    .map(state => state[1])
    .map((account:Account) => this.accountActions.findAccountByCode(account.code));

  @Effect() updateCompoundCharge$ = this.actions$
    .ofType(AccountActions.UPDATE_COMPOUND_CHARGE)
   .map(action => action.payload)
    .switchMap(payload => this.accountService.updateCompoundCharge(payload.account, payload.charge))
    .map(message => this.accountActions.updateCompoundChargeSuccess(message))
    .withLatestFrom(this.store$.select(...this.ACCOUNT))
    .map(state => state[1])
    .map((account:Account) => this.accountActions.findAccountByCode(account.code));
}

