import {Injectable} from '@angular/core';
import {Actions, Effect} from '@ngrx/effects';
import {SetupActions} from './setup.action';
import {CommonService} from '../../../services/common.service';
import {from} from 'rxjs/observable/from';
import {SetupModuleState} from './index';
import {Store} from '@ngrx/store';

@Injectable()
export class SetupEffects {
  constructor(private actions$: Actions,
              private setupActions: SetupActions,
              private commonService: CommonService,
              private store: Store<SetupModuleState>) {
  }

  @Effect() changeTitle$ = this.actions$
    .ofType(SetupActions.CHANGE_TITLE)
    .map(action => action.payload)
    .map(payload => this.setupActions.changeTitleSuccess(payload));

  @Effect() findBankCodes$ = this.actions$
    .ofType(SetupActions.FIND_BANK_CODES)
    .map(action => action.payload)
    .switchMap(() => this.commonService.findBankCodes())
    .map(codes => this.setupActions.findBankCodesSuccess(codes));

  @Effect() saveBankCodes$ = this.actions$
    .ofType(SetupActions.SAVE_BANK_CODE)
    .map(action => action.payload)
    .switchMap(payload => this.commonService.saveBankCode(payload))
    .map(message => this.setupActions.saveBankCodeSuccess(message))
    .mergeMap(action => from([action, this.setupActions.findBankCodes()]));

  @Effect() updateBankCodes$ = this.actions$
    .ofType(SetupActions.UPDATE_BANK_CODE)
    .map(action => action.payload)
    .switchMap(payload => this.commonService.updateBankCode(payload))
    .map(message => this.setupActions.updateBankCodeSuccess(message))
    .mergeMap(action => from([action, this.setupActions.findBankCodes()]));

  @Effect() removeBankCodes$ = this.actions$
    .ofType(SetupActions.REMOVE_BANK_CODE)
    .map(action => action.payload)
    .switchMap(payload => this.commonService.removeBankCode(payload))
    .map(message => this.setupActions.removeBankCodeSuccess(message))
    .mergeMap(action => from([action, this.setupActions.findBankCodes()]));

  @Effect() findCountryCodes$ = this.actions$
    .ofType(SetupActions.FIND_COUNTRY_CODES)
    .map(action => action.payload)
    .switchMap(() => this.commonService.findCountryCodes())
    .map(codes => this.setupActions.findCountryCodesSuccess(codes));

  @Effect() findStateCodes$ = this.actions$
    .ofType(SetupActions.FIND_STATE_CODES)
    .map(action => action.payload)
    .switchMap(() => this.commonService.findStateCodes())
    .map(codes => this.setupActions.findStateCodesSuccess(codes));

  @Effect() findProgramCodes$ = this.actions$
    .ofType(SetupActions.FIND_PROGRAM_CODES)
    .map(action => action.payload)
    .switchMap(() => this.commonService.findProgramCodes())
    .map(codes => this.setupActions.findProgramCodesSuccess(codes));

  @Effect() saveProgramCodes$ = this.actions$
    .ofType(SetupActions.SAVE_PROGRAM_CODE)
    .map(action => action.payload)
    .switchMap(payload => this.commonService.saveProgramCode(payload))
    .map(message => this.setupActions.saveProgramCodeSuccess(message))
    .mergeMap(action => from([action, this.setupActions.findProgramCodes()]));

  @Effect() removeProgramCode$ = this.actions$
    .ofType(SetupActions.REMOVE_PROGRAM_CODE)
    .map(action => action.payload)
    .switchMap(payload => this.commonService.removeProgramCode(payload))
    .map(message => this.setupActions.removeProgramCodeSuccess(message))
    .mergeMap(action => from([action, this.setupActions.findProgramCodes()]));

  @Effect() findFacultyCodes$ = this.actions$
    .ofType(SetupActions.FIND_FACULTY_CODES)
    .map(action => action.payload)
    .switchMap(() => this.commonService.findFacultyCodes())
    .map(codes => this.setupActions.findFacultyCodesSuccess(codes));

  @Effect() saveFacultyCodes$ = this.actions$
    .ofType(SetupActions.SAVE_FACULTY_CODE)
    .map(action => action.payload)
    .switchMap(payload => this.commonService.saveFacultyCode(payload))
    .map(message => this.setupActions.saveFacultyCodeSuccess(message))
    .mergeMap(action => from([action, this.setupActions.findFacultyCodes()]));

  @Effect() findStudyModes$ = this.actions$
    .ofType(SetupActions.FIND_STUDY_MODES)
    .map(action => action.payload)
    .switchMap(() => this.commonService.findStudyModes())
    .map(codes => this.setupActions.findStudyModesSuccess(codes));

  @Effect() saveStudyModes$ = this.actions$
    .ofType(SetupActions.SAVE_STUDY_MODE)
    .map(action => action.payload)
    .switchMap(payload => this.commonService.saveStudyMode(payload))
    .map(message => this.setupActions.saveStudyModeSuccess(message))
    .mergeMap(action => from([action, this.setupActions.findStudyModes()]));

  @Effect() findResidencyCodes$ = this.actions$
    .ofType(SetupActions.FIND_RESIDENCY_CODES)
    .map(action => action.payload)
    .switchMap(() => this.commonService.findResidencyCodes())
    .map(codes => this.setupActions.findResidencyCodesSuccess(codes));

  @Effect() findCohortCodes$ = this.actions$
    .ofType(SetupActions.FIND_COHORT_CODES)
    .map(action => action.payload)
    .switchMap(() => this.commonService.findCohortCodes())
    .map(codes => this.setupActions.findCohortCodesSuccess(codes));

  @Effect() saveCohortCodes$ = this.actions$
    .ofType(SetupActions.SAVE_COHORT_CODE)
    .map(action => action.payload)
    .switchMap(payload => this.commonService.saveCohortCode(payload))
    .map(message => this.setupActions.saveCohortCodeSuccess(message))
    .mergeMap(action => from([action, this.setupActions.findCohortCodes()]));

  @Effect() updateCohortCodes$ = this.actions$
    .ofType(SetupActions.UPDATE_COHORT_CODE)
    .map(action => action.payload)
    .switchMap(payload => this.commonService.updateCohortCode(payload))
    .map(message => this.setupActions.updateCohortCodeSuccess(message))
    .mergeMap(action => from([action, this.setupActions.findCohortCodes()]));

  @Effect() removeCohortCode$ = this.actions$
    .ofType(SetupActions.REMOVE_COHORT_CODE)
    .map(action => action.payload)
    .switchMap(payload => this.commonService.removeCohortCode(payload))
    .map(message => this.setupActions.removeCohortCodeSuccess(message))
    .mergeMap(action => from([action, this.setupActions.findCohortCodes()]));

  @Effect() saveResidencyCodes$ = this.actions$
    .ofType(SetupActions.SAVE_RESIDENCY_CODE)
    .map(action => action.payload)
    .switchMap(payload => this.commonService.saveResidencyCode(payload))
    .map(message => this.setupActions.saveResidencyCodeSuccess(message))
    .mergeMap(action => from([action, this.setupActions.findResidencyCodes()]));

  @Effect() updateResidencyCodes$ = this.actions$
    .ofType(SetupActions.UPDATE_RESIDENCY_CODE)
    .map(action => action.payload)
    .switchMap(payload => this.commonService.updateResidencyCode(payload))
    .map(message => this.setupActions.updateResidencyCodeSuccess(message))
    .mergeMap(action => from([action, this.setupActions.findResidencyCodes()]));

  @Effect() removeResidencyCode$ = this.actions$
    .ofType(SetupActions.REMOVE_RESIDENCY_CODE)
    .map(action => action.payload)
    .switchMap(payload => this.commonService.removeResidencyCode(payload))
    .map(message => this.setupActions.removeResidencyCodeSuccess(message))
    .mergeMap(action => from([action, this.setupActions.findResidencyCodes()]));

  @Effect() findTaxCodes$ = this.actions$
    .ofType(SetupActions.FIND_TAX_CODES)
    .map(action => action.payload)
    .switchMap(() => this.commonService.findTaxCodes())
    .map(codes => this.setupActions.findTaxCodesSuccess(codes));

  @Effect() saveTaxCodes$ = this.actions$
    .ofType(SetupActions.SAVE_TAX_CODE)
    .map(action => action.payload)
    .switchMap(payload => this.commonService.saveTaxCode(payload))
    .map(message => this.setupActions.saveTaxCodeSuccess(message))
    .mergeMap(action => from([action, this.setupActions.findTaxCodes()]));

  @Effect() updateTaxCodes$ = this.actions$
    .ofType(SetupActions.UPDATE_TAX_CODE)
    .map(action => action.payload)
    .switchMap(payload => this.commonService.updateTaxCode(payload))
    .map(message => this.setupActions.updateTaxCodeSuccess(message))
    .mergeMap(action => from([action, this.setupActions.findTaxCodes()]));

  @Effect() removeTaxCode$ = this.actions$
    .ofType(SetupActions.REMOVE_TAX_CODE)
    .map(action => action.payload)
    .switchMap(payload => this.commonService.removeTaxCode(payload))
    .map(message => this.setupActions.removeTaxCodeSuccess(message))
    .mergeMap(action => from([action, this.setupActions.findTaxCodes()]));

    @Effect() findTaxCodesByActive$ = this.actions$
    .ofType(SetupActions.FIND_TAX_CODES_BY_ACTIVE)
    .map((action) => action.payload)
    .switchMap(() => this.commonService.findTaxCodesByActive())
    .map(codes => this.setupActions.findTaxCodesByActiveSuccess(codes));

  @Effect() findSecurityChargeCodes$ = this.actions$
    .ofType(SetupActions.FIND_SECURITY_CHARGE_CODES)
    .map(action => action.payload)
    .switchMap(() => this.commonService.findSecurityChargeCodes())
    .map(codes => this.setupActions.findSecurityChargeCodesSuccess(codes));

  @Effect() findSecurityChargeCodesByActive$ = this.actions$
    .ofType(SetupActions.FIND_SECURITY_CHARGE_CODES_BY_ACTIVE)
    .map((action) => action.payload)
    .switchMap(() => this.commonService.findSecurityChargeCodesByActive())
    .map(codes => this.setupActions.findSecurityChargeCodesByActiveSuccess(codes));

  @Effect() saveSecurityChargeCode$ = this.actions$
    .ofType(SetupActions.SAVE_SECURITY_Charge_CODE)
    .map(action => action.payload)
    .switchMap(payload => this.commonService.saveSecurityChargeCode(payload))
    .map(message => this.setupActions.saveSecurityChargeCodeSuccess(message))
    .mergeMap(action => from([action, this.setupActions.findSecurityChargeCodes()]));

  @Effect() updateSecurityChargeCode$ = this.actions$
    .ofType(SetupActions.UPDATE_SECURITY_Charge_CODE)
    .map(action => action.payload)
    .switchMap(payload => this.commonService.updateSecurityChargeCode(payload))
    .map(message => this.setupActions.updateSecurityChargeCodeSuccess(message))
    .mergeMap(action => from([action, this.setupActions.findSecurityChargeCodes()]));

  @Effect() removeSecurityChargeCode$ = this.actions$
    .ofType(SetupActions.REMOVE_SECURITY_Charge_CODE)
    .map(action => action.payload)
    .switchMap(payload => this.commonService.removeSecurityChargeCode(payload))
    .map(message => this.setupActions.removeSecurityChargeCodeSuccess(message))
    .mergeMap(action => from([action, this.setupActions.findSecurityChargeCodes()]));
}
