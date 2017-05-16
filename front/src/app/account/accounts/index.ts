import {NgModule, ModuleWithProviders} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../../app.routes';
import {EffectsModule} from "@ngrx/effects";

import {CovalentCoreModule} from '@covalent/core';

import {CommonService} from '../../../services';
import {IdentityService} from '../../../services';
import {AccountService} from "../../../services/account.service";
import {AccountActions} from "./account.action";
import {AccountEffects} from "./account.effect";
import {AccountCenterPage} from "./account-center.page";
import {AccountListComponent} from "./component/account-list.component";
import {AccountDetailPage} from "./account-detail.page";
import {AccountComponent} from "./component/account.component";
import {AccountStatusComponent} from "./component/account-status.component";
import {AccountComboBoxComponent} from "./component/account-combo-box.component";
import {AccountSelectComponent} from "./component/account-select.component";
import {AccountCreatorDialog} from "./dialog/account-creator.dialog";
import {IdentityModule} from "../../identity/index";
import {AccountActivityListComponent} from "./component/account-activity-list.component";
import {AccountChargeListComponent} from "./component/account-charge-list.component";
import {AccountWaiverListComponent} from "./component/account-waiver-list.component";
import {AdmissionChargeDialog} from "./dialog/admission-charge.dialog";
import {CommonModule} from "../../common/index";
import {AcademicSessionSubModule} from "../academic-sessions/index";


@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
    IdentityModule.forRoot(),
    CommonModule.forRoot(),
    AcademicSessionSubModule.forRoot(),
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
    AccountActivityListComponent,
    AccountChargeListComponent,
    AccountWaiverListComponent,
    AccountComboBoxComponent,
    AccountSelectComponent,
    AccountCreatorDialog,
    AdmissionChargeDialog,

  ],
  exports: [
    AccountComboBoxComponent,
    AccountSelectComponent,
  ],
  entryComponents: [
    AccountCreatorDialog,
    AdmissionChargeDialog,
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
