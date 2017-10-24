import {Component, Input, OnInit, ViewContainerRef} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MdDialog, MdSnackBar, MdDialogRef, MdDialogConfig} from '@angular/material';
import {WaiverApplicationActions} from '../waiver-application.action';
import {Store} from '@ngrx/store';
import {FinancialaidModuleState} from '../../index';
import {WaiverApplicationTask} from '../../../../shared/model/financialaid/waiver-application-task.interface';
import { TdDialogService } from "@covalent/core";
import { WaiverApplicationEditorDialog } from "../dialog/waiver-application-editor.dialog";

@Component({
  selector: 'pams-waiver-application-register-task',
  templateUrl: './waiver-application-register-task.panel.html',
})

export class WaiverApplicationRegisterTaskPanel implements OnInit {

  @Input() waiverApplicationTask: WaiverApplicationTask;
  
  private creatorDialogRef: MdDialogRef<WaiverApplicationEditorDialog>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private vcf: ViewContainerRef,
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
  
  showDialog(): void {
      console.log("Waiver Application" + this.waiverApplicationTask.application.referenceNo);
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '40%';
    config.position = {top: '65px'};
    this.creatorDialogRef = this.dialog.open(WaiverApplicationEditorDialog, config);
    this.creatorDialogRef.componentInstance.application = this.waiverApplicationTask.application;

    // close
    this.creatorDialogRef.afterClosed().subscribe((res) => {
      console.log('close dialog');
      // load something here
    });
  }
  
  cancel(): void {
      console.log("Waiver Application" + this.waiverApplicationTask.application);
      this._dialogService.openConfirm({
        message: 'Cancel Waiver Application ' + this.waiverApplicationTask.application.referenceNo + ' ?',
        disableClose: false, // defaults to false
        viewContainerRef: this.vcf,
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
