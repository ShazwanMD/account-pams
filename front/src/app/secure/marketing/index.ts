import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../../app.routes';

import {CovalentCoreModule} from '@covalent/core';

import {CommonService, IdentityService} from '../../../services';

import {MarketingPage} from './marketing.page';
import {MarketingService} from '../../../services/marketing.service';
import {ModuleWithProviders, NgModule} from '@angular/core';
import {PromoCodeSubModule} from './promo-codes/index';
import {promoCodeListReducer, PromoCodeListState} from './promo-codes/promo-code-list.reducer';
import {promoCodeReducer, PromoCodeState} from './promo-codes/promo-code.reducer';
import {promoCodeItemListReducer, PromoCodeItemListState} from './promo-codes/promo-code-item-list.reducer';

export interface MarketingModuleState {
  promoCodes: PromoCodeListState;
  promoCode: PromoCodeState;
  promoCodeItems: PromoCodeItemListState;
}

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
