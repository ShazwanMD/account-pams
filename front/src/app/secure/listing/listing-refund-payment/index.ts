import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../../../app.routes';
import {CovalentCoreModule} from '@covalent/core';
import {CommonService, IdentityService} from '../../../../services';
import {ModuleWithProviders, NgModule} from '@angular/core';
import { AccountSubModule } from '../../account/accounts/index';
import { CommonModule } from '../../../common/index';
import { ListingRefundPaymentCenterPage } from './listing-refund-payment-center.page';

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
    ListingRefundPaymentCenterPage,
  ],
  exports: [],
  entryComponents: [
  ],
})
export class ListingRefundPaymentSubModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: ListingRefundPaymentSubModule,
      providers: [
        appRoutingProviders,
        IdentityService,
        CommonService,
      ],
    };
  }
}
