import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Store} from '@ngrx/store';
import {Observable} from 'rxjs/Observable';
import {MdDialog, MdDialogConfig, MdDialogRef} from '@angular/material';
import {WaiverApplicationTask} from '../../../shared/model/financialaid/waiver-application-task.interface';
import {WaiverApplication} from '../../../shared/model/financialaid/waiver-application.interface';
import { WaiverFinanceApplicationTask } from "../../../shared/model/billing/waiver-finance-application-task.interface";
import { WaiverFinanceApplication } from "../../../shared/model/billing/waiver-finance-application.interface";
import { WaiverFinanceApplicationCreatorDialog } from "./dialog/waiver-finance-application-creator.dialog";
import { WaiverFinanceApplicationActions } from "./waiver-finance-application.action";
import { BillingModuleState } from "../index";

@Component({
  selector: 'pams-waiver-finance-application-center',
  templateUrl: './waiver-finance-application-center.page.html',
})

export class WaiverFinanceApplicationCenterPage implements OnInit {

  private ASSIGNED_WAIVER_FINANCIAL_APPLICATION_TASKS: string[] = 'BillingModuleState.assignedWaiverFinanceApplicationTasks'.split('.');
  private POOLED_WAIVER_FINANCIAL_APPLICATION_TASKS: string[] = 'BillingModuleState.pooledWaiverFinanceApplicationTasks'.split('.');
  private ARCHIVED_WAIVER_FINANCIAL_APPLICATIONS: string[] = 'BillingModuleState.archivedWaiverFinanceApplications'.split('.');
  private creatorDialogRef: MdDialogRef<WaiverFinanceApplicationCreatorDialog>;

  private assignedWaiverFinanceApplicationTasks$: Observable<WaiverFinanceApplicationTask>;
  private pooledWaiverFinanceApplicationTasks$: Observable<WaiverFinanceApplicationTask>;
  private archivedWaiverFinanceApplications$: Observable<WaiverFinanceApplication>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private vcf: ViewContainerRef,
              private actions: WaiverFinanceApplicationActions,
              private store: Store<BillingModuleState>,
              private dialog: MdDialog) {

    this.assignedWaiverFinanceApplicationTasks$ = this.store.select(...this.ASSIGNED_WAIVER_FINANCIAL_APPLICATION_TASKS);
    this.pooledWaiverFinanceApplicationTasks$ = this.store.select(...this.POOLED_WAIVER_FINANCIAL_APPLICATION_TASKS);
    this.archivedWaiverFinanceApplications$ = this.store.select(...this.ARCHIVED_WAIVER_FINANCIAL_APPLICATIONS);
  }

  showDialog(): void {
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '60%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(WaiverFinanceApplicationCreatorDialog, config);
    this.creatorDialogRef.afterClosed().subscribe((res) => {
      console.log('close dialog');
      // load something here
    });
  }

  claim(task: WaiverApplicationTask) {
    console.log('waiverApplication: ' + task.taskId);
    this.store.dispatch(this.actions.claimWaiverFinanceApplicationTask(task));
  }

  view(task: WaiverApplicationTask) {
    console.log('waiverApplication: ' + task.taskId);
    this.router.navigate(['/secure/financialaid/waiver-applications/view-task', task.taskId]);
  }

  viewWaiverApplication(waiverApplication: WaiverApplication) {
    console.log('WaiverApplication: ' + waiverApplication.referenceNo);
    this.router.navigate(['/secure/financialaid/waiver-applications/waiver-application-detail', waiverApplication.referenceNo]);
  }

  ngOnInit(): void {
    console.log('find assigned/pooled waiver application tasks');
    this.store.dispatch(this.actions.findAssignedWaiverFinanceApplicationTasks());
    this.store.dispatch(this.actions.findPooledWaiverFinanceApplicationTasks());
    this.store.dispatch(this.actions.findArchivedWaiverFinanceApplications());
  }
}
