import {Injectable} from '@angular/core';
import {Effect, Actions} from '@ngrx/effects';
import {AccountActions} from './account.action';
import {AccountService} from '../../../services/account.service';
import {from} from 'rxjs/observable/from';
import {AccountModuleState} from '../index';
import {Store} from '@ngrx/store';
import {Account} from './account.interface';

@Injectable()
export class AccountEffects {

  private ACCOUNT: string[] = 'accountModuleState.account'.split('.');

  constructor(private actions$: Actions,
              private accountActions: AccountActions,
              private accountService: AccountService,
              private store$: Store<AccountModuleState>) {
  }

  @Effect() findAccounts$ = this.actions$
    .ofType(AccountActions.FIND_ACCOUNTS)
    .map((action) => action.payload)
    .switchMap(() => this.accountService.findAccounts())
    .map((accounts) => this.accountActions.findAccountsSuccess(accounts));

  @Effect() findAccountsByFilter$ = this.actions$
    .ofType(AccountActions.FIND_ACCOUNTS_BY_FILTER)
    .map((action) => action.payload)
    .switchMap((filter) => this.accountService.findAccountsByFilter(filter))
    .map((accounts) => this.accountActions.findAccountsSuccess(accounts));

  @Effect() findAccountsByActor$ = this.actions$
  .ofType(AccountActions.FIND_ACCOUNTS_BY_ACTOR)
  .map((action) => action.payload)
  .switchMap((actorType) => this.accountService.findAccountsByActor())
  .map((accounts) => this.accountActions.findAccountsByActorSuccess(accounts));

  @Effect() findAccountsByActorSponsor$ = this.actions$
  .ofType(AccountActions.FIND_ACCOUNTS_BY_ACTOR_SPONSOR)
  .map((action) => action.payload)
  .switchMap((actorType) => this.accountService.findAccountsByActorSponsor())
  .map((accounts) => this.accountActions.findAccountsByActorSponsorSuccess(accounts));

  @Effect() findAccountsByActorStaff$ = this.actions$
  .ofType(AccountActions.FIND_ACCOUNTS_BY_ACTOR_STAFF)
  .map((action) => action.payload)
  .switchMap((actorType) => this.accountService.findAccountsByActorStaff())
  .map((accounts) => this.accountActions.findAccountsByActorStaffSuccess(accounts));

  @Effect() findAccountByCode$ = this.actions$
    .ofType(AccountActions.FIND_ACCOUNT_BY_CODE)
    .map((action) => action.payload)
    .switchMap((code) => this.accountService.findAccountByCode(code))
    .map((account) => this.accountActions.findAccountByCodeSuccess(account))
    .mergeMap((action) => from([action,
      this.accountActions.findAccountTransactions(action.payload),
      this.accountActions.findAccountCharges(action.payload),
      this.accountActions.findAccountWaivers(action.payload),
    ]));

  @Effect() findAccountTransactions$ = this.actions$
    .ofType(AccountActions.FIND_ACCOUNT_TRANSACTIONS)
    .map((action) => action.payload)
    .switchMap((account) => this.accountService.findAccountTransactions(account))
    .map((transactions) => this.accountActions.findAccountTransactionsSuccess(transactions));

  @Effect() findAccountCharges$ = this.actions$
    .ofType(AccountActions.FIND_ACCOUNT_CHARGES)
    .map((action) => action.payload)
    .switchMap((account) => this.accountService.findAccountCharges(account))
    .map((charges) => this.accountActions.findAccountChargesSuccess(charges));

  @Effect() findAccountWaivers$ = this.actions$
  .ofType(AccountActions.FIND_ACCOUNT_WAIVERS)
  .map((action) => action.payload)
  .switchMap((account) => this.accountService.findAccountWaivers(account))
  .map((accountWaivers) => this.accountActions.findAccountWaiversSuccess(accountWaivers));

  @Effect() saveAccount$ = this.actions$
    .ofType(AccountActions.SAVE_ACCOUNT)
    .map((action) => action.payload)
    .switchMap((account) => this.accountService.saveAccount(account))
    .map((account) => this.accountActions.saveAccountSuccess(account))
<<<<<<< HEAD
    .mergeMap((action) => from([action, this.accountActions.findAccountsByActor()]));
=======
    .mergeMap((action) => from([action, this.accountActions.findAccounts()]));
>>>>>>> branch 'master' of http://119.110.101.9/pams/account.git

  @Effect() updateAccount$ = this.actions$
    .ofType(AccountActions.UPDATE_ACCOUNT)
    .map((action) => action.payload)
    .switchMap((account) => this.accountService.updateAccount(account))
    .map((account) => this.accountActions.updateAccountSuccess(account));

  @Effect() addAccountCharge$ = this.actions$
    .ofType(AccountActions.ADD_ACCOUNT_CHARGE)
    .map((action) => action.payload)
    .switchMap((payload) => this.accountService.addAccountCharge(payload.account, payload.charge))
    .map((message) => this.accountActions.addAccountChargeSuccess(message))
    .withLatestFrom(this.store$.select(...this.ACCOUNT))
    .map((state) => state[1])
    .map((account: Account) => this.accountActions.findAccountByCode(account.code));

  @Effect() updateAccountCharge$ = this.actions$
    .ofType(AccountActions.UPDATE_ACCOUNT_CHARGE)
   .map((action) => action.payload)
    .switchMap((payload) => this.accountService.updateAccountCharge(payload.account, payload.charge))
    .map((message) => this.accountActions.updateAccountChargeSuccess(message))
    .withLatestFrom(this.store$.select(...this.ACCOUNT))
    .map((state) => state[1])
    .map((account: Account) => this.accountActions.findAccountByCode(account.code));

  @Effect() removeAccountCharge$ = this.actions$
    .ofType(AccountActions.REMOVE_ACCOUNT_CHARGE)
    .map((action) => action.payload)
    .switchMap((payload) => this.accountService.removeAccountCharge(payload.account, payload.charge))
    .map((message) => this.accountActions.removeAccountChargeSuccess(message))
    .withLatestFrom(this.store$.select(...this.ACCOUNT))
    .map((state) => state[1])
    .map((account: Account) => this.accountActions.findAccountByCode(account.code));
}

