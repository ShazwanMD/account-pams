import {Component, OnInit, ViewContainerRef, Input} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import {Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {KnockoffActions} from './knockoff.action';
import {KnockoffTask} from '../../../shared/model/billing/knockoff-task.interface';
import {BillingModuleState} from '../index';
import {MdDialog, MdDialogConfig, MdDialogRef} from '@angular/material';
import { Knockoff } from "../../../shared/model/billing/knockoff.interface";

@Component({
  selector: 'pams-knockoff-center',
  templateUrl: './knockoff-center.page.html',
})

export class KnockoffCenterPage implements OnInit {

  private ASSIGNED_KNOCKOFF_TASKS : string[] = 'billingModuleState.assignedKnockoffTasks'.split('.');
  private POOLED_KNOCKOFF_TASKS = 'billingModuleState.pooledKnockoffTasks'.split('.');
  private ARCHIVED_KNOCKOFF = 'billingModuleState.archivedKnockoffs'.split('.');
  
  private KNOCKOFF = 'billingModuleState.knockoffs'.split('.');
  private knockoff$: Observable<Knockoff[]>;

  private assignedKnockoffTasks$: Observable<KnockoffTask[]>;
  private pooledKnockoffTasks$: Observable<KnockoffTask[]>;
  private archivedKnockoffs$: Observable<Knockoff[]>;

  
  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: KnockoffActions,
              private store: Store<BillingModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {
    this.assignedKnockoffTasks$ = this.store.select(...this.ASSIGNED_KNOCKOFF_TASKS);
    this.pooledKnockoffTasks$ = this.store.select(...this.POOLED_KNOCKOFF_TASKS);
    this.archivedKnockoffs$ = this.store.select(...this.ARCHIVED_KNOCKOFF);
    this.knockoff$ = this.store.select(...this.KNOCKOFF);
  }

  goBack(route: string): void {
    this.router.navigate(['/knokoffs']);
  }

  claimTask(task: KnockoffTask) {
    console.log('Knockoff: ' + task.taskId);
    this.store.dispatch(this.actions.claimKnockoffTask(task));
  }

  viewTask(task: KnockoffTask) {
    console.log('Task: ' + task.taskId);
    this.router.navigate(['/secure/billing/knockoffs/knockoff-task-detail', task.taskId]);
  }

  viewKnockoff(knockoff: Knockoff) {
    console.log('Knockoff: ' + knockoff.referenceNo);
    this.router.navigate(['/secure/billing/knockoffs/knockoff-detail', knockoff.referenceNo]);
  }

//  showDialog(): void {
//    console.log('showDialog');
//    let config: MdDialogConfig = new MdDialogConfig();
//    config.viewContainerRef = this.vcf;
//    config.role = 'dialog';
//    config.width = '50%';
//    config.height = '65%';
//    config.position = {top: '0px'};
//    this.creatorDialogRef = this.dialog.open(InvoiceTaskCreatorDialog, config);
//    this.creatorDialogRef.afterClosed().subscribe((res) => {
//      console.log('close dialog');
//      // load something here
//    });
//  }

  ngOnInit(): void {
    console.log('find assigned knockoff tasks');
    this.store.dispatch(this.actions.findAssignedKnockoffTasks());
    this.store.dispatch(this.actions.findPooledKnockoffTasks());
    this.store.dispatch(this.actions.findArchivedknockoffs());
  }
}

