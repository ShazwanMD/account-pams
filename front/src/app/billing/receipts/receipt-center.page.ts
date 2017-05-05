import {Component, OnInit, ChangeDetectionStrategy, state, ViewContainerRef} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {Store} from "@ngrx/store";
import {Observable} from "rxjs";
import {ReceiptActions} from "./receipt.action";
import {ReceiptTask} from "./receipt-task.interface";
import {BillingModuleState} from "../index";
import {ReceiptTaskCreatorDialog} from "./dialog/receipt-task-creator.dialog";
import {MdDialogRef, MdDialog, MdDialogConfig} from "@angular/material";

@Component({
  selector: 'pams-receipt-center',
  templateUrl: './receipt-center.page.html',
})

export class ReceiptCenterPage implements OnInit {

  private ASSIGNED_RECEIPT_TASKS = "billingModuleState.assignedReceiptTasks".split(".");
  private POOLED_RECEIPT_TASKS = "billingModuleState.pooledReceiptTasks".split(".");
  private assignedReceiptTasks$: Observable<ReceiptTask[]>;
  private pooledReceiptTasks$: Observable<ReceiptTask[]>;
  private creatorDialogRef: MdDialogRef<ReceiptTaskCreatorDialog>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: ReceiptActions,
              private store: Store<BillingModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {
    this.assignedReceiptTasks$ = this.store.select(...this.ASSIGNED_RECEIPT_TASKS);
    this.pooledReceiptTasks$ = this.store.select(...this.POOLED_RECEIPT_TASKS);
  }

  goBack(route: string): void {
    this.router.navigate(['/receipts']);
  }

  view(receipt: ReceiptTask) {
    console.log("receipt: " + receipt.taskId);
    this.router.navigate(['/view-task', receipt.taskId]);
  }

  showDialog(): void {
    console.log("showDialog");
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '60%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(ReceiptTaskCreatorDialog, config);
    this.creatorDialogRef.afterClosed().subscribe(res => {
      console.log("close dialog");
      // load something here
    });
  }

  ngOnInit(): void {
    console.log("find assigned receipt tasks");
    this.store.dispatch(this.actions.findAssignedReceiptTasks());
  }
}

