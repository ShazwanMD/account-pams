import { BillingModuleState } from './../index';
import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Observable} from 'rxjs';
import {Store} from '@ngrx/store';
import {WaiverApplicationTask} from '../../../shared/model/financialaid/waiver-application-task.interface';
import { WaiverFinanceApplicationTask } from "../../../shared/model/billing/waiver-finance-application-task.interface";
import { WaiverFinanceApplicationActions } from "./waiver-finance-application.action";

@Component({
  selector: 'pams-waiver-finance-application-task-view',
  templateUrl: './waiver-finance-application-task-view.page.html',
})
export class WaiverFinanceApplicationTaskViewPage implements OnInit {

  private INVOICE_TASK = 'billingModuleState.waiverFinanceApplicationTask'.split('.');
  private waiverFinanceApplicationTask$: Observable<WaiverFinanceApplicationTask>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private store: Store<BillingModuleState>,
              private actions: WaiverFinanceApplicationActions) {
    this.waiverFinanceApplicationTask$ = this.store.select(...this.INVOICE_TASK);
  }

  ngOnInit(): void {
    this.route.params.subscribe((params: { taskId: string }) => {
      let taskId: string = params.taskId;
      this.store.dispatch(this.actions.findWaiverFinanceApplicationTaskByTaskId(taskId));
    });
  }

  goBack(): void {
    this.router.navigate(['/secure/billing/waiver-finance-applications']);
  }
}

