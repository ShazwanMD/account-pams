import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../app.routes';
import {environment} from '../../environments/environment';

import {CovalentCoreModule} from '@covalent/core';

import {CommonService} from '../../services';
import {IdentityService} from '../../services';

import {MarketingPage} from './marketing.page';
import {MarketingService} from '../../services/marketing.service';
import {NgModule, ModuleWithProviders} from '@angular/core';
import {PromoCodeSubModule} from './promo-codes/index';
import {PromoCodeListState, promoCodeListReducer} from './promo-codes/promo-code-list.reducer';
import {PromoCodeState, promoCodeReducer} from './promo-codes/promo-code.reducer';
import {PromoCodeItemListState, promoCodeItemListReducer} from './promo-codes/promo-code-item-list.reducer';

export interface MarketingModuleState {
  promoCodes: PromoCodeListState;
  promoCode: PromoCodeState;
  promoCodeItems: PromoCodeItemListState;
};

export const INITIAL_MARKETING_STATE: MarketingModuleState = <MarketingModuleState>{};
export const marketingModuleReducers = {
   promoCodes: promoCodeListReducer,
   promoCode: promoCodeReducer,
   promoCodeItems: promoCodeItemListReducer,
};

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
