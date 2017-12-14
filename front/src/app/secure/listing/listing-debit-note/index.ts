import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../../../app.routes';
import {CovalentCoreModule} from '@covalent/core';
import {CommonService, IdentityService} from '../../../../services';
import {ListingDebitNoteCenterPage} from './listing-debit-note-center.page';
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
    ListingDebitNoteCenterPage,
  ],
  exports: [],
  entryComponents: [
  ],
})
export class ListingDebitNoteSubModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: ListingDebitNoteSubModule,
      providers: [
        appRoutingProviders,
        IdentityService,
        CommonService,
      ],
    };
  }
}
