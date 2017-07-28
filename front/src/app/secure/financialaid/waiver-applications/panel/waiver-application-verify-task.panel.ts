import {Component, Input, OnInit, ViewContainerRef} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MdDialog, MdSnackBar} from '@angular/material';
import {WaiverApplicationActions} from '../waiver-application.action';
import {Store} from '@ngrx/store';
import {FinancialaidModuleState} from '../../index';
import {WaiverApplicationTask} from '../../../../shared/model/financialaid/waiver-application-task.interface';

@Component({
  selector: 'pams-waiver-application-verify-task',
  templateUrl: './waiver-application-verify-task.panel.html',
})

export class WaiverApplicationVerifyTaskPanel implements OnInit {

  @Input() waiverApplicationTask: WaiverApplicationTask;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private viewContainerRef: ViewContainerRef,
              private actions: WaiverApplicationActions,
              private store: Store<FinancialaidModuleState>,
              private dialog: MdDialog,
              private snackBar: MdSnackBar) {
  }

  ngOnInit(): void {
  }

  register() {
    this.store.dispatch(this.actions.completeWaiverApplicationTask(this.waiverApplicationTask));
    this.goBack();
  }

  goBack(): void {
    this.router.navigate(['/secure/financialaid/waiver-applications']);
  }
}
