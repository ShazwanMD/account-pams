import {
  Component, OnInit, ViewChild, ViewContainerRef,
  ComponentFactoryResolver, ComponentRef
} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {ReceiptTask} from "./receipt-task.interface";
import {ReceiptActions} from "./receipt.action";
import {Observable} from "rxjs";
import {BillingModuleState} from "../index";
import {Store} from "@ngrx/store";


@Component({
  selector: 'pams-receipt-view-task',
  templateUrl: './receipt-view-task.page.html',
})
export class ReceiptViewTaskPage implements OnInit {

  private receiptTask$: Observable<ReceiptTask>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private store: Store<BillingModuleState>,
              private actions: ReceiptActions) {
    this.receiptTask$ = this.store.select(state => state.receiptTask)
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


