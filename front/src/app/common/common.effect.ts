import {Injectable} from "@angular/core";
import {Effect, Actions} from '@ngrx/effects';
import {CommonService} from "../../services/common.service";
import {CommonActions} from "./common.action";

@Injectable()
export class CommonEffects {
  constructor(private actions$: Actions,
              private commonActions: CommonActions,
              private commonService: CommonService) {}

  @Effect() findDunCodes$ = this.actions$
    .ofType(CommonActions.FIND_DUN_CODES)
    .map(action => action.payload)
    .switchMap(() => this.commonService.findDunCodes())
    .map(codes => this.commonActions.findDunCodesSuccess(codes));

  @Effect() findParliamentCodes$ = this.actions$
    .ofType(CommonActions.FIND_PARLIAMENT_CODES)
    .map(action => action.payload)
    .switchMap(() => this.commonService.findParliamentCodes())
    .map(codes => this.commonActions.findParliamentCodesSuccess(codes));

  @Effect() findGenderCodes$ = this.actions$
    .ofType(CommonActions.FIND_GENDER_CODES)
    .map(action => action.payload)
    .switchMap(() => this.commonService.findGenderCodes())
    .map(codes => this.commonActions.findGenderCodesSuccess(codes));

  @Effect() findMaritalCodes$ = this.actions$
    .ofType(CommonActions.FIND_MARITAL_CODES)
    .map(action => action.payload)
    .switchMap(() => this.commonService.findMaritalCodes())
    .map(codes => this.commonActions.findMaritalCodesSuccess(codes));


  @Effect() findFacultyCodes$ = this.actions$
    .ofType(CommonActions.FIND_FACULTY_CODES)
    .map(action => action.payload)
    .switchMap(() => this.commonService.findFacultyCodes())
    .map(codes => this.commonActions.findFacultyCodesSuccess(codes));

  @Effect() findGraduateCentres$ = this.actions$
    .ofType(CommonActions.FIND_GRADUATE_CENTRES)
    .map(action => action.payload)
    .switchMap(() => this.commonService.findGraduateCentres())
    .map(centres => this.commonActions.findGraduateCentresSuccess(centres));

  @Effect() findProgramCodes$ = this.actions$
    .ofType(CommonActions.FIND_PROGRAM_CODES)
    .map(action => action.payload)
    .switchMap(() => this.commonService.findProgramCodes())
    .map(codes => this.commonActions.findProgramCodesSuccess(codes));

   @Effect() findStudyMode$ = this.actions$
     .ofType(CommonActions.FIND_STUDY_MODES)
     .map(action => action.payload)
     .switchMap(() => this.commonService.findStudyModes())
     .map(codes => this.commonActions.findStudyModeSuccess(codes));
}
