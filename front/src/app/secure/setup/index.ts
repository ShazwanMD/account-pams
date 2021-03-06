import { ResidencyCodesComponent } from './residency-codes/component/residency-code';
import { ProgramCodesComponent } from './program-codes/component/program-code';
import { TaxCodesComponent } from './tax-codes/component/tax-code';
import { FacultyCodesComponent } from './faculty-codes/component/faculty-code';
import { CohortCodesComponent } from './cohort-codes/component/cohort-code';
import { BankCodesComponent } from './bank-codes/component/bank-code';
import {SecurityChargeCodeListPage} from './security-charge-codes/security-charge-code-list.page';
import {SecurityChargeCode} from '../../shared/model/common/security-charge-code.interface';
import {CohortCodeEditorDialog} from './cohort-codes/dialog/cohort-code-editor.dialog';
import {CohortCodeListPage} from './cohort-codes/cohort-code-list.page';
import {CohortCode} from '../../shared/model/common/cohort-code.interface';
import {CommonModule} from '../../common/index';
import {BankCodeListPage} from './bank-codes/bank-code-list.page';
import {BankCode} from '../../shared/model/common/bank-code.interface';
import {BankCodeEditorDialog} from './bank-codes/dialog/bank-code-editor.dialog';
import {SetupPage} from './setup.page';
import {BrowserModule} from '@angular/platform-browser';
import {ReactiveFormsModule} from '@angular/forms';
import {appRoutes, appRoutingProviders} from '../../app.routes';
import {ModuleWithProviders, NgModule} from '@angular/core';
import {CovalentCoreModule} from '@covalent/core';
import {titleReducer, TitleState} from './title.reducer';
import {SetupEffects} from './setup.effect';
import {EffectsModule} from '@ngrx/effects';
import {SetupActions} from './setup.action';
import {StudyModeCreatorDialog} from './study-modes/dialog/study-mode-creator.dialog';
import {FacultyCodeEditorDialog} from './faculty-codes/dialog/faculty-code-editor.dialog';
import {CountryCodeCreatorDialog} from './country-codes/dialog/country-code-creator.dialog';
import {StateCodeCreatorDialog} from './state-codes/dialog/state-code-creator.dialog';
import {ProgramCodeEditorDialog} from './program-codes/dialog/program-code-editor.dialog';
import {CountryCodeListPage} from './country-codes/country-code-list.page';
import {StateCodeListPage} from './state-codes/state-code-list.page';
import {ProgramCodeListPage} from './program-codes/program-code-list.page';
import {FacultyCodeListPage} from './faculty-codes/faculty-code-list.page';
import {StudyModeListPage} from './study-modes/study-mode-list.page';
import {bankCodeListReducer, BankCodeListState} from './bank-codes/bank-code-list.reducer';
import {cohortCodeListReducer, CohortCodeListState} from './cohort-codes/cohort-code-list.reducer';
import {residencyCodeListReducer,ResidencyCodeListState} from '../../common/residency-codes/residency-code-list.reducer';
import {ResidencyCode} from '../../shared/model/common/residency-code.interface';
import {ResidencyCodeListPage} from './residency-codes/residency-code-list.page';
import {ResidencyCodeEditorDialog} from './residency-codes/dialog/residency-code-editor.dialog';
import {taxCodeListReducer, TaxCodeListState} from '../../common/tax-codes/tax-code-list.reducer';
import {TaxCode} from '../../shared/model/common/tax-code.interface';
import {TaxCodeListPage} from './tax-codes/tax-code-list.page';
import {TaxCodeEditorDialog} from './tax-codes/dialog/tax-code-editor.dialog';
import {securityChargeCodeByActiveListReducer,SecurityChargeCodeByActiveListState} from './security-charge-codes/security-charge-code-by-active-list.reducer';
import {SecurityChargeCodeEditorDialog} from './security-charge-codes/dialog/security-charge-code-editor.dialog';
import {securityChargeCodeListReducer,SecurityChargeCodeListState} from './security-charge-codes/security-charge-code-list.reducer';
import { FacultyCode } from "../../shared/model/common/faculty-code.interface";
import { facultyCodeListReducer, FacultyCodeListState } from "../../common/faculty-codes/faculty-code-list.reducer";
import { ProgramCode } from "../../shared/model/common/program-code.interface";
import { programCodeListReducer, ProgramCodeListState } from "../../common/program-codes/program-code-list.reducer";
import { SecurityChargeCodeComponent } from './security-charge-codes/component/security-charge-code';
import { TaxCodeByActiveListState, taxCodeByActiveListReducer } from './tax-codes/tax-code-by-active-list.reducer';

export interface SetupModuleState {
  title: TitleState;
  bankCodes: BankCodeListState;
  cohortCodes: CohortCodeListState;
  residencyCodes: ResidencyCodeListState;
  taxCodes: TaxCodeListState;
  securityChargeCodes: SecurityChargeCodeListState;
  securityChargeCodesByActive: SecurityChargeCodeByActiveListState;
  taxCodesByActive: TaxCodeByActiveListState;
  facultyCodes: FacultyCodeListState;
  programCodes: ProgramCodeListState;

}
;

export const INITIAL_SETUP_STATE: SetupModuleState =
  <SetupModuleState>{
    title: 'Setup Codes',
    bankCodes: <BankCode[]>[],
    cohortCodes: <CohortCode[]>[],
    facultyCodes: <FacultyCode[]>[],
    programCodes: <ProgramCode[]>[],
    residencyCodes: <ResidencyCode[]>[],
    taxCodes: <TaxCode[]>[],
    taxCodesByActive: <TaxCode[]>[],
    securityChargeCodes: <SecurityChargeCode[]>[],
    securityChargeCodesByActive: <SecurityChargeCode[]>[],
  };


export const setupModuleReducers = {
  title: titleReducer,
  bankCodes: bankCodeListReducer,
  cohortCodes: cohortCodeListReducer,
  facultyCodes: facultyCodeListReducer,
  programCodes: programCodeListReducer,
  residencyCodes: residencyCodeListReducer,
  taxCodes: taxCodeListReducer,
  taxCodesByActive: taxCodeByActiveListReducer,
  securityChargeCodes: securityChargeCodeListReducer,
  securityChargeCodesByActive: securityChargeCodeByActiveListReducer,
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
    ResidencyCodeListPage,
    TaxCodeListPage,
    SecurityChargeCodeListPage,

    // dialog
    BankCodeEditorDialog,
    CountryCodeCreatorDialog,
    StateCodeCreatorDialog,
    ProgramCodeEditorDialog,
    FacultyCodeEditorDialog,
    StudyModeCreatorDialog,
    CohortCodeEditorDialog,
    ResidencyCodeEditorDialog,
    TaxCodeEditorDialog,
    SecurityChargeCodeEditorDialog,

    // component
    BankCodesComponent,
    SecurityChargeCodeComponent,
    CohortCodesComponent,
    FacultyCodesComponent,
    ProgramCodesComponent,
    ResidencyCodesComponent,
    TaxCodesComponent,


  ],
  exports: [],
  entryComponents: [
    BankCodeEditorDialog,
    CountryCodeCreatorDialog,
    StateCodeCreatorDialog,
    ProgramCodeEditorDialog,
    FacultyCodeEditorDialog,
    StudyModeCreatorDialog,
    CohortCodeEditorDialog,
    ResidencyCodeEditorDialog,
    TaxCodeEditorDialog,
    SecurityChargeCodeEditorDialog,

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
