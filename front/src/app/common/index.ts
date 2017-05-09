import {NgModule, ModuleWithProviders} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../app.routes';

import {CovalentCoreModule} from '@covalent/core';

import {CommonService} from '../../services';
import {IdentityService} from '../../services';

import {EffectsModule} from "@ngrx/effects";
import {CommonEffects} from "./common.effect";
import {CommonActions} from "./common.action";
import {cohortCodeListReducer, CohortCodeListState} from "./cohort-codes/cohort-code-list.reducer";
import {CohortCodeSelectComponent} from "./cohort-codes/component/cohort-code-select.component";
import {facultyCodeListReducer, FacultyCodeListState} from "./faculty-codes/faculty-code-list.reducer";
import {FacultyCodeSelectComponent} from "./faculty-codes/component/faculty-code-select.component";

export interface CommonModuleState {
  cohortCodes: CohortCodeListState;
  facultyCodes: CohortCodeListState;
}
;

export const INITIAL_COMMON_STATE: CommonModuleState = <CommonModuleState>{};
export const commonModuleReducers = {
  cohortCodes: cohortCodeListReducer,
  facultyCodes: facultyCodeListReducer,
};

@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
    EffectsModule.run(CommonEffects),
  ],
  declarations: [
    CohortCodeSelectComponent,
    FacultyCodeSelectComponent,
  ],
  exports: [
    CohortCodeSelectComponent,
    FacultyCodeSelectComponent,
  ],
})
export class CommonModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: CommonModule,
      providers: [
        appRoutingProviders,
        IdentityService,
        CommonService,
        CommonActions
      ],
    };
  }
}
