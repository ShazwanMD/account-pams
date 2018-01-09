import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../../../app.routes';
import {CovalentCoreModule} from '@covalent/core';
import {CommonService, IdentityService} from '../../../../services';
import {ListingAdvancePaymentStatementCenterPage} from './listing-advance-payment-statement-center.page';
import {ModuleWithProviders, NgModule} from '@angular/core';
import { AccountSubModule } from '../../account/accounts/index';
import { CommonModule } from '../../../common/index';

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
    ListingAdvancePaymentStatementCenterPage,
  ],
  exports: [],
  entryComponents: [
  ],
})
export class ListingAdvancePaymentStatementSubModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: ListingAdvancePaymentStatementSubModule,
      providers: [
        appRoutingProviders,
        IdentityService,
        CommonService,
      ],
    };
  }
}
