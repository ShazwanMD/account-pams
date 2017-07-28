import {ModuleWithProviders, NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../../../app.routes';
import {EffectsModule} from '@ngrx/effects';

import {CovalentCoreModule} from '@covalent/core';

import {CommonService, IdentityService} from '../../../../services';
import {AccountService} from '../../../../services/account.service';
import {IdentityModule} from '../../identity/index';
import {ChargeCodeSelectComponent} from './component/charge-code-select.component';
import {ChargeCodeCreatorDialog} from './dialog/charge-code-creator.dialog';
import {ChargeCodeEffects} from './charge-code.effect';
import {ChargeCodeActions} from './charge-code.action';
import {ChargeCodeCenterPage} from './charge-code-center.page';
import {ChargeCodeListComponent} from './component/charge-code-list.component';
import {ChargeCodeEditorDialog} from './dialog/charge-code-editor.dialog';
import {CommonModule} from '../../../common/index';

@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
    IdentityModule.forRoot(),
    CommonModule.forRoot(),
    EffectsModule.run(ChargeCodeEffects),
  ],
  declarations: [
    //page
    ChargeCodeCenterPage,

    //component
    ChargeCodeListComponent,
    ChargeCodeSelectComponent,

    // dialog
    ChargeCodeCreatorDialog,
    ChargeCodeEditorDialog,

  ],
  exports: [
    ChargeCodeSelectComponent,
  ],
  entryComponents: [
    ChargeCodeCreatorDialog,
    ChargeCodeEditorDialog,
  ],

})
export class ChargeCodeSubModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: ChargeCodeSubModule,
      providers: [
        appRoutingProviders,
        IdentityService,
        CommonService,
        AccountService,
        ChargeCodeActions,
      ],
    };
  }
}
