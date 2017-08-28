import {Component, Input, OnInit, ViewContainerRef} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MdDialog, MdSnackBar} from '@angular/material';
import {WaiverFinanceApplicationActions} from '../waiver-finance-application.action';
import {Store} from '@ngrx/store';
import {BillingModuleState } from '../../index';
import {WaiverApplicationTask} from '../../../../shared/model/financialaid/waiver-application-task.interface';
import { WaiverFinanceApplicationTask } from "../../../../shared/model/billing/waiver-finance-application-task.interface";

@Component({
  selector: 'pams-waiver-finance-application-register-task',
  templateUrl: './waiver-finance-application-register-task.panel.html',
})

export class WaiverFinanceApplicationRegisterTaskPanel implements OnInit {

  @Input() waiverFinanceApplicationTask: WaiverFinanceApplicationTask;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private viewContainerRef: ViewContainerRef,
              private actions: WaiverFinanceApplicationActions,
              private store: Store<BillingModuleState>,
              private dialog: MdDialog,
              private snackBar: MdSnackBar) {
  }

  ngOnInit(): void {
  }

  register() {
    this.store.dispatch(this.actions.completeWaiverFinanceApplicationTask(this.waiverFinanceApplicationTask));
    this.goBack();
  }

  goBack(): void {
    this.router.navigate(['/secure/billing/waiver-finance-applications']);
  }
}
