import {Component, Input, OnInit, ViewContainerRef} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MdDialog, MdSnackBar} from '@angular/material';
import {WaiverApplicationActions} from '../waiver-application.action';
import {Store} from '@ngrx/store';
import {FinancialaidModuleState} from '../../index';
import {WaiverApplicationTask} from '../../../../shared/model/financialaid/waiver-application-task.interface';
import { TdDialogService } from "@covalent/core";

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
              private _dialogService: TdDialogService,
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
  
  cancel(): void {
      console.log("Waiver Application" + this.waiverApplicationTask.application);
      this._dialogService.openConfirm({
        message: 'Cancel Waiver Application ' + this.waiverApplicationTask.application.referenceNo + ' ?',
        disableClose: false, // defaults to false
        viewContainerRef: this.viewContainerRef,
        cancelButton: 'No', //OPTIONAL, defaults to 'CANCEL'
        acceptButton: 'Yes', //OPTIONAL, defaults to 'ACCEPT'
      }).afterClosed().subscribe((accept: boolean) => {
        if (accept) {
          this.store.dispatch(this.actions.removeWaiverApplicationTask(this.waiverApplicationTask));
          this.router.navigate(['/secure/financialaid/waiver-applications']);
        } else {
          // DO SOMETHING ELSE
        }
      });
    }
}
