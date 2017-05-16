import {Component, OnInit, ChangeDetectionStrategy, state, ViewContainerRef} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {FinancialaidModuleState} from "../index";
import {Store} from "@ngrx/store";
import {WaiverApplicationTask} from "./waiver-application-task.interface";
import {Observable} from "rxjs/Observable";
import {MdDialogConfig, MdDialogRef, MdDialog} from "@angular/material";
import {WaiverApplicationCreatorDialog} from "./dialog/waiver-application-creator.dialog";
import {WaiverApplicationActions} from "./waiver-application.action";


@Component({
  selector: 'pams-waiver-application-center',
  templateUrl: './waiver-application-center.page.html',
})

export class WaiverApplicationCenterPage implements OnInit {

  private ASSIGNED_WAIVER_APPLICATION_TASKS = "financialaidModuleState.assignedWaiverApplicationTasks".split(".")
  private POOLED_WAIVER_APPLICATION_TASKS = "financialaidModuleState.pooledWaiverApplicationTasks".split(".")
  private creatorDialogRef: MdDialogRef<WaiverApplicationCreatorDialog>;

  private assignedWaiverApplicationTasks$: Observable<WaiverApplicationTask>;
  private pooledWaiverApplicationTasks$: Observable<WaiverApplicationTask>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private vcf: ViewContainerRef,
              private actions: WaiverApplicationActions,
              private store: Store<FinancialaidModuleState>,
              private dialog: MdDialog) {

    this.assignedWaiverApplicationTasks$ = this.store.select(...this.ASSIGNED_WAIVER_APPLICATION_TASKS);
    this.pooledWaiverApplicationTasks$ = this.store.select(...this.POOLED_WAIVER_APPLICATION_TASKS);
  }

  showDialog(): void {
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '70%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(WaiverApplicationCreatorDialog, config);
    this.creatorDialogRef.afterClosed().subscribe(res => {
      console.log("close dialog");
      // load something here
    });
  }

  ngOnInit(): void {
    this.store.dispatch(this.actions.findAssignedWaiverApplicationTasks());
    this.store.dispatch(this.actions.findPooledWaiverApplicationTasks());
  }
}
