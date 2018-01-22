import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../../../app.routes';
import {CovalentCoreModule} from '@covalent/core';
import {CommonService, IdentityService} from '../../../../services';
import {ModuleWithProviders, NgModule} from '@angular/core';
import { AccountSubModule } from '../../account/accounts/index';
import { CommonModule } from '../../../common/index';
import { DebtorsCenterPage } from './debtors-center.page';

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
    DebtorsCenterPage,
  ],
  exports: [],
  entryComponents: [
  ],
})
export class DebtorsSubModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: DebtorsSubModule,
      providers: [
        appRoutingProviders,
        IdentityService,
        CommonService,
      ],
    };
  }
}
