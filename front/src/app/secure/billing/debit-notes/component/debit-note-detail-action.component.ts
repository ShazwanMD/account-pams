import {ChangeDetectionStrategy, Component, Input, ViewContainerRef} from '@angular/core';
import {Invoice} from '../../../../shared/model/billing/invoice.interface';
import {MdDialog, MdDialogConfig, MdDialogRef} from '@angular/material';
import {BillingModuleState} from '../../index';
import {Store} from '@ngrx/store';
import {ActivatedRoute, Router} from '@angular/router';
import {CreditNoteCreatorDialog} from '../../credit-notes/dialog/credit-note-creator.dialog';
import {DebitNoteCreatorDialog} from '../../debit-notes/dialog/debit-note-creator.dialog';
import {TdDialogService} from '@covalent/core';
import { ReportActions } from '../../../../shared/report/report.action';
import {DebitNote} from '../../../../shared/model/billing/debit-note.interface';

@Component({
  selector: 'pams-debit-note-detail-action',
  templateUrl: './debit-note-detail-action.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class DebitNoteDetailActionComponent {

    @Input() debitNote: DebitNote;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private store: Store<BillingModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog,
              private _dialogService: TdDialogService,
              private reportActions: ReportActions) {
  }


}
