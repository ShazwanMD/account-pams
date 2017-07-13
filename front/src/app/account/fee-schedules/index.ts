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
import {CommonModule} from "../../common/index";
import {FeeScheduleComponent} from "./component/fee-schedule.component";
import {FeeScheduleDetailPage} from "./fee-schedule-detail.page";
import {FeeScheduleItemListComponent} from "./component/fee-schedule-item-list.component";
import {FeeScheduleListComponent} from "./component/fee-schedule-list.component";
import { FeeScheduleStatusComponent } from "./component/fee-schedule-status.component";


@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
    CommonModule.forRoot(),
    IdentityModule.forRoot(),
    ChargeCodeSubModule.forRoot(),
    EffectsModule.run(FeeScheduleEffects),
  ],
  declarations: [
    //page
    FeeScheduleCenterPage,
    FeeScheduleDetailPage,

    // dialog
    FeeScheduleCreatorDialog,
    FeeScheduleItemEditorDialog,

    // component
    FeeScheduleComponent,
    FeeScheduleListComponent,
    FeeScheduleItemListComponent,
    FeeScheduleStatusComponent,
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
