import {Component, OnInit, ChangeDetectionStrategy, state, ViewContainerRef} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {FinancialaidModuleState} from "../index";
import {Store} from "@ngrx/store";
import {WaiverApplicationTask} from "./waiver-application-task.interface";
import {Observable} from "rxjs/Observable";
import {MdDialogConfig, MdDialogRef, MdDialog} from "@angular/material";
import {WaiverApplicationCreatorDialog} from "./dialog/waiver-application-creator.dialog";
import {WaiverApplicationActions} from "./waiver-application.action";
import { WaiverApplication } from "./waiver-application.interface";


@Component({
  selector: 'pams-waiver-application-center',
  templateUrl: './waiver-application-center.page.html',
})

export class WaiverApplicationCenterPage implements OnInit {

  private ASSIGNED_WAIVER_APPLICATION_TASKS = "financialaidModuleState.assignedWaiverApplicationTasks".split(".")
  private POOLED_WAIVER_APPLICATION_TASKS = "financialaidModuleState.pooledWaiverApplicationTasks".split(".")
  private ARCHIVED_WAIVER_APPLICATIONS = "financialaidModuleState.archivedWaiverApplications".split(".");
  private creatorDialogRef: MdDialogRef<WaiverApplicationCreatorDialog>;

  private assignedWaiverApplicationTasks$: Observable<WaiverApplicationTask>;
  private pooledWaiverApplicationTasks$: Observable<WaiverApplicationTask>;
  private archivedWaiverApplications$: Observable<WaiverApplication>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private vcf: ViewContainerRef,
              private actions: WaiverApplicationActions,
              private store: Store<FinancialaidModuleState>,
              private dialog: MdDialog) {

    this.assignedWaiverApplicationTasks$ = this.store.select(...this.ASSIGNED_WAIVER_APPLICATION_TASKS);
    this.pooledWaiverApplicationTasks$ = this.store.select(...this.POOLED_WAIVER_APPLICATION_TASKS);
    this.archivedWaiverApplications$ = this.store.select(...this.ARCHIVED_WAIVER_APPLICATIONS);
  }

  showDialog(): void {
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '80%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(WaiverApplicationCreatorDialog, config);
    this.creatorDialogRef.afterClosed().subscribe(res => {
      console.log("close dialog");
      // load something here
    });
  }

  claim(task: WaiverApplicationTask) {
    console.log("waiverApplication: " + task.taskId);
    this.store.dispatch(this.actions.claimWaiverApplicationTask(task));
  }

  view(task: WaiverApplicationTask) {
    console.log("waiverApplication: " + task.taskId);
    this.router.navigate(['/financialaid/waiver-applications/view-task', task.taskId]);
  }

  viewWaiverApplication(waiverApplication: WaiverApplication) {
      console.log("WaiverApplication: " + waiverApplication.referenceNo);
      this.router.navigate(['/financialaid/waiver-applications/invoice-detail', waiverApplication.referenceNo]);
  }

  ngOnInit(): void {
    console.log("find assigned/pooled waiver application tasks");
    this.store.dispatch(this.actions.findAssignedWaiverApplicationTasks());
    this.store.dispatch(this.actions.findPooledWaiverApplicationTasks());
    this.store.dispatch(this.actions.findArchivedWaiverApplications());
  }
}
