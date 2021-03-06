import {ChangeDetectionStrategy, Component, Input, ViewContainerRef} from '@angular/core';
import {Invoice} from '../../../../shared/model/billing/invoice.interface';
import {MdDialog, MdDialogConfig, MdDialogRef} from '@angular/material';
import {BillingModuleState} from '../../index';
import {InvoiceActions} from '../invoice.action';
import {Store} from '@ngrx/store';
import {ActivatedRoute, Router} from '@angular/router';
import {CreditNoteCreatorDialog} from '../../credit-notes/dialog/credit-note-creator.dialog';
import {DebitNoteCreatorDialog} from '../../debit-notes/dialog/debit-note-creator.dialog';
import {TdDialogService} from '@covalent/core';
import { ReportActions } from '../../../../shared/report/report.action';

@Component({
  selector: 'pams-invoice-action',
  templateUrl: './invoice-action.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class InvoiceActionComponent {

  private debitCreatorDialogRef: MdDialogRef<DebitNoteCreatorDialog>;
  private creditCreatorDialogRef: MdDialogRef<CreditNoteCreatorDialog>;
  @Input() invoice: Invoice;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private store: Store<BillingModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog,
              private _dialogService: TdDialogService,
              private reportActions: ReportActions,
              private actions: InvoiceActions) {
  }

  showDialog(): void {
    console.log('showDialog');
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '65%';
    config.position = {top: '0px'};
    this.debitCreatorDialogRef = this.dialog.open(DebitNoteCreatorDialog, config);
    this.debitCreatorDialogRef.componentInstance.invoice = this.invoice;
    this.debitCreatorDialogRef.afterClosed().subscribe((res) => {
    console.log('close dialog');
    this.route.params.subscribe((params: { referenceNo: string }) => {
    let referenceNo: string = params.referenceNo;
    this.store.dispatch(this.actions.findInvoiceByReferenceNo(referenceNo));
       });

    });
  }

  showDialog1(): void {
    console.log('showDialog');
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '65%';
    config.position = {top: '0px'};
    this.creditCreatorDialogRef = this.dialog.open(CreditNoteCreatorDialog, config);
    this.creditCreatorDialogRef.componentInstance.invoice = this.invoice;
    this.creditCreatorDialogRef.afterClosed().subscribe((res) => {
     console.log('close dialog');
    this.route.params.subscribe((params: { referenceNo: string }) => {
    let referenceNo: string = params.referenceNo;
    this.store.dispatch(this.actions.findInvoiceByReferenceNo(referenceNo));
       });

    });
  }

  downloadReport(reportId,parameterReport:Invoice): void {
    let repParam = reportId+'&inv_ref_no='+ parameterReport.referenceNo;  
    this.store.dispatch(this.reportActions.downloadReport(repParam));
  }

}
