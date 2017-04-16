import {NgModule, ModuleWithProviders} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../../app.routes';

import {CovalentCoreModule} from '@covalent/core';

import {CommonService} from '../../../services';
import {IdentityService} from '../../../services';
import {FinancialaidService} from "../../../services/financialaid.service";


@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
    // EffectsModule.run(SettlementEffects),
  ],
  declarations: [
    // page
    //SettlementCenterPage,
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
        // SettlementActions,
      ],
    };
  }
}
