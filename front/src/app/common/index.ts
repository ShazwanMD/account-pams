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
import {studyCenterCodeListReducer, StudyCenterCodeListState} from './study-center-codes/study-center-code-list.reducer';
import {StudyCenterCodeSelectComponent} from './study-center-codes/component/study-center-code-select.component';
import { SecurityChargeCodeSelectComponent } from "./security-charges-code/component/security-charge-code-select.component";
import { SetupModule } from "../secure/setup/index";
import { ProgramCodeListState, programCodeListReducer } from './program-codes/program-code-list.reducer';
import { ProgramLevelListState, programLevelListReducer } from './program-levels/program-level-list.reducer';
import { ProgramLevelSelectComponent } from './program-levels/component/program-level-select.component';
import { ProgramCodeSelectComponent } from './program-codes/component/program-code-select.component';
import { DebitNoteStatusTypeSelectComponent } from './status/component/debit-note-status-type-select.component';

export interface CommonModuleState {
  cohortCodes: CohortCodeListState;
  programCodes: ProgramCodeListState;
  programLevels: ProgramLevelListState;
  facultyCodes: CohortCodeListState;
  studyModes: StudyModeListState;
  residencyCodes: ResidencyCodeListState;
  taxCodes: TaxCodeListState;
  studyCenterCodes: StudyCenterCodeListState;
}

export const INITIAL_COMMON_STATE: CommonModuleState =
  <CommonModuleState>{
    cohortCodes: [],
    programCodes: [],
    programLevels: [],
    facultyCodes: [],
    studyModes: [],
    residencyCodes: [],
    taxCodes: [],
    studyCenterCodes: [],
  };

export const commonModuleReducers = {
  cohortCodes: cohortCodeListReducer,
  programCodes: programCodeListReducer,
  programLevels: programLevelListReducer,
  facultyCodes: facultyCodeListReducer,
  studyModes: studyModeListReducer,
  residencyCodes: residencyCodeListReducer,
  taxCodes: taxCodeListReducer,
  studyCenterCodes: studyCenterCodeListReducer,
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
    ProgramCodeSelectComponent,
    ProgramLevelSelectComponent,
    FacultyCodeSelectComponent,
    StudyModeSelectComponent,
    ResidencyCodeSelectComponent,
    TaxCodeSelectComponent,
    SecurityChargeCodeSelectComponent,
    StudyCenterCodeSelectComponent,
    DebitNoteStatusTypeSelectComponent,
  ],
  exports: [
    CohortCodeSelectComponent,
    ProgramCodeSelectComponent,
    ProgramLevelSelectComponent,
    FacultyCodeSelectComponent,
    StudyModeSelectComponent,
    ResidencyCodeSelectComponent,
    TaxCodeSelectComponent,
    SecurityChargeCodeSelectComponent,
    StudyCenterCodeSelectComponent,
    DebitNoteStatusTypeSelectComponent,
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
