import {NgModule, ModuleWithProviders} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../app.routes';

import {CovalentCoreModule} from '@covalent/core';

import {CommonService} from '../../services';
import {IdentityService} from '../../services';

import {AccountPage} from "./account.page";
import {AccountService} from "../../services/account.service";
import {AccountSubModule} from "./accounts/index";
import {AccountListState, accountListReducer} from "./accounts/account-list.reducer";
import {accountReducer, AccountState} from "./accounts/account.reducer";
import {AccountTransactionListState, accountTransactionListReducer} from "./accounts/account-transaction-list.reducer";
import {IdentityModule} from "../identity/index";
import {chargeCodeReducer, ChargeCodeState} from "./charge-codes/charge-code.reducer";
import {chargeCodeListReducer, ChargeCodeListState} from "./charge-codes/charge-code-list.reducer";
import {ChargeCodeSubModule} from "./charge-codes/index";
import {
  academicSessionListReducer,
  AcademicSessionListState
} from "./academic-sessions/component/academic-session-list.reducer";
import {academicSessionReducer, AcademicSessionState} from "./academic-sessions/academic-session.reducer";
import {AcademicSessionSubModule} from "./academic-sessions/index";
import {FeeScheduleSubModule} from "./fee-schedules/index";
import {feeScheduleReducer, FeeScheduleState} from "./fee-schedules/fee-schedule.reducer";
import {feeScheduleItemListReducer, FeeScheduleItemListState} from "./fee-schedules/fee-schedule-item-list.reducer";
import {feeScheduleListReducer, FeeScheduleListState} from "./fee-schedules/fee-schedule-list.reducer";
import {accountChargeListReducer, AccountChargeListState} from "./accounts/account-charge-list.reducer";

export interface AccountModuleState {
  academicSessions: AcademicSessionListState;
  academicSession: AcademicSessionState;
  accounts: AccountListState;
  account: AccountState;
  accountTransactions: AccountTransactionListState;
  accountCharges:AccountChargeListState;
  chargeCode: ChargeCodeState;
  chargeCodes: ChargeCodeListState;
  feeSchedules: FeeScheduleListState;
  feeSchedule: FeeScheduleState;
  feeScheduleItems: FeeScheduleItemListState;
}
;

export const INITIAL_ACCOUNT_STATE: AccountModuleState =
  <AccountModuleState>{
    academicSessions: [],
    academicSession: {},
    accounts: [],
    account: {},
    accountTransactions: [],
    accountCharges: [],
    chargeCodes: [],
    chargeCode: {},
    feeSchedules:[],
    feeSchedule:{},
    feeScheduleItems:[],
  };

export const accountModuleReducers = {
  academicSessions: academicSessionListReducer,
  academicSession: academicSessionReducer,
  accounts: accountListReducer,
  account: accountReducer,
  accountTransactions: accountTransactionListReducer,
  accountCharges: accountChargeListReducer,
  chargeCodes: chargeCodeListReducer,
  chargeCode: chargeCodeReducer,
  feeSchedules: feeScheduleListReducer,
  feeSchedule: feeScheduleReducer,
  feeScheduleItems:feeScheduleItemListReducer,
};

@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
    AccountSubModule.forRoot(),
    ChargeCodeSubModule.forRoot(),
    AccountSubModule.forRoot(),
    AcademicSessionSubModule.forRoot(),
    FeeScheduleSubModule.forRoot(),
    IdentityModule.forRoot(),
  ],
  declarations: [
    AccountPage,
  ],
  exports: [],
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

