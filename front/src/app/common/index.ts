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
import {ProgramCodeListState, programCodeListReducer} from "./program-codes/program-code-list.reducer";
import {StudyModeListState, studyModeListReducer} from "./study-modes/study-mode-list.reducer";
import {FacultyCodeListState, facultyCodeListReducer} from "./faculty-codes/faculty-code-list.reducer";
import {GenderCodeListState, genderCodeListReducer} from "./gender-codes/gender-code-list.reducer";
import {SupervisorCodeListState, supervisorCodeListReducer} from "./supervisor-codes/supervisor-code-list.reducer";
import {GraduateCentreListState, graduateCentreListReducer} from "./graduate-centres/graduate-centre-list.reducer";
import {GraduateCentreSelectComponent} from "./graduate-centres/component/graduate-centre-select.component";
import {ProgramCodeSelectComponent} from "./program-codes/component/program-code-select.component";
import {StudyModeCodeSelectComponent} from "./study-modes/component/study-mode-select.component";
import {FacultyCodeSelectComponent} from "./faculty-codes/component/faculty-code-select.component";
import {GenderCodeSelectComponent} from "./gender-codes/component/gender-code-select.component";
import { maritalCodeListReducer, MaritalCodeListState } from "./marital-codes/marital-code-list.reducer";
import { MaritalCodeSelectComponent } from "./marital-codes/component/marital-code-select.component";

export interface CommonModuleState {
  programCodes: ProgramCodeListState;
  studyModes: StudyModeListState;
  supervisorCodes: SupervisorCodeListState;
  graduateCentres: GraduateCentreListState;
  facultyCodes: FacultyCodeListState;
  genderCodes: GenderCodeListState;
  maritalCodes: MaritalCodeListState;
}
;

export const INITIAL_COMMON_STATE: CommonModuleState = <CommonModuleState>{};
export const commonModuleReducers = {
  programCodes: programCodeListReducer,
  studyModes: studyModeListReducer,
  supervisorCodes: supervisorCodeListReducer,
  graduateCentres: graduateCentreListReducer,
  facultyCodes: facultyCodeListReducer,
  genderCodes: genderCodeListReducer,
  maritalCodes: maritalCodeListReducer,
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
    GraduateCentreSelectComponent,
    ProgramCodeSelectComponent,
    StudyModeCodeSelectComponent,
    FacultyCodeSelectComponent,
    GenderCodeSelectComponent,
    MaritalCodeSelectComponent,
  ],
  exports: [
    GraduateCentreSelectComponent,
    ProgramCodeSelectComponent,
    StudyModeCodeSelectComponent,
    FacultyCodeSelectComponent,
    GenderCodeSelectComponent,
    MaritalCodeSelectComponent,
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
