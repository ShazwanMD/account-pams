import {NgModule, ModuleWithProviders} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../../app.routes';
import {environment} from '../../../environments/environment';

import {CovalentCoreModule} from '@covalent/core';

import {CommonService} from '../../../services';
import {IdentityService} from '../../../services';
import {BillingService} from "../../../services/billing.service";


@NgModule({
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
    appRoutes,
  ],
  declarations: [
    // page, component, dialog
  ],
  exports: [],
  entryComponents: [
    // dialog
  ],

})
export class InvoiceModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: InvoiceModule,
      providers: [
        appRoutingProviders,
        IdentityService,
        CommonService,
        BillingService,
      ],
    };
  }
}
