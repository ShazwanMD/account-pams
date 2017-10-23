import {Component, Input, OnInit, ViewContainerRef} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MdDialog, MdSnackBar} from '@angular/material';
import {WaiverFinanceApplicationActions} from '../waiver-finance-application.action';
import {Store} from '@ngrx/store';
import {BillingModuleState } from '../../index';
import {WaiverApplicationTask} from '../../../../shared/model/financialaid/waiver-application-task.interface';
import { WaiverFinanceApplicationTask } from "../../../../shared/model/billing/waiver-finance-application-task.interface";
import { TdDialogService } from "@covalent/core";
import { Observable } from "rxjs/Observable";
import { WaiverItem } from "../../../../shared/model/billing/waiver-item.interface";

@Component({
  selector: 'pams-waiver-finance-application-register-task',
  templateUrl: './waiver-finance-application-register-task.panel.html',
})

export class WaiverFinanceApplicationRegisterTaskPanel implements OnInit {

  @Input() waiverFinanceApplicationTask: WaiverFinanceApplicationTask;
  
  private WAIVER_ITEM: string[] = 'billingModuleState.waiverItem'.split('.');
  private waiverItem$: Observable<WaiverItem[]>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private viewContainerRef: ViewContainerRef,
              private actions: WaiverFinanceApplicationActions,
              private store: Store<BillingModuleState>,
              private dialog: MdDialog,
              private _dialogService: TdDialogService,
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
