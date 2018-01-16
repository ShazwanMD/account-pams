import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../../../app.routes';
import {CovalentCoreModule} from '@covalent/core';
import {CommonService, IdentityService} from '../../../../services';
import {ModuleWithProviders, NgModule} from '@angular/core';
import { AccountSubModule } from '../../account/accounts/index';
import { CommonModule } from '../../../common/index';
import { ListingKnockoffPaymentCenterPage } from './listing-knockoff-payment-center.page';

@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
    AccountSubModule.forRoot(),
    CommonModule.forRoot(),
  ],
  declarations: [
    // page
    ListingKnockoffPaymentCenterPage,
  ],
  exports: [],
  entryComponents: [
  ],
})
export class ListingKnockoffPaymentSubModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: ListingKnockoffPaymentSubModule,
      providers: [
        appRoutingProviders,
        IdentityService,
        CommonService,
      ],
    };
  }
}
