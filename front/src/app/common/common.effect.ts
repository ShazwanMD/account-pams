import {Injectable} from '@angular/core';
import {Actions, Effect} from '@ngrx/effects';
import {CommonService} from '../../services/common.service';
import {CommonActions} from './common.action';

@Injectable()
export class CommonEffects {
  constructor(private actions$: Actions,
              private commonActions: CommonActions,
              private commonService: CommonService) {
  }

  @Effect() findCohortCodes$ = this.actions$
    .ofType(CommonActions.FIND_COHORT_CODES)
    .map((action) => action.payload)
    .switchMap(() => this.commonService.findCohortCodes())
    .map((codes) => this.commonActions.findCohortCodesSuccess(codes));

  @Effect() findFacultyCodes$ = this.actions$
    .ofType(CommonActions.FIND_FACULTY_CODES)
    .map((action) => action.payload)
    .switchMap(() => this.commonService.findFacultyCodes())
    .map((codes) => this.commonActions.findFacultyCodesSuccess(codes));

  @Effect() findStudyModes$ = this.actions$
    .ofType(CommonActions.FIND_STUDY_MODES)
    .map((action) => action.payload)
    .switchMap(() => this.commonService.findStudyModes())
    .map((codes) => this.commonActions.findStudyModesSuccess(codes));

  @Effect() findStudyCenterCodes$ = this.actions$
  .ofType(CommonActions.FIND_STUDY_CENTER_CODES)
  .map((action) => action.payload)
  .switchMap(() => this.commonService.findStudyCenterCodes())
  .map((codes) => this.commonActions.findStudyCenterCodesSuccess(codes));

  @Effect() findProgramCodes$ = this.actions$
  .ofType(CommonActions.FIND_PROGRAM_CODES)
  .map((action) => action.payload)
  .switchMap(() => this.commonService.findProgramCodes())
  .map((codes) => this.commonActions.findProgramCodesSuccess(codes));

  @Effect() findProgramLevels$ = this.actions$
  .ofType(CommonActions.FIND_PROGRAM_LEVELS)
  .map((action) => action.payload)
  .switchMap(() => this.commonService.findProgramLevels())
  .map((codes) => this.commonActions.findProgramLevelsSuccess(codes));
}
