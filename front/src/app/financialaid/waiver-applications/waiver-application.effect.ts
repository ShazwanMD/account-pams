import {Injectable} from '@angular/core';
import {Effect, Actions} from '@ngrx/effects';
import {from} from "rxjs/observable/from";
import {WaiverApplicationActions} from "./waiver-application.action";
import {FinancialaidService} from "../../../services/financialaid.service";


@Injectable()
export class WaiverApplicationEffects {
  constructor(private actions$: Actions,
              private waiverApplicationActions: WaiverApplicationActions,
              private financialaidService: FinancialaidService) {
  }

  @Effect() findAssignedWaiverApplicationTasks$ = this.actions$
    .ofType(WaiverApplicationActions.FIND_ASSIGNED_WAIVER_APPLICATION_TASKS)
    .switchMap(() => this.financialaidService.findAssignedWaiverApplicationTasks())
    .map(waiverApplications => this.waiverApplicationActions.findAssignedWaiverApplicationTasksSuccess(waiverApplications));


  @Effect() findPooledWaiverApplicationTasks$ = this.actions$
    .ofType(WaiverApplicationActions.FIND_POOLED_WAIVER_APPLICATION_TASKS)
    .switchMap(() => this.financialaidService.findPooledWaiverApplicationTasks())
    .map(waiverApplications => this.waiverApplicationActions.findPooledWaiverApplicationTasksSuccess(waiverApplications));

  @Effect() findWaiverApplicationTaskByTaskId = this.actions$
    .ofType(WaiverApplicationActions.FIND_WAIVER_APPLICATION_TASK_BY_TASK_ID)
    .map(action => action.payload)
    .switchMap(taskId => this.financialaidService.findWaiverApplicationTaskByTaskId(taskId))
    .map(task => this.waiverApplicationActions.findWaiverApplicationTaskByTaskIdSuccess(task));

  @Effect() findWaiverApplicationByReferenceNo$ = this.actions$
    .ofType(WaiverApplicationActions.FIND_WAIVER_APPLICATION_BY_REFERENCE_NO)
    .map(action => action.payload)
    .switchMap(referenceNo => this.financialaidService.findWaiverApplicationByReferenceNo(referenceNo))
    .map(waiverApplication => this.waiverApplicationActions.findWaiverApplicationByReferenceNoSuccess(waiverApplication))
    .mergeMap(action => from([action, this.waiverApplicationActions.findWaiverApplicationItems(action.payload)]));

  @Effect() startWaiverApplicationTask$ = this.actions$
    .ofType(WaiverApplicationActions.START_WAIVER_APPLICATION_TASK)
    .map(action => action.payload)
    .switchMap(waiverApplication => this.financialaidService.startWaiverApplicationTask(waiverApplication))
    .map(task => this.waiverApplicationActions.startWaiverApplicationTaskSuccess(task));

  @Effect() completeWaiverApplicationTask$ = this.actions$
    .ofType(WaiverApplicationActions.COMPLETE_WAIVER_APPLICATION_TASK)
    .map(action => action.payload);
  // todo
  // .switchMap(waiverApplication => this.financialaidService.startWaiverApplicationTask(waiverApplication))
  // .map(task => this.waiverApplicationActions.startWaiverApplicationTaskSuccess(task));

  @Effect() assignWaiverApplicationTask$ = this.actions$
    .ofType(WaiverApplicationActions.ASSIGN_WAIVER_APPLICATION_TASK)
    .map(action => action.payload);
  // todo
  // .switchMap(waiverApplication => this.financialaidService.startWaiverApplicationTask(waiverApplication))
  // .map(task => this.waiverApplicationActions.startWaiverApplicationTaskSuccess(task));


  @Effect() releaseWaiverApplicationTask$ = this.actions$
    .ofType(WaiverApplicationActions.RELEASE_WAIVER_APPLICATION_TASK)
    .map(action => action.payload);
  // todo
  // .switchMap(waiverApplication => this.financialaidService.startWaiverApplicationTask(waiverApplication))
  // .map(task => this.waiverApplicationActions.startWaiverApplicationTaskSuccess(task));

  @Effect() updateWaiverApplication$ = this.actions$
    .ofType(WaiverApplicationActions.UPDATE_WAIVER_APPLICATION)
    .map(action => action.payload)
    .switchMap(waiverApplication => this.financialaidService.updateWaiverApplication(waiverApplication))
    .map(waiverApplication => this.waiverApplicationActions.updateWaiverApplicationSuccess(waiverApplication));
}
