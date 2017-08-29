import {Component, Input, OnInit, ViewContainerRef} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MdDialog, MdDialogConfig, MdDialogRef, MdSnackBar} from '@angular/material';
import {Store} from '@ngrx/store';
import {BillingModuleState } from '../../index';
import {Observable} from 'rxjs/Observable';
import {WaiverApplicationTask} from '../../../../shared/model/financialaid/waiver-application-task.interface';
import {WaiverApplication} from '../../../../shared/model/financialaid/waiver-application.interface';
import { WaiverFinanceApplication } from "../../../../shared/model/billing/waiver-finance-application.interface";
import { WaiverFinanceApplicationTask } from "../../../../shared/model/billing/waiver-finance-application-task.interface";
import { WaiverFinanceApplicationActions } from "../waiver-finance-application.action";
import { WaiverApplicationEditorDialog } from "../../../financialaid/waiver-applications/dialog/waiver-application-editor.dialog";

@Component({
  selector: 'pams-waiver-finance-application-draft-task',
  templateUrl: './waiver-finance-application-draft-task.panel.html',
})

export class WaiverFinanceApplicationDraftTaskPanel implements OnInit {

  private WAIVER_FINANCE_APPLICATION: string[] = 'financialaidModuleState.waiverFinanceApplication'.split('.');
  private waiverFinanceApplication$: Observable<WaiverFinanceApplication>;
  private creatorDialogRef: MdDialogRef<WaiverApplicationEditorDialog>;

  @Input() waiverFinanceApplicationTask: WaiverFinanceApplicationTask;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private viewContainerRef: ViewContainerRef,
              private actions: WaiverFinanceApplicationActions,
              private store: Store<BillingModuleState>,
              private dialog: MdDialog,
              private vcf: ViewContainerRef,
              private snackBar: MdSnackBar) {

    this.waiverFinanceApplication$ = this.store.select(...this.WAIVER_FINANCE_APPLICATION);
  }

  ngOnInit(): void {
    //this.store.dispatch(this.actions.findW(this.invoice))
  }

  register() {
    this.store.dispatch(this.actions.completeWaiverFinanceApplicationTask(this.waiverFinanceApplicationTask));
    this.goBack();
  }

  goBack(): void {
    this.router.navigate(['/secure/billing/waiver-finance-applications']);
  }

  showDialog(): void {
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '40%';
    config.position = {top: '65px'};
    this.creatorDialogRef = this.dialog.open(WaiverApplicationEditorDialog, config);
    this.creatorDialogRef.componentInstance.application = this.waiverFinanceApplicationTask.application;

    // close
    this.creatorDialogRef.afterClosed().subscribe((res) => {
      console.log('close dialog');
      // load something here
    });
  }

}