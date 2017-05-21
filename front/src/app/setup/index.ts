import { CohortCodeEditorDialog } from './cohort-codes/dialog/cohort-code-editor.dialog';
import { CohortCodeListPage } from './cohort-codes/cohort-code-list.page';
import { CohortCode } from './../common/cohort-codes/cohort-code.interface';
import { CommonModuleState } from './../common/index';
import {CommonModule} from "../common/index";
import { BankCodeListPage } from './bank-codes/bank-code-list.page';
import { BankCode } from './../common/bank-codes/bank-code.interface';
import { AccountModule } from './../account/index';
import { BankCodeEditorDialog } from './bank-codes/dialog/bank-code-editor.dialog';
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
import {StudyModeCreatorDialog} from "./study-modes/dialog/study-mode-creator.dialog";
import {FacultyCodeCreatorDialog} from "./faculty-codes/dialog/faculty-code-creator.dialog";
import {CountryCodeCreatorDialog} from "./country-codes/dialog/country-code-creator.dialog";
import {StateCodeCreatorDialog} from "./state-codes/dialog/state-code-creator.dialog";
import {ProgramCodeCreatorDialog} from "./program-codes/dialog/program-code-creator.dialog";
import {CountryCodeListPage} from "./country-codes/country-code-list.page";
import {StateCodeListPage} from "./state-codes/state-code-list.page";
import {ProgramCodeListPage} from "./program-codes/program-code-list.page";
import {FacultyCodeListPage} from "./faculty-codes/faculty-code-list.page";
import {StudyModeListPage} from "./study-modes/study-mode-list.page";
import {stateCodeListReducer, StateCodeListState} from "./state-codes/state-code-list.reducer";
import {countryCodeListReducer, CountryCodeListState} from "./country-codes/country-code-list.reducer";
import {studyModeListReducer, StudyModeListState} from "./study-modes/study-mode-list.reducer";
import {FacultyCodeListState} from "./faculty-codes/faculty-code-list.reducer";
import {programCodeListReducer, ProgramCodeListState} from "./program-codes/program-code-list.reducer";
import {StudyMode} from "../common/study-modes/study-mode.interface";
import {CountryCode} from "../common/country-codes/country-code.interface";
import {StateCode} from "../common/state-codes/state-code.interface";
import {ProgramCode} from "../common/program-codes/program-code.interface";
import {bankCodeListReducer, BankCodeListState} from "./bank-codes/bank-code-list.reducer";
import {cohortCodeListReducer, CohortCodeListState} from "./cohort-codes/cohort-code-list.reducer";






export interface SetupModuleState {
  title:TitleState;
  bankCodes: BankCodeListState;
  cohortCodes: CohortCodeListState;

};

export const INITIAL_SETUP_STATE: SetupModuleState =
  <SetupModuleState>{
    title:'Setup Codes',
    bankCodes: <BankCode[]>[],
    cohortCodes: <CohortCode[]>[],
  };


export const setupModuleReducers = {
  title:titleReducer,
  bankCodes: bankCodeListReducer,
  cohortCodes: cohortCodeListReducer,
}


@NgModule({
  imports: [
    appRoutes,
    BrowserModule,
    ReactiveFormsModule,
    CovalentCoreModule.forRoot(),
    CommonModule.forRoot(),  
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
    CohortCodeListPage,

    // dialog
    BankCodeEditorDialog,
    CountryCodeCreatorDialog,
    StateCodeCreatorDialog,
    ProgramCodeCreatorDialog,
    FacultyCodeCreatorDialog,
    StudyModeCreatorDialog,
    CohortCodeEditorDialog,


  ],
  exports: [],
  entryComponents: [
    BankCodeEditorDialog,
    CountryCodeCreatorDialog,
    StateCodeCreatorDialog,
    ProgramCodeCreatorDialog,
    FacultyCodeCreatorDialog,
    StudyModeCreatorDialog,
    CohortCodeEditorDialog,

  ],

})
export class SetupModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: SetupModule,
      providers: [
        appRoutingProviders,
        SetupActions
      ],
    };
  }
}
