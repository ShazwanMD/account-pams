import {
  Component, OnInit, ViewChild, ViewContainerRef,
  ComponentFactoryResolver, ComponentRef,
} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {ReceiptActions} from './receipt.action';
import {Observable} from 'rxjs';
import {BillingModuleState} from '../index';
import {Store} from '@ngrx/store';
import {ReceiptTask} from '../../../shared/model/billing/receipt-task.interface';

@Component({
  selector: 'pams-receipt-task-view',
  templateUrl: './receipt-task-view.page.html',
})
export class ReceiptTaskViewPage implements OnInit {

  private RECEIPT_TASK = 'billingModuleState.receiptTask'.split('.');
  private receiptTask$: Observable<ReceiptTask>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private store: Store<BillingModuleState>,
              private actions: ReceiptActions) {
    this.receiptTask$ = this.store.select(...this.RECEIPT_TASK);
  }

  ngOnInit(): void {
    this.route.params.subscribe((params: {taskId: string}) => {
      let taskId: string = params.taskId;
      this.store.dispatch(this.actions.findReceiptTaskByTaskId(taskId));
    });
  }

  goBack(): void {
    this.router.navigate(['/billing/receipts']);
  }
}

