import {ModuleWithProviders, NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../../../app.routes';

import {CovalentCoreModule} from '@covalent/core';

import {CommonService, IdentityService} from '../../../../services';
import {FinancialaidService} from '../../../../services/financialaid.service';
import {PromoCodeEffects} from './promo-code.effect';
import {EffectsModule} from '@ngrx/effects';
import {PromoCodeActions} from './promo-code.action';
import {PromoCodeCenterPage} from './promo-code-center.page';
import {PromoCodeListComponent} from './component/promo-code-list.component';
import {PromoCodeDetailPage} from './promo-code-detail.page';
import {PromoCodeComponent} from './component/promo-code.component';
import {PromoCodeItemListComponent} from './component/promo-code-item-list.component';
import {PromoCodeCreatorDialog} from './dialog/promo-code-creator.dialog';
import {PromoCodeItemEditorDialog} from './dialog/promo-code-item-editor.dialog';
import {AccountSubModule} from '../../account/accounts/index';


@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
    EffectsModule.run(PromoCodeEffects),
    AccountSubModule.forRoot(),
  ],
  declarations: [
    // page
    PromoCodeCenterPage,
    PromoCodeDetailPage,

    // component
    PromoCodeListComponent,
    PromoCodeComponent,
    PromoCodeItemListComponent,

    PromoCodeCreatorDialog,
    PromoCodeItemEditorDialog,
  ],
  exports: [],
  entryComponents: [
    PromoCodeCreatorDialog,
    PromoCodeItemEditorDialog,
  ],

})
export class PromoCodeSubModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: PromoCodeSubModule,
      providers: [
        appRoutingProviders,
        IdentityService,
        CommonService,
        FinancialaidService,
        PromoCodeActions,
      ],
    };
  }
}
