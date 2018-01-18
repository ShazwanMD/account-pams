import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../../app.routes';
import {CovalentCoreModule} from '@covalent/core';
import {CommonService, IdentityService} from '../../../services';
import {ListingPage} from './listing.page';
import {ModuleWithProviders, NgModule} from '@angular/core';
import { ListingInvoiceSubModule } from './listing-invoice/index';
import { ListingReceiptSubModule } from './listing-receipt/index';
import { ListingDebitNoteSubModule } from './listing-debit-note/index';
import { ListingCreditNoteSubModule } from './listing-credit-note/index';
import { ListingRefundPaymentSubModule } from './listing-refund-payment/index';
import { ListingKnockoffPaymentSubModule } from './listing-knockoff-payment/index';
import { AgeingModuleRoutes } from './ageing/ageing-module.routes';
import { AgeingSubModule } from './ageing/index';
import { DebtorsSubModule } from './debtors/index';

export interface ListingModuleState {
}

@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
    ListingInvoiceSubModule.forRoot(),
    ListingReceiptSubModule.forRoot(),
    ListingDebitNoteSubModule.forRoot(),
    ListingCreditNoteSubModule.forRoot(),
    ListingRefundPaymentSubModule.forRoot(),
    ListingKnockoffPaymentSubModule.forRoot(),
    AgeingSubModule.forRoot(),
    DebtorsSubModule.forRoot(),
  ],
  declarations: [
    // page
    ListingPage,
  ],
  exports: [],
})
export class ListingModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: ListingModule,
      providers: [
        appRoutingProviders,
        IdentityService,
        CommonService,
      ],
    };
  }
}
