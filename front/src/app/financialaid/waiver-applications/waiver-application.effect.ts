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

  // @Effect() findCompletedWaiverApplications$ = this.actions$
  //   .ofType(WaiverApplicationActions.FIND_COMPLETED_WAIVER_APPLICATIONS)
  //   .switchMap(() => this.financialaidService.findCompletedWaiverApplications())
  //   .map(invoices => this.waiverApplicationActions.findCompletedWaiverApplicationSuccess(invoices));

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
    .map(referenceNo => this.waiverApplicationActions.startWaiverApplicationTaskSuccess(referenceNo))
    .mergeMap(action => from([action,
        this.waiverApplicationActions.findAssignedWaiverApplicationTasks(),
        this.waiverApplicationActions.findPooledWaiverApplicationTasks()
      ]
    ));

  @Effect() completeWaiverApplicationTask$ = this.actions$
    .ofType(WaiverApplicationActions.COMPLETE_WAIVER_APPLICATION_TASK)
    .map(action => action.payload)
    .switchMap(invoiceTask => this.financialaidService.completeWaiverApplicationTask(invoiceTask))
    .map(message => this.waiverApplicationActions.completeWaiverApplicationTaskSuccess(message))
    .mergeMap(action => from([action,
        this.waiverApplicationActions.findAssignedWaiverApplicationTasks(),
        this.waiverApplicationActions.findPooledWaiverApplicationTasks()
      ]
    ));

  @Effect() claimWaiverApplicationTask$ = this.actions$
    .ofType(WaiverApplicationActions.CLAIM_WAIVER_APPLICATION_TASK)
    .map(action => action.payload)
    .switchMap(invoiceTask => this.financialaidService.claimWaiverApplicationTask(invoiceTask))
    .map(message => this.waiverApplicationActions.claimWaiverApplicationTaskSuccess(message))
    .mergeMap(action => from([action,
        this.waiverApplicationActions.findAssignedWaiverApplicationTasks(),
        this.waiverApplicationActions.findPooledWaiverApplicationTasks()
      ]
    ));

  @Effect() releaseWaiverApplicationTask$ = this.actions$
    .ofType(WaiverApplicationActions.RELEASE_WAIVER_APPLICATION_TASK)
    .map(action => action.payload)
    .switchMap(invoiceTask => this.financialaidService.releaseWaiverApplicationTask(invoiceTask))
    .map(message => this.waiverApplicationActions.releaseWaiverApplicationTaskSuccess(message))
    .mergeMap(action => from([action,
        this.waiverApplicationActions.findAssignedWaiverApplicationTasks(),
        this.waiverApplicationActions.findPooledWaiverApplicationTasks()
      ]
    ));

  @Effect() updateWaiverApplication$ = this.actions$
    .ofType(WaiverApplicationActions.UPDATE_WAIVER_APPLICATION)
    .map(action => action.payload)
    .switchMap(invoice => this.financialaidService.updateWaiverApplication(invoice))
    .map(invoice => this.waiverApplicationActions.updateWaiverApplicationSuccess(invoice));

}
