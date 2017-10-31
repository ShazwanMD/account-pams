import {ChangeDetectionStrategy, Component, Input, ViewContainerRef} from '@angular/core';
import {MdDialog, MdDialogConfig, MdDialogRef} from '@angular/material';
import {Store} from '@ngrx/store';
import {ActivatedRoute, Router} from '@angular/router';
import {TdDialogService} from '@covalent/core';
import {BillingModuleState} from '../../index';
import {Account} from '../../../../shared/model/account/account.interface';
import {AccountChargeTransaction} from '../../../../shared/model/account/account-charge-transaction.interface';
import { ReportActions } from '../../../../shared/report/report.action';
import { ReceiptActions } from '../receipt.action';
import { Receipt } from '../../../../shared/model/billing/receipt.interface';


@Component({
  selector: 'pams-receipt-action',
  templateUrl: './receipt-action.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ReceiptActionComponent {

  @Input() receipt: Receipt;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private store: Store<BillingModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog,
              private _dialogService: TdDialogService,
              private reportActions: ReportActions,
              private actions: ReceiptActions) {
  }

 downloadReport(reportId,parameterReport:Receipt): void {
   let repParam = reportId+'&receipt_ref_no='+ parameterReport.referenceNo;  
   this.store.dispatch(this.reportActions.downloadReport(repParam));
   }
}
