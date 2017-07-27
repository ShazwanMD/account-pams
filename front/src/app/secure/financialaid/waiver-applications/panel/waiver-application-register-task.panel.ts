import {Component, OnInit, ViewContainerRef, Input} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {MdSnackBar, MdDialog, MdDialogRef, MdDialogConfig} from '@angular/material';
import {WaiverApplicationActions} from '../waiver-application.action';
import {Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {FinancialaidModuleState} from '../../index';
import {WaiverApplicationTask} from '../../../../shared/model/financialaid/waiver-application-task.interface';

@Component({
  selector: 'pams-waiver-application-register-task',
  templateUrl: './waiver-application-register-task.panel.html',
})

export class WaiverApplicationRegisterTaskPanel implements OnInit {

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
      this.router.navigate(['/financialaid/waiver-applications']);
  }
}
