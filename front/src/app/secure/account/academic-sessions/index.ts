import {NgModule, ModuleWithProviders} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../../../app.routes';
import {EffectsModule} from '@ngrx/effects';

import {CovalentCoreModule} from '@covalent/core';

import {CommonService} from '../../../../services';
import {IdentityService} from '../../../../services';
import {AccountService} from '../../../../services/account.service';
import {AcademicSessionSelectComponent} from './component/academic-session-select.component';
import {AcademicSessionActions} from './component/academic-session.action';
import {AcademicSessionEffects} from './component/academic-session.effect';

@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
    EffectsModule.run(AcademicSessionEffects),
  ],
  declarations: [
    // page

    // component
    AcademicSessionSelectComponent,

  ],
  exports: [
    AcademicSessionSelectComponent,
  ],
  entryComponents: [],

})
export class AcademicSessionSubModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: AcademicSessionSubModule,
      providers: [
        appRoutingProviders,
        IdentityService,
        CommonService,
        AccountService,
        AcademicSessionActions,
      ],
    };
  }
}
