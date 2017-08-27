import { BillingService } from './../../../../services/billing.service';
import {Injectable} from '@angular/core';
import {Actions, Effect} from '@ngrx/effects';
import {from} from 'rxjs/observable/from';
import {FinancialaidService} from '../../../../services/financialaid.service';
import { WaiverFinanceApplicationActions } from "./waiver-finance-application.action";

@Injectable()
export class WaiverFinanceApplicationEffects {
  constructor(private actions$: Actions,
              private waiverFinanceApplicationActions: WaiverFinanceApplicationActions,
              private billingService: BillingService) {
  }

  @Effect() findCompletedWaiverFinanceApplications$ = this.actions$
    .ofType(WaiverFinanceApplicationActions.FIND_COMPLETED_WAIVER_FINANCE_APPLICATIONS)
    .switchMap(() => this.billingService.findCompletedWaiverFinanceApplications())
    .map((invoices) => this.waiverFinanceApplicationActions.findCompletedWaiverFinanceApplicationsSuccess(invoices));

  @Effect() findAssignedWaiverFinanceApplicationTasks$ = this.actions$
    .ofType(WaiverFinanceApplicationActions.FIND_ASSIGNED_WAIVER_FINANCE_APPLICATION_TASKS)
    .switchMap(() => this.billingService.findAssignedWaiverFinanceApplicationTasks())
    .map((waiverfinanceApplications) => this.waiverFinanceApplicationActions.findAssignedWaiverFinanceApplicationTasksSuccess(waiverApplications));

  @Effect() findPooledWaiverFinanceApplicationTasks$ = this.actions$
    .ofType(WaiverFinanceApplicationActions.FIND_POOLED_WAIVER_FINANCE_APPLICATION_TASKS)
    .switchMap(() => this.billingService.findPooledWaiverFinanceApplicationTasks())
    .map((waiverFinanceApplications) => this.waiverFinanceApplicationActions.findPooledWaiverFinanceApplicationTasksSuccess(waiverApplications));

  @Effect() findWaiverFinanceApplicationTaskByTaskId = this.actions$
    .ofType(WaiverFinanceApplicationActions.FIND_WAIVER_FINANCE_APPLICATION_TASK_BY_TASK_ID)
    .map((action) => action.payload)
    .switchMap((taskId) => this.billingService.findWaiverFinanceApplicationTaskByTaskId(taskId))
    .map((task) => this.waiverFinanceApplicationActions.findWaiverFinanceApplicationTaskByTaskIdSuccess(task));

  @Effect() findWaiverFinanceApplicationByReferenceNo$ = this.actions$
    .ofType(WaiverFinanceApplicationActions.FIND_WAIVER_FINANCE_APPLICATION_BY_REFERENCE_NO)
    .map((action) => action.payload)
    .switchMap((referenceNo) => this.billingService.findWaiverFinanceApplicationByReferenceNo(referenceNo))
    .map((waiverApplication) => this.waiverFinanceApplicationActions.findWaiverFinanceApplicationByReferenceNoSuccess(waiverApplication))
    .mergeMap((action) => from([action, this.waiverFinanceApplicationActions.findWaiverFinanceApplicationItems(action.payload)]));

  @Effect() findArchivedWaiverFinanceApplications$ = this.actions$
    .ofType(WaiverFinanceApplicationActions.FIND_ARCHIVED_WAIVER_FINANCE_APPLICATIONS)
    .switchMap(() => this.billingService.findArchivedWaiverFinanceApplications())
    .map((invoices) => this.waiverFinanceApplicationActions.findArchivedWaiverFinanceApplicationsSuccess(invoices));

  @Effect() startWaiverFinanceApplicationTask$ = this.actions$
    .ofType(WaiverFinanceApplicationActions.START_WAIVER_FINANCE_APPLICATION_TASK)
    .map((action) => action.payload)
    .switchMap((waiverFinanceApplication) => this.billingService.startWaiverFinanceApplicationTask(waiverFinanceApplication))
    .map((referenceNo) => this.waiverFinanceApplicationActions.startWaiverFinanceApplicationTaskSuccess(referenceNo))
    .mergeMap((action) => from([action,
        this.waiverFinanceApplicationActions.findAssignedWaiverFinanceApplicationTasks(),
        this.waiverFinanceApplicationActions.findPooledWaiverFinanceApplicationTasks(),
      ],
    ));

  @Effect() completeWaiverFinanceApplicationTask$ = this.actions$
    .ofType(WaiverFinanceApplicationActions.COMPLETE_WAIVER_FINANCE_APPLICATION_TASK)
    .map((action) => action.payload)
    .switchMap((applicationTask) => this.billingService.completeWaiverFinanceApplicationTask(applicationTask))
    .map((message) => this.waiverFinanceApplicationActions.completeWaiverFinanceApplicationTaskSuccess(message))
    .mergeMap((action) => from([action,
        this.waiverFinanceApplicationActions.findAssignedWaiverFinanceApplicationTasks(),
        this.waiverFinanceApplicationActions.findPooledWaiverFinanceApplicationTasks(),
        this.waiverFinanceApplicationActions.findArchivedWaiverFinanceApplications(),
      ],
    ));

  @Effect() claimWaiverFinanceApplicationTask$ = this.actions$
    .ofType(WaiverFinanceApplicationActions.CLAIM_WAIVER_FINANCE_APPLICATION_TASK)
    .map((action) => action.payload)
    .switchMap((applicationTask) => this.billingService.claimWaiverFinanceApplicationTask(applicationTask))
    .map((message) => this.waiverFinanceApplicationActions.claimWaiverFinanceApplicationTaskSuccess(message))
    .mergeMap((action) => from([action,
        this.waiverFinanceApplicationActions.findAssignedWaiverFinanceApplicationTasks(),
        this.waiverFinanceApplicationActions.findPooledWaiverFinanceApplicationTasks(),
      ],
    ));

  @Effect() releaseWaiverFinanceApplicationTask$ = this.actions$
    .ofType(WaiverFinanceApplicationActions.RELEASE_WAIVER_FINANCE_APPLICATION_TASK)
    .map((action) => action.payload)
    .switchMap((applicationTask) => this.billingService.releaseWaiverApplicationTask(applicationTask))
    .map((message) => this.waiverFinanceApplicationActions.releaseWaiverFinanceApplicationTaskSuccess(message))
    .mergeMap((action) => from([action,
        this.waiverFinanceApplicationActions.findAssignedWaiverFinanceApplicationTasks(),
        this.waiverFinanceApplicationActions.findPooledWaiverFinanceApplicationTasks(),
      ],
    ));

  @Effect() updateWaiverFinanceApplication$ = this.actions$
    .ofType(WaiverFinanceApplicationActions.UPDATE_WAIVER_FINANCE_APPLICATION)
    .map((action) => action.payload)
    .switchMap((application) => this.billingService.updateWaiverFinanceApplication(application))
    .map((application) => this.waiverFinanceApplicationActions.updateWaiverFinanceApplicationSuccess(application));

}
