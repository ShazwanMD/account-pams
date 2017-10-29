import {Component, Input, OnInit, ViewContainerRef} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MdDialog, MdSnackBar, MdDialogConfig, MdDialogRef,} from '@angular/material';
import {WaiverFinanceApplicationActions} from '../waiver-finance-application.action';
import {Store} from '@ngrx/store';
import {BillingModuleState } from '../../index';
import {WaiverApplicationTask} from '../../../../shared/model/financialaid/waiver-application-task.interface';
import { WaiverFinanceApplicationTask } from "../../../../shared/model/billing/waiver-finance-application-task.interface";
import { TdDialogService } from "@covalent/core";
import { Observable } from "rxjs/Observable";
import { WaiverItem } from "../../../../shared/model/billing/waiver-item.interface";
import { WaiverApplicationEditorDialog } from '../dialog/waiver-application-editor.dialog';

@Component({
  selector: 'pams-waiver-finance-application-register-task',
  templateUrl: './waiver-finance-application-register-task.panel.html',
})

export class WaiverFinanceApplicationRegisterTaskPanel implements OnInit {

  @Input() waiverFinanceApplicationTask: WaiverFinanceApplicationTask;
  
  private WAIVER_ITEM: string[] = 'billingModuleState.waiverItem'.split('.');
  private waiverItem$: Observable<WaiverItem[]>;
  private creatorDialogRef: MdDialogRef<WaiverApplicationEditorDialog>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private viewContainerRef: ViewContainerRef,
              private actions: WaiverFinanceApplicationActions,
              private store: Store<BillingModuleState>,
              private dialog: MdDialog,
              private _dialogService: TdDialogService,
              private vcf: ViewContainerRef,
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
  
  cancel(): void {
      console.log("Waiver Finance Application" + this.waiverFinanceApplicationTask.application);
      this._dialogService.openConfirm({
        message: 'Cancel Waiver Finance Application ' + this.waiverFinanceApplicationTask.application.referenceNo + ' ?',
        disableClose: false, // defaults to false
        viewContainerRef: this.viewContainerRef,
        cancelButton: 'No', //OPTIONAL, defaults to 'CANCEL'
        acceptButton: 'Yes', //OPTIONAL, defaults to 'ACCEPT'
      }).afterClosed().subscribe((accept: boolean) => {
        if (accept) {
          this.store.dispatch(this.actions.removeWaiverFinanceApplicationTask(this.waiverFinanceApplicationTask));
          this.router.navigate(['/secure/billing/waiver-finance-applications']);
        } else {
          // DO SOMETHING ELSE
        }
      });
    }
}
