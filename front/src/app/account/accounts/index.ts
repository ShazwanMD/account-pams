import {NgModule, ModuleWithProviders} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../../app.routes';
import {environment} from '../../../environments/environment';
import {EffectsModule} from "@ngrx/effects";

import {CovalentCoreModule} from '@covalent/core';

import {CommonService} from '../../../services';
import {IdentityService} from '../../../services';
import {BillingService} from "../../../services/billing.service";
import {AccountService} from "../../../services/account.service";
import {AccountActions} from "./account.action";
import {AccountEffects} from "./account.effect";
import {AccountCenterPage} from "./account-center.page";
import {AccountListComponent} from "./component/account-list.component";
import {AccountDetailPage} from "./account-detail.page";
import {AccountComponent} from "./component/account.component";
import {AccountActivityComponent} from "./component/account-activity.component";
import {AccountStatusComponent} from "./component/account-status.component";
import {AccountComboBoxComponent} from "./component/account-combo-box.component";
import {AccountSelectComponent} from "./component/account-select.component";
import {AccountCreatorDialog} from "./dialog/account-creator.dialog";
import {IdentityModule} from "../../identity/index";


@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
    IdentityModule.forRoot(),
    EffectsModule.run(AccountEffects),
  ],
  declarations: [
    // page
    AccountCenterPage,
    AccountDetailPage,

    // component
    AccountListComponent,
    AccountComponent,
    AccountStatusComponent,
    AccountActivityComponent,
    AccountComboBoxComponent,
    AccountSelectComponent,
    AccountCreatorDialog,

  ],
  exports: [
    AccountComboBoxComponent,
    AccountSelectComponent,
  ],
  entryComponents: [
    AccountCreatorDialog,
  ],

})
export class AccountSubModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: AccountSubModule,
      providers: [
        appRoutingProviders,
        IdentityService,
        CommonService,
        AccountService,
        AccountActions
      ],
    };
  }
}
