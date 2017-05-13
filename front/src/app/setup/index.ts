import { SetupPage } from './setup.page';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../app.routes';
import {environment} from '../../environments/environment';
import {ActionReducer, combineReducers} from "@ngrx/store";
import {NgModule, ModuleWithProviders} from "@angular/core";

import {CovalentCoreModule} from '@covalent/core';
import {titleReducer, TitleState} from "./title.reducer";
import {FacultyCode} from "../common/faculty-codes/faculty-code.interface";
import {facultyCodeListReducer} from "../common/faculty-codes/faculty-code-list.reducer";
import {SetupEffects} from "./setup.effect";
import {EffectsModule} from "@ngrx/effects";
import {SetupActions} from "./setup.action";
import {BankCodeCreatorDialog} from "./bank-codes/dialog/bank-code-creator.dialog";
import {StudyModeCreatorDialog} from "./study-modes/dialog/study-mode-creator.dialog";
import {FacultyCodeCreatorDialog} from "./faculty-codes/dialog/faculty-code-creator.dialog";
import {CountryCodeCreatorDialog} from "./country-codes/dialog/country-code-creator.dialog";
import {StateCodeCreatorDialog} from "./state-codes/dialog/state-code-creator.dialog";
import {ProgramCodeCreatorDialog} from "./program-codes/dialog/program-code-creator.dialog";
import {BankCodeListPage} from "./bank-codes/bank-code-list.page";
import {CountryCodeListPage} from "./country-codes/country-code-list.page";
import {StateCodeListPage} from "./state-codes/state-code-list.page";
import {ProgramCodeListPage} from "./program-codes/program-code-list.page";
import {FacultyCodeListPage} from "./faculty-codes/faculty-code-list.page";
import {StudyModeListPage} from "./study-modes/study-mode-list.page";
import {stateCodeListReducer, StateCodeListState} from "./state-codes/state-code-list.reducer";
import {countryCodeListReducer, CountryCodeListState} from "./country-codes/country-code-list.reducer";
import {bankCodeListReducer, BankCodeListState} from "./bank-codes/bank-code-list.reducer";
import {studyModeListReducer, StudyModeListState} from "./study-modes/study-mode-list.reducer";
import {FacultyCodeListState} from "./faculty-codes/faculty-code-list.reducer";
import {programCodeListReducer, ProgramCodeListState} from "./program-codes/program-code-list.reducer";
import {StudyMode} from "../common/study-modes/study-mode.interface";
import {CountryCode} from "../common/country-codes/country-code.interface";
import {StateCode} from "../common/state-codes/state-code.interface";
import {ProgramCode} from "../common/program-codes/program-code.interface";
import {BankCode} from "../common/bank-codes/bank-code.interface";

export interface SetupModuleState {
  title:TitleState;
  bankCodes: BankCodeListState;
  countryCodes : CountryCodeListState;
  stateCodes : StateCodeListState;
  programCodes : ProgramCodeListState;
  facultyCodes : FacultyCodeListState;
  studyModes : StudyModeListState;
};

export const INITIAL_SETUP_STATE: SetupModuleState =
  <SetupModuleState>{
    title:'Setup Codes',
    bankCodes: <BankCode[]>[],
    programCodes: <ProgramCode[]>[],
    stateCodes: <StateCode[]>[],
    countryCodes: <CountryCode[]>[],
    facultyCodes: <FacultyCode[]>[],
    studyModes: <StudyMode[]>[],
  };


export const setupModuleReducers = {
  title:titleReducer,
  bankCodes: bankCodeListReducer,
  countryCodes: countryCodeListReducer,
  stateCodes: stateCodeListReducer,
  programCodes: programCodeListReducer,
  facultyCodes: facultyCodeListReducer,
  studyModes: studyModeListReducer,
}


@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
    EffectsModule.run(SetupEffects),
  ],
  declarations: [
    SetupPage,
    BankCodeListPage,
    CountryCodeListPage,
    StateCodeListPage,
    ProgramCodeListPage,
    FacultyCodeListPage,
    StudyModeListPage,

    // dialog
    BankCodeCreatorDialog,
    CountryCodeCreatorDialog,
    StateCodeCreatorDialog,
    ProgramCodeCreatorDialog,
    FacultyCodeCreatorDialog,
    StudyModeCreatorDialog,


  ],
  exports: [],
  entryComponents: [
    BankCodeCreatorDialog,
    CountryCodeCreatorDialog,
    StateCodeCreatorDialog,
    ProgramCodeCreatorDialog,
    FacultyCodeCreatorDialog,
    StudyModeCreatorDialog,

  ],

})
export class SetupModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: SetupModule,
      providers: [
        appRoutingProviders,
        SetupActions,
      ],
    };
  }
}
