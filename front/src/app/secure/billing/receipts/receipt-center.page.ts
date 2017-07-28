import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import {Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {ReceiptActions} from './receipt.action';
import {ReceiptTask} from '../../../shared/model/billing/receipt-task.interface';
import {BillingModuleState} from '../index';
import {ReceiptTaskCreatorDialog} from './dialog/receipt-task-creator.dialog';
import {MdDialog, MdDialogConfig, MdDialogRef} from '@angular/material';
import {Receipt} from '../../../shared/model/billing/receipt.interface';

@Component({
  selector: 'pams-receipt-center',
  templateUrl: './receipt-center.page.html',
})

export class ReceiptCenterPage implements OnInit {

  private ASSIGNED_RECEIPT_TASKS = 'billingModuleState.assignedReceiptTasks'.split('.');
  private POOLED_RECEIPT_TASKS = 'billingModuleState.pooledReceiptTasks'.split('.');
  private ARCHIVED_RECEIPTS = 'billingModuleState.archivedReceipts'.split('.');
  private assignedReceiptTasks$: Observable<ReceiptTask[]>;
  private pooledReceiptTasks$: Observable<ReceiptTask[]>;
  private archivedReceipts$: Observable<Receipt[]>;
  private creatorDialogRef: MdDialogRef<ReceiptTaskCreatorDialog>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: ReceiptActions,
              private store: Store<BillingModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {
    this.assignedReceiptTasks$ = this.store.select(...this.ASSIGNED_RECEIPT_TASKS);
    this.pooledReceiptTasks$ = this.store.select(...this.POOLED_RECEIPT_TASKS);
    this.archivedReceipts$ = this.store.select(...this.ARCHIVED_RECEIPTS);
  }

  goBack(route: string): void {
    this.router.navigate(['/receipts']);
  }

  view(receipt: ReceiptTask) {
    console.log('receipt: ' + receipt.taskId);
    this.router.navigate(['/secure/billing/receipts/view-task', receipt.taskId]);
  }

  claim(task: ReceiptTask) {
    console.log('receipt: ' + task.taskId);
    this.store.dispatch(this.actions.claimReceiptTask(task));
  }

  showDialog(): void {
    console.log('showDialog');
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '90%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(ReceiptTaskCreatorDialog, config);
    this.creatorDialogRef.afterClosed().subscribe((res) => {
      console.log('close dialog');
      // load something here
    });
  }

  ngOnInit(): void {
    console.log('find assigned receipt tasks');
    this.store.dispatch(this.actions.findAssignedReceiptTasks());
    this.store.dispatch(this.actions.findPooledReceiptTasks());
  }
}
