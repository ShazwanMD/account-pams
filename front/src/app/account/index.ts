import {NgModule, ModuleWithProviders} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../app.routes';

import {CovalentCoreModule} from '@covalent/core';

import {CommonService} from '../../services';
import {IdentityService} from '../../services';

import {AccountPage} from './account.page';
import {AccountService} from '../../services/account.service';
import {AccountSubModule} from './accounts/index';
import {AccountListState, accountListReducer} from './accounts/account-list.reducer';
import {accountReducer, AccountState} from './accounts/account.reducer';
import {AccountTransactionListState, accountTransactionListReducer} from './accounts/account-transaction-list.reducer';
import {IdentityModule} from '../identity/index';
import {chargeCodeReducer, ChargeCodeState} from './charge-codes/charge-code.reducer';
import {chargeCodeListReducer, ChargeCodeListState} from './charge-codes/charge-code-list.reducer';
import {ChargeCodeSubModule} from './charge-codes/index';
import {
  academicSessionListReducer,
  AcademicSessionListState,
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
  securityAccountChargeListReducer,
} from './accounts/account-charge-list.reducer';
import {ChargeCodeSelectComponent} from './charge-codes/component/charge-code-select.component';
import {accountWaiverReducer, AccountWaiverState} from './accounts/account-waivers.reducer';

export interface AccountModuleState {
  academicSessions: AcademicSessionListState;
  academicSession: AcademicSessionState;
  accounts: AccountListState;
  account: AccountState;
  accountTransactions: AccountTransactionListState;
  securityAccountCharges: AccountChargeListState;
  admissionAccountCharges: AccountChargeListState;
  accountCharges: AccountChargeListState;
  chargeCode: ChargeCodeState;
  chargeCodes: ChargeCodeListState;
  feeSchedules: FeeScheduleListState;
  feeSchedule: FeeScheduleState;
  feeScheduleItems: FeeScheduleItemListState;
  accountWaiver: AccountWaiverState;
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
    chargeCodes: [],
    chargeCode: {},
    feeSchedules: [],
    feeSchedule: {},
    feeScheduleItems: [],
    accountWaiver: [],
  };

export const accountModuleReducers = {
  academicSessions: academicSessionListReducer,
  academicSession: academicSessionReducer,
  accounts: accountListReducer,
  account: accountReducer,
  accountTransactions: accountTransactionListReducer,
  securityAccountCharges: securityAccountChargeListReducer,
  admissionAccountCharges: admissionAccountChargeListReducer,
  chargeCodes: chargeCodeListReducer,
  chargeCode: chargeCodeReducer,
  feeSchedules: feeScheduleListReducer,
  feeSchedule: feeScheduleReducer,
  feeScheduleItems: feeScheduleItemListReducer,
  accountWaiver: accountWaiverReducer,
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

