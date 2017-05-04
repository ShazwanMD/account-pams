import {NgModule, ModuleWithProviders} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../../app.routes';
import {EffectsModule} from "@ngrx/effects";
import {CovalentCoreModule} from '@covalent/core';
import {WaiverApplicationCenterPage} from "./waiver-application-center.page";
import {CommonService} from '../../../services';
import {IdentityService} from '../../../services';
import {FinancialaidService} from "../../../services/financialaid.service";
import {WaiverApplicationEffects} from "./waiver-application.effect";
import {WaiverApplicationActions} from "./waiver-application.action";

@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
    EffectsModule.run(WaiverApplicationEffects),
  ],
  declarations: [
    // page
    WaiverApplicationCenterPage,
  ],
  exports: [],
  entryComponents: [
  ],

})
export class WaiverApplicationSubModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: WaiverApplicationSubModule,
      providers: [
        appRoutingProviders,
        IdentityService,
        CommonService,
        FinancialaidService,
        WaiverApplicationActions,
      ],
    };
  }
}
