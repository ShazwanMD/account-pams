import { BillingModuleState } from './../index';
import { Store } from '@ngrx/store';
import { BillingService } from './../../../../services/billing.service';
import {Injectable} from '@angular/core';
import {Actions, Effect} from '@ngrx/effects';
import {from} from 'rxjs/observable/from';
import {FinancialaidService} from '../../../../services/financialaid.service';
import { WaiverFinanceApplicationActions } from "./waiver-finance-application.action";
import { Router } from "@angular/router";

@Injectable()
export class WaiverFinanceApplicationEffects {

  private WAIVER_FINANCE_APPLICATION_TASK: string[] = 'billingModuleState.waiverFinanceApplicationTask'.split('.');

  constructor(private actions$: Actions,
              private waiverFinanceApplicationActions: WaiverFinanceApplicationActions,
              private billingService: BillingService,
              private router: Router,
              private store$: Store<BillingModuleState>) {
  }

  @Effect() findCompletedWaiverFinanceApplications$ = this.actions$
    .ofType(WaiverFinanceApplicationActions.FIND_COMPLETED_WAIVER_FINANCE_APPLICATIONS)
    .switchMap(() => this.billingService.findCompletedWaiverFinanceApplications())
    .map((invoices) => this.waiverFinanceApplicationActions.findCompletedWaiverFinanceApplicationsSuccess(invoices));

  @Effect() findAssignedWaiverFinanceApplicationTasks$ = this.actions$
    .ofType(WaiverFinanceApplicationActions.FIND_ASSIGNED_WAIVER_FINANCE_APPLICATION_TASKS)
    .switchMap(() => this.billingService.findAssignedWaiverFinanceApplicationTasks())
    .map((waiverFinanceApplications) => this.waiverFinanceApplicationActions.findAssignedWaiverFinanceApplicationTasksSuccess(waiverFinanceApplications));

  @Effect() findPooledWaiverFinanceApplicationTasks$ = this.actions$
    .ofType(WaiverFinanceApplicationActions.FIND_POOLED_WAIVER_FINANCE_APPLICATION_TASKS)
    .switchMap(() => this.billingService.findPooledWaiverFinanceApplicationTasks())
    .map((waiverFinanceApplications) => this.waiverFinanceApplicationActions.findPooledWaiverFinanceApplicationTasksSuccess(waiverFinanceApplications));

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
    .mergeMap((action) => from([action, 
      this.waiverFinanceApplicationActions.findWaiverFinanceApplicationItems(action.payload),
      this.waiverFinanceApplicationActions.findWaiverItems(action.payload),
    ]));

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

  @Effect() removeWaiverFinanceApplicationTask$ = this.actions$
  .ofType(WaiverFinanceApplicationActions.REMOVE_WAIVER_TASK)
  .map((action) => action.payload)
  .switchMap((waiverFinanceApplicationTask) => this.billingService.removeWaiverFinanceApplicationTask(waiverFinanceApplicationTask))
  .map((task) => this.waiverFinanceApplicationActions.removeWaiverFinanceApplicationTaskSuccess(task))
  .mergeMap((action) => from([action,
                              this.waiverFinanceApplicationActions.findAssignedWaiverFinanceApplicationTasks(),
                              this.waiverFinanceApplicationActions.findPooledWaiverFinanceApplicationTasks(),
                              this.waiverFinanceApplicationActions.findArchivedWaiverFinanceApplications(),
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
    .switchMap((applicationTask) => this.billingService.releaseWaiverFinanceApplicationTask(applicationTask))
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
    .map((message) => this.waiverFinanceApplicationActions.updateWaiverFinanceApplicationSuccess(message));

  @Effect() addWaiverItem$ = this.actions$
  .ofType(WaiverFinanceApplicationActions.ADD_WAIVER_ITEM)
  .map((action) => action.payload)
  .switchMap((payload) => this.billingService.addWaiverItem(payload.waiverFinanceApplication, payload.item))
  .map((message) => this.waiverFinanceApplicationActions.addWaiverItemSuccess(message))
  .withLatestFrom(this.store$.select(...this.WAIVER_FINANCE_APPLICATION_TASK))
  .map((state) => state[1])
  .map((waiverFinanceApplication) => this.waiverFinanceApplicationActions.findWaiverItems(waiverFinanceApplication));
  
  @Effect() addWaiverInvoice$ = this.actions$
  .ofType(WaiverFinanceApplicationActions.ADD_WAIVER_INVOICE)
  .map((action) => action.payload)
  .switchMap((payload) => this.billingService.addWaiverInvoice(payload.waiverFinanceApplication, payload.invoice))
  .map((message) => this.waiverFinanceApplicationActions.addWaiverInvoiceSuccess(message))
  .withLatestFrom(this.store$.select(...this.WAIVER_FINANCE_APPLICATION_TASK))
  .map((state) => state[1])
  .map((waiverFinanceApplication) => this.waiverFinanceApplicationActions.findWaiversByInvoice(waiverFinanceApplication));
  
  @Effect() addWaiverAccountCharge$ = this.actions$
  .ofType(WaiverFinanceApplicationActions.ADD_WAIVER_ACCOUNT_CHARGE)
  .map((action) => action.payload)
  .switchMap((payload) => this.billingService.addWaiverAccountCharge(payload.waiverFinanceApplication, payload.accountCharge))
  .map((message) => this.waiverFinanceApplicationActions.addWaiverAccountChargeSuccess(message))
  .withLatestFrom(this.store$.select(...this.WAIVER_FINANCE_APPLICATION_TASK))
  .map((state) => state[1])
  .map((waiverFinanceApplication) => this.waiverFinanceApplicationActions.findWaiverByAccountCharge(waiverFinanceApplication)); 
  
  @Effect() addWaiverDebitNote$ = this.actions$
  .ofType(WaiverFinanceApplicationActions.ADD_WAIVER_DEBIT_NOTE)
  .map((action) => action.payload)
  .switchMap((payload) => this.billingService.addWaiverDebitNote(payload.waiverFinanceApplication, payload.debitNote))
  .map((message) => this.waiverFinanceApplicationActions.addWaiverDebitNoteSuccess(message))
  .withLatestFrom(this.store$.select(...this.WAIVER_FINANCE_APPLICATION_TASK))
  .map((state) => state[1])
  .map((waiverFinanceApplication) => this.waiverFinanceApplicationActions.findWaiverByDebitNote(waiverFinanceApplication));   
  
  @Effect() updateWaivers$ = this.actions$
  .ofType(WaiverFinanceApplicationActions.UPDATE_WAIVER)
  .map((action) => action.payload)
  .switchMap((waiverFinanceApplication) => this.billingService.updateWaivers(waiverFinanceApplication))
  .map((message) => this.waiverFinanceApplicationActions.updateWaiversSuccess(message));
  
  @Effect() updateWaiverItems$ = this.actions$
  .ofType(WaiverFinanceApplicationActions.UPDATE_WAIVER_ITEMS)
  .map((action) => action.payload)
  .switchMap((payload) => this.billingService.updateWaiverItems(payload.waiverFinanceApplication, payload.item))
  .map((message) => this.waiverFinanceApplicationActions.updateWaiverItemsSuccess(message))
    .withLatestFrom(this.store$.select(...this.WAIVER_FINANCE_APPLICATION_TASK))
  .map((state) => state[1])
  .map((waiverFinanceApplication) => this.waiverFinanceApplicationActions.findWaiverItems(waiverFinanceApplication))
 // .do((action) => this.router.navigate(['/secure/billing/waiver-finance-applications/view-task/:taskId', action.payload])).ignoreElements();
  
  @Effect() itemToWaiverItem$ = this.actions$
  .ofType(WaiverFinanceApplicationActions.ITEM_TO_WAIVER_INVOICE)
  .map((action) => action.payload)
  .switchMap((payload) => this.billingService.itemToWaiverItem(payload.invoice, payload.waiverFinanceApplication))
  .map((message) => this.waiverFinanceApplicationActions.itemToWaiverItemSuccess(message))
  .withLatestFrom(this.store$.select(...this.WAIVER_FINANCE_APPLICATION_TASK))
  .map((state) => state[1])
  .map((waiverFinanceApplication) => this.waiverFinanceApplicationActions.findWaiverItems(waiverFinanceApplication));  

  @Effect() debitToWaiverItem$ = this.actions$
  .ofType(WaiverFinanceApplicationActions.ITEM_TO_WAIVER_DEBIT)
  .map((action) => action.payload)
  .switchMap((payload) => this.billingService.debitToWaiverItem(payload.debitNote, payload.waiverFinanceApplication))
  .map((message) => this.waiverFinanceApplicationActions.debitToWaiverItemSuccess(message))
  .withLatestFrom(this.store$.select(...this.WAIVER_FINANCE_APPLICATION_TASK))
  .map((state) => state[1])
  .map((waiverFinanceApplication) => this.waiverFinanceApplicationActions.findWaiverItems(waiverFinanceApplication));  
  
  @Effect() findWaiversByInvoice$ = this.actions$
  .ofType(WaiverFinanceApplicationActions.FIND_WAIVER_INVOICE)
  .map((action) => action.payload)
  .switchMap((waiverFinanceApplication) => this.billingService.findWaiversByInvoice(waiverFinanceApplication))
  .map((message) => this.waiverFinanceApplicationActions.findWaiversByInvoiceSuccess(message));
  
  @Effect() findInvoiceWaiverItems$ = this.actions$
  .ofType(WaiverFinanceApplicationActions.FIND_WAIVER_INVOICE_ITEMS)
  .map((action) => action.payload)
  .switchMap((payload) => this.billingService.findInvoiceWaiverItems(payload.waiverFinanceApplication, payload.invoice))
  .map((message) => this.waiverFinanceApplicationActions.findInvoiceWaiverItemsSuccess(message));
  
  @Effect() findDebitWaiverItems$ = this.actions$
  .ofType(WaiverFinanceApplicationActions.FIND_WAIVER_DEBIT_ITEMS)
  .map((action) => action.payload)
  .switchMap((payload) => this.billingService.findDebitWaiverItems(payload.waiverFinanceApplication, payload.debitNote))
  .map((message) => this.waiverFinanceApplicationActions.findDebitWaiverItemsSuccess(message));
  
  @Effect() findWaiverByDebitNote$ = this.actions$
  .ofType(WaiverFinanceApplicationActions.FIND_WAIVER_DEBIT_NOTE)
  .map((action) => action.payload)
  .switchMap((waiverFinanceApplication) => this.billingService.findWaiverByDebitNote(waiverFinanceApplication))
  .map((message) => this.waiverFinanceApplicationActions.findWaiverByDebitNoteSuccess(message));

  @Effect() findWaiverByAccountCharge$ = this.actions$
  .ofType(WaiverFinanceApplicationActions.FIND_WAIVER_ACCOUNT_CHARGE)
  .map((action) => action.payload)
  .switchMap((waiverFinanceApplication) => this.billingService.findWaiverByAccountCharge(waiverFinanceApplication))
  .map((message) => this.waiverFinanceApplicationActions.findWaiverByAccountChargeSuccess(message));
  
  @Effect() findWaiverItems$ = this.actions$
  .ofType(WaiverFinanceApplicationActions.FIND_WAIVER_ITEMS)
  .map((action) => action.payload)
  .switchMap((waiverFinanceApplication) => this.billingService.findWaiverItems(waiverFinanceApplication))
  .map((message) => this.waiverFinanceApplicationActions.findWaiverItemsSuccess(message));
  
  @Effect() deleteWaiverAccCharges$ = this.actions$
  .ofType(WaiverFinanceApplicationActions.DELETE_WAIVER_ACC_CHARGES)
  .map((action) => action.payload)
  .switchMap((waiverAccountCharge) => this.billingService.deleteWaiverAccCharges(waiverAccountCharge))
  .map((message) => this.waiverFinanceApplicationActions.deleteWaiverAccChargesSuccess(message))
  .withLatestFrom(this.store$.select(...this.WAIVER_FINANCE_APPLICATION_TASK))
      .map((state) => state[1])
      .map((waiverFinanceApplication) => this.waiverFinanceApplicationActions.findWaiverByAccountCharge(waiverFinanceApplication));
  
  @Effect() deleteWaiverDebitNotes$ = this.actions$
  .ofType(WaiverFinanceApplicationActions.DELETE_WAIVER_DEBIT_NOTE)
  .map((action) => action.payload)
  .switchMap((waiverDebitNote) => this.billingService.deleteWaiverDebitNotes(waiverDebitNote))
  .map((message) => this.waiverFinanceApplicationActions.deleteWaiverDebitNotesSuccess(message))
    .withLatestFrom(this.store$.select(...this.WAIVER_FINANCE_APPLICATION_TASK))
      .map((state) => state[1])
      .map((waiverFinanceApplication) => this.waiverFinanceApplicationActions.findWaiverByDebitNote(waiverFinanceApplication));
  
  @Effect() deleteWaiverInvoices$ = this.actions$
  .ofType(WaiverFinanceApplicationActions.DELETE_WAIVER_INVOICE)
  .map((action) => action.payload)
  .switchMap((waiverInvoice) => this.billingService.deleteWaiverInvoices(waiverInvoice))
  .map((message) => this.waiverFinanceApplicationActions.deleteWaiverInvoicesSuccess(message))
    .withLatestFrom(this.store$.select(...this.WAIVER_FINANCE_APPLICATION_TASK))
      .map((state) => state[1])
      .map((waiverFinanceApplication) => this.waiverFinanceApplicationActions.findWaiversByInvoice(waiverFinanceApplication));
  
  @Effect() deleteWaiverItem$ = this.actions$
  .ofType(WaiverFinanceApplicationActions.DELETE_WAIVER_ITEM)
  .map((action) => action.payload)
  .switchMap((payload) => this.billingService.deleteWaiverItem(payload.waiverFinanceApplication, payload.item))
  .map((message) => this.waiverFinanceApplicationActions.deleteWaiverItemSuccess(message))
  .withLatestFrom(this.store$.select(...this.WAIVER_FINANCE_APPLICATION_TASK))
  .map((state) => state[1])
  .map((waiverFinanceApplication) => this.waiverFinanceApplicationActions.findWaiverItems(waiverFinanceApplication))
  //.do((action) => this.router.navigate(['/secure/billing/waiver-finance-applications/view-task/:taskId', action.payload])).ignoreElements();


}
