import {NgModule, ModuleWithProviders} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../app.routes';
import {environment} from '../../environments/environment';
import {combineReducers, StoreModule} from "@ngrx/store";

import {CovalentCoreModule} from '@covalent/core';

import {CommonService} from '../../services';
import {IdentityService} from '../../services';

import {AccountPage} from "./account.page";
import {AccountService} from "../../services/account.service";
import {AccountModule} from "./accounts/index";
import {AccountListState, accountListReducer} from "./accounts/account-list.reducer";
import {accountReducer} from "./accounts/account.reducer";
import {compose} from "@ngrx/core/compose";

export interface AccountState {
  accounts: AccountListState;
  account: AccountState;
}
;

export const accountxReducer = compose(combineReducers)({
  accounts: accountListReducer, account: accountReducer,
});


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

