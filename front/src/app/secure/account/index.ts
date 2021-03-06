import {ModuleWithProviders, NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../../app.routes';

import {CovalentCoreModule} from '@covalent/core';

import {CommonService, IdentityService} from '../../../services';

import {AccountPage} from './account.page';
import {AccountService} from '../../../services/account.service';
import {AccountSubModule} from './accounts/index';
import {accountListReducer, AccountListState} from './accounts/account-list.reducer';
import {accountReducer, AccountState} from './accounts/account.reducer';
import {accountTransactionListReducer, AccountTransactionListState} from './accounts/account-transaction-list.reducer';
import {IdentityModule} from '../identity/index';
import {chargeCodeReducer, ChargeCodeState} from './charge-codes/charge-code.reducer';
import {chargeCodeListReducer, ChargeCodeListState} from './charge-codes/charge-code-list.reducer';
import {ChargeCodeSubModule} from './charge-codes/index';
import {
  academicSessionListReducer,
  AcademicSessionListState
} from './academic-sessions/component/academic-session-list.reducer';
import {academicSessionReducer, AcademicSessionState} from './academic-sessions/academic-session.reducer';
import {AcademicSessionSubModule} from './academic-sessions/index';
import {FeeScheduleSubModule} from './fee-schedules/index';
import {feeScheduleReducer, FeeScheduleState} from './fee-schedules/fee-schedule.reducer';
import {feeScheduleItemListReducer, FeeScheduleItemListState} from './fee-schedules/fee-schedule-item-list.reducer';
import {feeScheduleListReducer, FeeScheduleListState} from './fee-schedules/fee-schedule-list.reducer';
import {
  AccountChargeListState,
  admissionAccountChargeListReducer,
  loanAccountChargeListReducer,
  securityAccountChargeListReducer,
  studentAffairsAccountChargeListReducer,
accountChargeListReducer
} from './accounts/account-charge-list.reducer';
import {ChargeCodeSelectComponent} from './charge-codes/component/charge-code-select.component';
import {accountWaiverReducer, AccountWaiverState} from './accounts/account-waivers.reducer';
import {accountStudentListReducer, AccountStudentListState} from './accounts/account-student-list.reducer';
import {accountSponsorListReducer, AccountSponsorListState} from './accounts/account-sponsor-list.reducer';
import {accountActivityListReducer, AccountActivityListState} from './accounts/account-activity.reducer';
import { AccountSponsorshipState, accountSponsorshipReducer } from "./accounts/account-sponsorship.reducer";
import { AccountChargeActivityListState, accountChargeActivityListReducer } from "./accounts/account-activity-charge.reducer";

export interface AccountModuleState {
  academicSessions: AcademicSessionListState;
  academicSession: AcademicSessionState;
  accounts: AccountListState;
  account: AccountState;
  accountTransactions: AccountTransactionListState;
  securityAccountCharges: AccountChargeListState;
  admissionAccountCharges: AccountChargeListState;
  loanAccountCharges: AccountChargeListState;
  studentAffairsAccountCharges: AccountChargeListState;
  accountCharges: AccountChargeListState;
  chargeCode: ChargeCodeState;
  chargeCodes: ChargeCodeListState;
  feeSchedules: FeeScheduleListState;
  feeSchedule: FeeScheduleState;
  feeScheduleItems: FeeScheduleItemListState;
  accountWaiver: AccountWaiverState;
  accountStudentList: AccountStudentListState;
  accountSponsorList: AccountSponsorListState;
  accountActivities: AccountActivityListState;
  accountSponsorships:AccountSponsorshipState;
  accountChargesActivities: AccountChargeActivityListState;
}
;

export const INITIAL_ACCOUNT_STATE: AccountModuleState =
  <AccountModuleState>{
    academicSessions: [],
    academicSession: {},
    accounts: [],
    account: {},
    accountTransactions: [],
    securityAccountCharges: [],
    admissionAccountCharges: [],
    loanAccountCharges: [],
    studentAffairsAccountCharges: [],
    chargeCodes: [],
    chargeCode: {},
    feeSchedules: [],
    feeSchedule: {},
    feeScheduleItems: [],
    accountWaiver: [],
    accountStudentList: [],
    accountSponsorList: [],
    accountActivities: [],
    accountSponsorships: [],
    accountCharges: [],
    accountChargesActivities: [],
  };

export const accountModuleReducers = {
  academicSessions: academicSessionListReducer,
  academicSession: academicSessionReducer,
  accounts: accountListReducer,
  account: accountReducer,
  accountTransactions: accountTransactionListReducer,
  securityAccountCharges: securityAccountChargeListReducer,
  admissionAccountCharges: admissionAccountChargeListReducer,
  loanAccountCharges: loanAccountChargeListReducer,
  studentAffairsAccountCharges: studentAffairsAccountChargeListReducer,
  chargeCodes: chargeCodeListReducer,
  chargeCode: chargeCodeReducer,
  feeSchedules: feeScheduleListReducer,
  feeSchedule: feeScheduleReducer,
  feeScheduleItems: feeScheduleItemListReducer,
  accountWaiver: accountWaiverReducer,
  accountStudentList: accountStudentListReducer,
  accountSponsorList: accountSponsorListReducer,
  accountActivities: accountActivityListReducer,
  accountSponsorships:accountSponsorshipReducer,
  accountCharges:accountChargeListReducer,
  accountChargesActivities: accountChargeActivityListReducer,
};

@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
    IdentityModule.forRoot(),
    AccountSubModule.forRoot(),
    AcademicSessionSubModule.forRoot(),
    ChargeCodeSubModule.forRoot(),
    FeeScheduleSubModule.forRoot(),
  ],
  declarations: [
    AccountPage,

  ],
  exports: [
    ChargeCodeSelectComponent,
  ],

  entryComponents: [],
})
export class AccountModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: AccountModule,
      providers: [
        appRoutingProviders,
        IdentityService,
        CommonService,
        AccountService,
      ],
    };
  }
}

