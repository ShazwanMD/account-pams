import {ModuleWithProviders, NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {CovalentCoreModule} from '@covalent/core';
import {appRoutes, appRoutingProviders} from '../../../app.routes';
import {IdentityService} from '../../../../services/identity.service';
import {CommonService} from '../../../../services/common.service';
import {BillingService} from '../../../../services/billing.service';

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
