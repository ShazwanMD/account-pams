import {NgModule, ModuleWithProviders} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../../app.routes';

import {CovalentCoreModule} from '@covalent/core';

import {CommonService} from '../../../services';
import {IdentityService} from '../../../services';
import {FinancialaidService} from "../../../services/financialaid.service";
import {SettlementEffects} from "./settlement.effect";
import {EffectsModule} from "@ngrx/effects";
import {SettlementCenterPage} from "./settlement-center.page";
import {SettlementActions} from "./settlement.action";
import {SettlementListComponent} from "./component/settlement-list.component";


@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
    EffectsModule.run(SettlementEffects),
  ],
  declarations: [
    // page
    SettlementCenterPage,

    // component
    SettlementListComponent,
  ],
  exports: [],
  entryComponents: [
  ],

})
export class SettlementSubModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: SettlementSubModule,
      providers: [
        appRoutingProviders,
        IdentityService,
        CommonService,
        FinancialaidService,
        SettlementActions,
      ],
    };
  }
}
