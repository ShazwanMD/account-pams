import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../../../app.routes';
import {CovalentCoreModule} from '@covalent/core';
import {CommonService, IdentityService} from '../../../../services';
import {ListingInvoiceCenterPage} from './listing-invoice-center.page';
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
    ListingInvoiceCenterPage,
  ],
  exports: [],
  entryComponents: [
  ],
})
export class ListingInvoiceSubModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: ListingInvoiceSubModule,
      providers: [
        appRoutingProviders,
        IdentityService,
        CommonService,
      ],
    };
  }
}
