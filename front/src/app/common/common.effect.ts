import {Injectable} from "@angular/core";
import {Effect, Actions} from '@ngrx/effects';
import {CommonService} from "../../services/common.service";
import {CommonActions} from "./common.action";

@Injectable()
export class CommonEffects {
  constructor(private actions$: Actions,
              private commonActions: CommonActions,
              private commonService: CommonService) {}


  @Effect() findCohortCodes$ = this.actions$
    .ofType(CommonActions.FIND_COHORT_CODES)
    .map(action => action.payload)
    .switchMap(() => this.commonService.findCohortCodes())
    .map(codes => this.commonActions.findCohortCodesSuccess(codes));
}
