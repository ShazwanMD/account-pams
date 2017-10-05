import {Component, Input, OnInit, ViewContainerRef} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MdDialog, MdDialogConfig, MdDialogRef, MdSnackBar} from '@angular/material';
import {WaiverApplicationActions} from '../waiver-application.action';
import {Store} from '@ngrx/store';
import {FinancialaidModuleState} from '../../index';
import {WaiverApplicationEditorDialog} from '../dialog/waiver-application-editor.dialog';
import {Observable} from 'rxjs/Observable';
import {WaiverApplicationTask} from '../../../../shared/model/financialaid/waiver-application-task.interface';
import {WaiverApplication} from '../../../../shared/model/financialaid/waiver-application.interface';
import { TdDialogService } from "@covalent/core";

@Component({
  selector: 'pams-waiver-application-draft-task',
  templateUrl: './waiver-application-draft-task.panel.html',
})

export class WaiverApplicationDraftTaskPanel implements OnInit {

  private WAIVER_APPLICATION: string[] = 'financialaidModuleState.waiverApplication'.split('.');
  private waiverApplication$: Observable<WaiverApplication>;
  private creatorDialogRef: MdDialogRef<WaiverApplicationEditorDialog>;

  @Input() waiverApplicationTask: WaiverApplicationTask;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private viewContainerRef: ViewContainerRef,
              private actions: WaiverApplicationActions,
              private store: Store<FinancialaidModuleState>,
              private dialog: MdDialog,
              private _dialogService: TdDialogService,
              private vcf: ViewContainerRef,
              private snackBar: MdSnackBar) {

    this.waiverApplication$ = this.store.select(...this.WAIVER_APPLICATION);
  }

  ngOnInit(): void {
    //this.store.dispatch(this.actions.findW(this.invoice))
  }

  register() {
    this.store.dispatch(this.actions.completeWaiverApplicationTask(this.waiverApplicationTask));
    this.goBack();
  }

  goBack(): void {
    this.router.navigate(['/secure/financialaid/waiver-applications']);
  }

  showDialog(): void {
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
        viewContainerRef: this.viewContainerRef,
        cancelButton: 'No', //OPTIONAL, defaults to 'CANCEL'
        acceptButton: 'Yes', //OPTIONAL, defaults to 'ACCEPT'
      }).afterClosed().subscribe((accept: boolean) => {
        if (accept) {
          this.store.dispatch(this.actions.removeWaiverApplicationTask(this.waiverApplicationTask.application));
          this.router.navigate(['/secure/financialaid/waiver-applications']);
        } else {
          // DO SOMETHING ELSE
        }
      });
    }
}
