import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../../app.routes';
import {CovalentCoreModule} from '@covalent/core';
import {CommonService, IdentityService} from '../../../services';
import {ListingPage} from './listing.page';
import {ModuleWithProviders, NgModule} from '@angular/core';

export interface ListingModuleState {
}

@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
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
