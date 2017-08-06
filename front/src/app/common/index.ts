import {ModuleWithProviders, NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../app.routes';

import {CovalentCoreModule} from '@covalent/core';

import {CommonService, IdentityService} from '../../services';

import {EffectsModule} from '@ngrx/effects';
import {CommonEffects} from './common.effect';
import {CommonActions} from './common.action';
import {cohortCodeListReducer, CohortCodeListState} from './cohort-codes/cohort-code-list.reducer';
import {CohortCodeSelectComponent} from './cohort-codes/component/cohort-code-select.component';
import {facultyCodeListReducer} from './faculty-codes/faculty-code-list.reducer';
import {FacultyCodeSelectComponent} from './faculty-codes/component/faculty-code-select.component';
import {StudyModeSelectComponent} from './study-modes/component/study-mode-select.component';
import {studyModeListReducer, StudyModeListState} from '../secure/setup/study-modes/study-mode-list.reducer';
import {residencyCodeListReducer, ResidencyCodeListState} from './residency-codes/residency-code-list.reducer';
import {ResidencyCodeSelectComponent} from './residency-codes/component/residency-code-select.component';
import {taxCodeListReducer, TaxCodeListState} from './tax-codes/tax-code-list.reducer';
import {TaxCodeSelectComponent} from './tax-codes/component/tax-code-select.component';
import { SecurityChargeCodeSelectComponent } from "./security-charges-code/component/security-charge-code-select.component";
import { SetupModule } from "../secure/setup/index";

export interface CommonModuleState {
  cohortCodes: CohortCodeListState;
  facultyCodes: CohortCodeListState;
  studyModes: StudyModeListState;
  residencyCodes: ResidencyCodeListState;
  taxCodes: TaxCodeListState;
}

export const INITIAL_COMMON_STATE: CommonModuleState =
  <CommonModuleState>{
    cohortCodes: [],
    facultyCodes: [],
    studyModes: [],
    residencyCodes: [],
    taxCodes: [],
  };

export const commonModuleReducers = {
  cohortCodes: cohortCodeListReducer,
  facultyCodes: facultyCodeListReducer,
  studyModes: studyModeListReducer,
  residencyCodes: residencyCodeListReducer,
  taxCodes: taxCodeListReducer,
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
    StudyModeSelectComponent,
    ResidencyCodeSelectComponent,
    TaxCodeSelectComponent,
    SecurityChargeCodeSelectComponent,
  ],
  exports: [
    CohortCodeSelectComponent,
    FacultyCodeSelectComponent,
    StudyModeSelectComponent,
    ResidencyCodeSelectComponent,
    TaxCodeSelectComponent,
    SecurityChargeCodeSelectComponent,
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
        CommonActions,
      ],
    };
  }
}
