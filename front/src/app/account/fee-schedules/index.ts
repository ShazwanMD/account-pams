import {NgModule, ModuleWithProviders} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../../app.routes';
import {environment} from '../../../environments/environment';
import {EffectsModule} from "@ngrx/effects";

import {CovalentCoreModule} from '@covalent/core';

import {CommonService} from '../../../services';
import {IdentityService} from '../../../services';
import {AccountService} from "../../../services/account.service";
import {IdentityModule} from "../../identity/index";
import {FeeScheduleEffects} from "./fee-schedule.effect";
import {FeeScheduleActions} from "./fee-schedule.action";
import {FeeScheduleCenterPage} from "./fee-schedule-center.page";
import {FeeScheduleCreatorDialog} from "./dialog/fee-schedule-creator.dialog";
import {ChargeCodeSubModule} from "../charge-codes/index";
import {FeeScheduleItemEditorDialog} from "./dialog/fee-schedule-item-editor.dialog";


@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
    IdentityModule.forRoot(),
    ChargeCodeSubModule.forRoot(),
    EffectsModule.run(FeeScheduleEffects),
  ],
  declarations: [
    //page
    FeeScheduleCenterPage,

    // dialog
    FeeScheduleCreatorDialog,
    FeeScheduleItemEditorDialog,
  ],
  exports: [],
  entryComponents: [
    FeeScheduleCreatorDialog,
    FeeScheduleItemEditorDialog,
  ],

})
export class FeeScheduleSubModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: FeeScheduleSubModule,
      providers: [
        appRoutingProviders,
        IdentityService,
        CommonService,
        AccountService,
        FeeScheduleActions,
      ],
    };
  }
}
