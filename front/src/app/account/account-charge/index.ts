import {NgModule, ModuleWithProviders} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../../app.routes';
import {EffectsModule} from "@ngrx/effects";

import {CovalentCoreModule} from '@covalent/core';

import {CommonService} from '../../../services';
import {IdentityService} from '../../../services';
import {AccountService} from "../../../services/account.service";
import {IdentityModule} from "../../identity/index";
//import {ChargeCodeSelectComponent} from "./component/charge-code-select.component";
//import {ChargeCodeCreatorDialog} from "./dialog/charge-code-creator.dialog";
//import {ChargeCodeEffects} from "./charge-code.effect";
import {AccountChargeActions} from "./account-charge.action";
import {AccountChargeCenterPage} from "./account-charge-center.page";
//import {ChargeCodeListComponent} from "./component/charge-code-list.component";
//import {ChargeCodeEditorDialog} from "./dialog/charge-code-editor.dialog";


@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
    IdentityModule.forRoot(),
   // EffectsModule.run(ChargeCodeEffects),
  ],
  declarations: [
    //page
    AccountChargeCenterPage,

    //component

    // dialog

  ],
  exports: [
  ],
  entryComponents: [
  ],

})
export class AccountChargeSubModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: AccountChargeSubModule,
      providers: [
        appRoutingProviders,
        IdentityService,
        CommonService,
        AccountService,
        AccountChargeActions,
      ],
    };
  }
}
