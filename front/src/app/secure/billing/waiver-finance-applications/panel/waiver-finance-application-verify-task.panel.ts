import { BillingModuleState } from './../../index';
import {Component, Input, OnInit, ViewContainerRef} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MdDialog, MdSnackBar} from '@angular/material';
import {Store} from '@ngrx/store';
import {WaiverApplicationTask} from '../../../../shared/model/financialaid/waiver-application-task.interface';
import { WaiverFinanceApplicationActions } from "../waiver-finance-application.action";
import { WaiverFinanceApplicationTask } from "../../../../shared/model/billing/waiver-finance-application-task.interface";
import { Observable } from "rxjs/Observable";
import { WaiverItem } from "../../../../shared/model/billing/waiver-item.interface";

@Component({
  selector: 'pams-waiver-finance-application-verify-task',
  templateUrl: './waiver-finance-application-verify-task.panel.html',
})

export class WaiverFinanceApplicationVerifyTaskPanel implements OnInit {

  @Input() waiverFinanceApplicationTask: WaiverFinanceApplicationTask;

  private WAIVER_ITEM: string[] = 'billingModuleState.waiverItem'.split('.');
  private waiverItem$: Observable<WaiverItem[]>;
  
  constructor(private router: Router,
              private route: ActivatedRoute,
              private viewContainerRef: ViewContainerRef,
              private actions: WaiverFinanceApplicationActions,
              private store: Store<BillingModuleState>,
              private dialog: MdDialog,
              private snackBar: MdSnackBar) {
      this.waiverItem$ = this.store.select(...this.WAIVER_ITEM);
  }

  ngOnInit(): void {
    this.store.dispatch(this.actions.findWaiverItems(this.waiverFinanceApplicationTask.application));
}

  register() {
    this.store.dispatch(this.actions.completeWaiverFinanceApplicationTask(this.waiverFinanceApplicationTask));
    this.goBack();
  }

  goBack(): void {
    this.router.navigate(['/secure/billing/waiver-finance-applications']);
  }
}
