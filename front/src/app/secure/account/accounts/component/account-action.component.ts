import {ChangeDetectionStrategy, Component, Input, ViewContainerRef} from '@angular/core';
import {MdDialog, MdDialogConfig, MdDialogRef} from '@angular/material';
import {Store} from '@ngrx/store';
import {ActivatedRoute, Router} from '@angular/router';
import {TdDialogService} from '@covalent/core';
import {AccountActions} from '../account.action';
import {AccountModuleState} from '../../index';
import {Account} from '../../../../shared/model/account/account.interface';
import {AccountChargeTransaction} from '../../../../shared/model/account/account-charge-transaction.interface';
import { ReportActions } from '../../../../shared/report/report.action';

@Component({
  selector: 'pams-account-action',
  templateUrl: './account-action.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AccountActionComponent {

  @Input() account: Account;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private store: Store<AccountModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog,
              private _dialogService: TdDialogService,
              private reportActions: ReportActions,
              private actions: AccountActions) {
  }

  reviseAccount(): void {
    console.log('revise account');
    this.store.dispatch(this.actions.reviseAccount(this.account));
  }

 downloadReport(reportId,parameterReport:Account): void {
   let repParam = reportId+'&IDENTITY_NO='+ parameterReport.actor.identityNo;  
   this.store.dispatch(this.reportActions.downloadReport(repParam));
   }
}
