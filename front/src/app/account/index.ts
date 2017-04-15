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
import {AccountModule} from "./accounts/index";
import {AccountListState, accountListReducer} from "./accounts/account-list.reducer";
import {accountReducer, AccountState} from "./accounts/account.reducer";
import {AccountTransactionListState, accountTransactionListReducer} from "./accounts/account-transaction-list.reducer";

export interface AccountModuleState {
  accounts: AccountListState;
  account: AccountState;
  accountTransactions:AccountTransactionListState;
};

export const INITIAL_ACCOUNT_DATA: AccountModuleState = <AccountModuleState>{accounts:[], account:{},accountTransactions:[]};
const reducers = {accounts:accountListReducer, account:accountReducer, accountTransactions:accountTransactionListReducer};
const productionReducer: ActionReducer<AccountModuleState> = combineReducers(reducers);

export function accountModuleReducer(state: any = INITIAL_ACCOUNT_DATA, action: any) {
  return productionReducer(state, action);
}

@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),

    // our modules
    AccountModule.forRoot(),
  ],
  declarations: [
    // page
    AccountPage,
  ],
  exports: [],
})
export class AccountModuleX {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: AccountModuleX,
      providers: [
        appRoutingProviders,
        IdentityService,
        CommonService,
        AccountService,
      ],
    };
  }
}

