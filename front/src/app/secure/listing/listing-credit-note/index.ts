import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../../../app.routes';
import {CovalentCoreModule} from '@covalent/core';
import {CommonService, IdentityService} from '../../../../services';
import {ListingCreditNoteCenterPage} from './listing-credit-note-center.page';
import {ModuleWithProviders, NgModule} from '@angular/core';
import { AccountSubModule } from '../../account/accounts/index';

@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
    AccountSubModule.forRoot(),
  ],
  declarations: [
    // page
    ListingCreditNoteCenterPage,
  ],
  exports: [],
  entryComponents: [
  ],
})
export class ListingCreditNoteSubModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: ListingCreditNoteSubModule,
      providers: [
        appRoutingProviders,
        IdentityService,
        CommonService,
      ],
    };
  }
}
