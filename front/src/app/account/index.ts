import {NgModule, ModuleWithProviders} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../app.routes';
import {combineReducers, ActionReducer} from "@ngrx/store";

import {CovalentCoreModule} from '@covalent/core';

import {CommonService} from '../../services';
import {IdentityService} from '../../services';

import {AccountPage} from "./account.page";
import {AccountService} from "../../services/account.service";
import {AccountSubModule} from "./accounts/index";
import {AccountListState, accountListReducer} from "./accounts/account-list.reducer";
import {accountReducer, AccountState} from "./accounts/account.reducer";
import {AccountTransactionListState, accountTransactionListReducer} from "./accounts/account-transaction-list.reducer";
import {AccountComboBoxComponent} from "./accounts/component/account-combo-box.component";
import {IdentityModule} from "../identity/index";
import {chargeCodeReducer, ChargeCodeState} from "./charge-codes/charge-code.reducer";
import {chargeCodeListReducer, ChargeCodeListState} from "./charge-codes/charge-code-list.reducer";
import {ChargeCodeSubModule} from "./charge-codes/index";

export interface AccountModuleState {
  accounts: AccountListState;
  account: AccountState;
  accountTransactions: AccountTransactionListState
  chargeCode: ChargeCodeState
  chargeCodes: ChargeCodeListState
}
;

export const INITIAL_ACCOUNT_STATE: AccountModuleState =
  <AccountModuleState>{
    accounts: [],
    account: {},
    accountTransactions: [],
    chargeCodes: [],
    chargeCode: {}
  };

export const accountModuleReducers = {
  accounts: accountListReducer,
  account: accountReducer,
  accountTransactions: accountTransactionListReducer,
  chargeCodes: chargeCodeListReducer,
  chargeCode: chargeCodeReducer,
};

@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
    AccountSubModule.forRoot(),
    ChargeCodeSubModule.forRoot(),
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

