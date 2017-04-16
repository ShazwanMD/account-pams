import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../app.routes';
import {environment} from '../../environments/environment';

import {CovalentCoreModule} from '@covalent/core';

import {CommonService} from '../../services';
import {IdentityService} from '../../services';

import {MarketingPage} from "./marketing.page";
import {MarketingService} from "../../services/marketing.service";
import {ActionReducer, combineReducers} from "@ngrx/store";
import {NgModule, ModuleWithProviders} from "@angular/core";
import {PromoCodeSubModule} from "./promo-codes/index";

export interface MarketingModuleState {
  // promoCodeTasks: PromoCodeTaskListState;
  // promoCodeTask: PromoCodeTaskState;
  // promoCode: PromoCodeState;
};

// export const INITIAL_MARKETING_STATE: MarketingModuleState = <MarketingModuleState>{};
// const marketingModuleReducers = {
//    promoCodeTasks:promoCodeTaskListReducer,
//    promoCodeTask:promoCodeTaskReducer,
//    promoCodes:promoCodeReducer
// };

@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
    PromoCodeSubModule.forRoot(),
  ],
  declarations: [
    // page
    MarketingPage,
  ],
  exports: [],
})
export class MarketingModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: MarketingModule,
      providers: [
        appRoutingProviders,
        IdentityService,
        CommonService,
        MarketingService,
      ],
    };
  }
}
