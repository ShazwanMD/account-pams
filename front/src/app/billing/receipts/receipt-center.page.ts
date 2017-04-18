import {Component, OnInit, ChangeDetectionStrategy, state} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {Store} from "@ngrx/store";
import {Observable} from "rxjs";
import {ReceiptActions} from "./receipt.action";
import {ReceiptTask} from "./receipt-task.interface";
import {BillingModuleState} from "../index";

@Component({
  selector: 'pams-receipt-center',
  templateUrl: './receipt-center.page.html',
})

export class ReceiptCenterPage implements OnInit {

  private receiptTasks$: Observable<ReceiptTask[]>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: ReceiptActions,
              private store: Store<BillingModuleState>) {
    this.receiptTasks$ = this.store.select(state => state.receiptTasks);
  }

  goBack(route: string): void {
    this.router.navigate(['/receipts']);
  }

  view(receipt: ReceiptTask) {
    console.log("receipt: " + receipt.taskId);
    this.router.navigate(['/view-task', receipt.taskId]);
  }

  ngOnInit(): void {
    console.log("find assigned receipt tasks");
    this.store.dispatch(this.actions.findAssignedReceiptTasks());
  }
}

