import {Injectable} from '@angular/core';
import {Actions, Effect} from '@ngrx/effects';
import {AccountActions} from './account.action';
import {AccountService} from '../../../../services/account.service';
import {from} from 'rxjs/observable/from';
import {AccountModuleState} from '../index';
import {Store} from '@ngrx/store';
import {Account} from '../../../shared/model/account/account.interface';

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
      //this.accountActions.findAccountTransactions(action.payload),
      //this.accountActions.findInvoicesByAccount(action.payload),
      //this.accountActions.findAccountActivities(action.payload),
      this.accountActions.findAccountActivitiesByAcademicSession(action.payload),  
      this.accountActions.findAccountChargeActivities(action.payload),
      this.accountActions.findSecurityAccountCharges(action.payload),
      this.accountActions.findAdmissionAccountCharges(action.payload),
      this.accountActions.findStudentAffairsAccountCharges(action.payload),
      this.accountActions.findLoanAccountCharges(action.payload),
      this.accountActions.findAccountWaivers(action.payload),
      this.accountActions.findSponsorships(action.payload),
    ]));

  @Effect() findAccountActivities$ = this.actions$
    .ofType(AccountActions.FIND_ACCOUNT_ACTIVITIES)
    .map((action) => action.payload)
    .switchMap((account) => this.accountService.findAccountActivities(account))
    .map((account) => this.accountActions.findAccountActivitiesSuccess(account));
  
  @Effect() findAccountActivitiesByAcademicSession$ = this.actions$
  .ofType(AccountActions.FIND_ACCOUNT_ACTIVITIES_BY_SESSION)
  .map((action) => action.payload)
  .switchMap((account) => this.accountService.findAccountActivitiesByAcademicSession(account))
  .map((account) => this.accountActions.findAccountActivitiesByAcademicSessionSuccess(account));

  @Effect() findAccountTransactions$ = this.actions$
    .ofType(AccountActions.FIND_ACCOUNT_TRANSACTIONS)
    .map((action) => action.payload)
    .switchMap((account) => this.accountService.findAccountTransactions(account))
    .map((transactions) => this.accountActions.findAccountTransactionsSuccess(transactions));

  @Effect() findSecurityAccountCharges$ = this.actions$
    .ofType(AccountActions.FIND_SECURITY_ACCOUNT_CHARGES)
    .map((action) => action.payload)
    .switchMap((account) => this.accountService.findSecurityAccountCharges(account))
    .map((charges) => this.accountActions.findSecurityAccountChargesSuccess(charges));

  @Effect() findAdmissionAccountCharges$ = this.actions$
    .ofType(AccountActions.FIND_ADMISSION_ACCOUNT_CHARGES)
    .map((action) => action.payload)
    .switchMap((account) => this.accountService.findAdmissionAccountCharges(account))
    .map((charges) => this.accountActions.findAdmissionAccountChargesSuccess(charges));

  @Effect() findLoanAccountCharges$ = this.actions$
    .ofType(AccountActions.FIND_LOAN_ACCOUNT_CHARGES)
    .map((action) => action.payload)
    .switchMap((account) => this.accountService.findLoanAccountCharges(account))
    .map((charges) => this.accountActions.findLoanAccountChargesSuccess(charges));

  @Effect() findStudentAffairsAccountCharges$ = this.actions$
    .ofType(AccountActions.FIND_STUDENT_AFFAIRS_ACCOUNT_CHARGES)
    .map((action) => action.payload)
    .switchMap((account) => this.accountService.findStudentAffairsAccountCharges(account))
    .map((charges) => this.accountActions.findStudentAffairsAccountChargesSuccess(charges));

  @Effect() findAccountWaivers$ = this.actions$
    .ofType(AccountActions.FIND_ACCOUNT_WAIVERS)
    .map((action) => action.payload)
    .switchMap((account) => this.accountService.findAccountWaivers(account))
    .map((accountWaivers) => this.accountActions.findAccountWaiversSuccess(accountWaivers));

  @Effect() saveAccount$ = this.actions$
    .ofType(AccountActions.SAVE_ACCOUNT)
    .map((action) => action.payload)
    .switchMap((account) => this.accountService.saveAccount(account))
    .map((account) => this.accountActions.saveAccountSuccess(account));

  @Effect() updateAccount$ = this.actions$
    .ofType(AccountActions.UPDATE_ACCOUNT)
    .map((action) => action.payload)
    .switchMap((account) => this.accountService.updateAccount(account))
    .map((account) => this.accountActions.updateAccountSuccess(account));

  @Effect() reviseAccount$ = this.actions$
    .ofType(AccountActions.REVISE_ACCOUNT)
    .map((action) => action.payload)
    .switchMap((account) => this.accountService.reviseAccount(account))
    .map((account) => this.accountActions.reviseAccountSuccess(account));

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

     @Effect() findAccountSponsorships$ = this.actions$
    .ofType(AccountActions.FIND_SPONSORSHIPS)
    .map((action) => action.payload)
    .switchMap((account) => this.accountService.findAccountSponsorships(account))
    .map((sponsorships) => this.accountActions.findSponsorshipsSuccess(sponsorships));

    @Effect() addAccountSponsorships$ = this.actions$
    .ofType(AccountActions.ADD_SPONSORSHIP)
    .map((action) => action.payload)
    .switchMap((payload) => this.accountService.addAccountSponsorships(payload.account, payload.sponsor, payload.sponsorship))
    .map((message) => this.accountActions.addSponsorshipSuccess(message))
    .withLatestFrom(this.store$.select(...this.ACCOUNT))
    .map((state) => state[1])
    .map((account: Account) => this.accountActions.findAccountByCode(account.code));

  @Effect() updateAccountSponsorships$ = this.actions$
    .ofType(AccountActions.UPDATE_SPONSORSHIP)
    .map((action) => action.payload)
    .switchMap((payload) => this.accountService.updateAccountSponsorships(payload.account, payload.sponsor, payload.sponsorship))
    .map((message) => this.accountActions.updateSponsorshipSuccess(message))
    .withLatestFrom(this.store$.select(...this.ACCOUNT))
    .map((state) => state[1])
    .map((account: Account) => this.accountActions.findAccountByCode(account.code));

      @Effect() removeAccountSponsorships$ = this.actions$
    .ofType(AccountActions.REMOVE_SPONSORSHIP)
    .map((action) => action.payload)
    .switchMap((payload) => this.accountService.removeAccountSponsorships(payload.account, payload.sponsor, payload.sponsorship))
    .map((message) => this.accountActions.removeSponsorshipSuccess(message))
    .withLatestFrom(this.store$.select(...this.ACCOUNT))
    .map((state) => state[1])
    .map((account: Account) => this.accountActions.findAccountByCode(account.code));

    @Effect() findAccountCharges$ = this.actions$
    .ofType(AccountActions.FIND_ACCOUNT_CHARGES)
    .map((action) => action.payload)
    .switchMap((account) => this.accountService.findAccountCharges(account))
    .map((charges) => this.accountActions.findAccountChargesSuccess(charges));
    
    @Effect() findUnpaidAccountCharges$ = this.actions$
    .ofType(AccountActions.FIND_UNPAID_ACCOUNT_CHARGES)
    .map((action) => action.payload)
    .switchMap((account) => this.accountService.findUnpaidAccountCharges(account))
    .map((accountCharges) => this.accountActions.findUnpaidAccountChargesSuccess(accountCharges));
    
    @Effect() findAccountChargeActivities$ = this.actions$
    .ofType(AccountActions.FIND_ACCOUNT_CHARGES_ACTIVITIES)
    .map((action) => action.payload)
    .switchMap((account) => this.accountService.findAccountChargeActivities(account))
    .map((accountCharges) => this.accountActions.findAccountChargeActivitiesSuccess(accountCharges));
}

