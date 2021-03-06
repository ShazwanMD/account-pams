import {Injectable} from '@angular/core';
import {Actions, Effect} from '@ngrx/effects';
import {from} from 'rxjs/observable/from';
import {WaiverApplicationActions} from './waiver-application.action';
import {FinancialaidService} from '../../../../services/financialaid.service';
import { Store } from "@ngrx/store";
import { FinancialaidModuleState } from "../index";
import { Router } from "@angular/router";

@Injectable()
export class WaiverApplicationEffects {
  
  private WAIVER_APPLICATION_TASK: string[] = 'financialaidModuleState.waiverApplicationTask'.split('.');

  constructor(private actions$: Actions,
              private waiverApplicationActions: WaiverApplicationActions,
              private store$: Store<FinancialaidModuleState>,
              private router: Router,
              private financialaidService: FinancialaidService) {
  }

  @Effect() findCompletedWaiverApplications$ = this.actions$
    .ofType(WaiverApplicationActions.FIND_COMPLETED_WAIVER_APPLICATIONS)
    .switchMap(() => this.financialaidService.findCompletedWaiverApplications())
    .map((invoices) => this.waiverApplicationActions.findCompletedWaiverApplicationsSuccess(invoices));

  @Effect() findAssignedWaiverApplicationTasks$ = this.actions$
    .ofType(WaiverApplicationActions.FIND_ASSIGNED_WAIVER_APPLICATION_TASKS)
    .switchMap(() => this.financialaidService.findAssignedWaiverApplicationTasks())
    .map((waiverApplications) => this.waiverApplicationActions.findAssignedWaiverApplicationTasksSuccess(waiverApplications));

  @Effect() findPooledWaiverApplicationTasks$ = this.actions$
    .ofType(WaiverApplicationActions.FIND_POOLED_WAIVER_APPLICATION_TASKS)
    .switchMap(() => this.financialaidService.findPooledWaiverApplicationTasks())
    .map((waiverApplications) => this.waiverApplicationActions.findPooledWaiverApplicationTasksSuccess(waiverApplications));

  @Effect() findWaiverApplicationTaskByTaskId = this.actions$
    .ofType(WaiverApplicationActions.FIND_WAIVER_APPLICATION_TASK_BY_TASK_ID)
    .map((action) => action.payload)
    .switchMap((taskId) => this.financialaidService.findWaiverApplicationTaskByTaskId(taskId))
    .map((task) => this.waiverApplicationActions.findWaiverApplicationTaskByTaskIdSuccess(task));

  @Effect() findWaiverApplicationByReferenceNo$ = this.actions$
    .ofType(WaiverApplicationActions.FIND_WAIVER_APPLICATION_BY_REFERENCE_NO)
    .map((action) => action.payload)
    .switchMap((referenceNo) => this.financialaidService.findWaiverApplicationByReferenceNo(referenceNo))
    .map((waiverApplication) => this.waiverApplicationActions.findWaiverApplicationByReferenceNoSuccess(waiverApplication))
    .mergeMap((action) => from([action, this.waiverApplicationActions.findWaiverApplicationItems(action.payload)]));

  @Effect() findArchivedWaiverApplications$ = this.actions$
    .ofType(WaiverApplicationActions.FIND_ARCHIVED_WAIVER_APPLICATIONS)
    .switchMap(() => this.financialaidService.findArchivedWaiverApplications())
    .map((invoices) => this.waiverApplicationActions.findArchivedWaiverApplicationsSuccess(invoices));

  @Effect() startWaiverApplicationTask$ = this.actions$
    .ofType(WaiverApplicationActions.START_WAIVER_APPLICATION_TASK)
    .map((action) => action.payload)
    .switchMap((waiverApplication) => this.financialaidService.startWaiverApplicationTask(waiverApplication))
    .map((referenceNo) => this.waiverApplicationActions.startWaiverApplicationTaskSuccess(referenceNo))
    .mergeMap((action) => from([action,
        this.waiverApplicationActions.findAssignedWaiverApplicationTasks(),
        this.waiverApplicationActions.findPooledWaiverApplicationTasks(),
      ],
    ));

  @Effect() completeWaiverApplicationTask$ = this.actions$
    .ofType(WaiverApplicationActions.COMPLETE_WAIVER_APPLICATION_TASK)
    .map((action) => action.payload)
    .switchMap((applicationTask) => this.financialaidService.completeWaiverApplicationTask(applicationTask))
    .map((message) => this.waiverApplicationActions.completeWaiverApplicationTaskSuccess(message))
    .mergeMap((action) => from([action,
        this.waiverApplicationActions.findAssignedWaiverApplicationTasks(),
        this.waiverApplicationActions.findPooledWaiverApplicationTasks(),
        this.waiverApplicationActions.findArchivedWaiverApplications(),
      ],
    ));

  @Effect() claimWaiverApplicationTask$ = this.actions$
    .ofType(WaiverApplicationActions.CLAIM_WAIVER_APPLICATION_TASK)
    .map((action) => action.payload)
    .switchMap((applicationTask) => this.financialaidService.claimWaiverApplicationTask(applicationTask))
    .map((message) => this.waiverApplicationActions.claimWaiverApplicationTaskSuccess(message))
    .mergeMap((action) => from([action,
        this.waiverApplicationActions.findAssignedWaiverApplicationTasks(),
        this.waiverApplicationActions.findPooledWaiverApplicationTasks(),
      ],
    ));

  @Effect() removeWaiverApplicationTask$ = this.actions$
  .ofType(WaiverApplicationActions.REMOVE_WAIVER_APPLICATION_TASK)
  .map((action) => action.payload)
  .switchMap((waiverApplicationTask) => this.financialaidService.removeWaiverApplicationTask(waiverApplicationTask))
  .map((task) => this.waiverApplicationActions.removeWaiverApplicationTaskSuccess(task))
  .mergeMap((action) => from([action,
                              this.waiverApplicationActions.findAssignedWaiverApplicationTasks(),
                              this.waiverApplicationActions.findPooledWaiverApplicationTasks(),
                              this.waiverApplicationActions.findArchivedWaiverApplications(),
    ],
  ));
  
  @Effect() releaseWaiverApplicationTask$ = this.actions$
    .ofType(WaiverApplicationActions.RELEASE_WAIVER_APPLICATION_TASK)
    .map((action) => action.payload)
    .switchMap((applicationTask) => this.financialaidService.releaseWaiverApplicationTask(applicationTask))
    .map((message) => this.waiverApplicationActions.releaseWaiverApplicationTaskSuccess(message))
    .mergeMap((action) => from([action,
        this.waiverApplicationActions.findAssignedWaiverApplicationTasks(),
        this.waiverApplicationActions.findPooledWaiverApplicationTasks(),
      ],
    ));

  @Effect() updateWaiverApplication$ = this.actions$
    .ofType(WaiverApplicationActions.UPDATE_WAIVER_APPLICATION)
    .map((action) => action.payload)
    .switchMap((waiverApplication) => this.financialaidService.updateWaiverApplication(waiverApplication))
    .map((message) => this.waiverApplicationActions.updateWaiverApplicationSuccess(message))
    .do((action) => this.router.navigate(['/secure/financialaid/waiver-applications/view-task/:taskId', action.payload])).ignoreElements();
}
