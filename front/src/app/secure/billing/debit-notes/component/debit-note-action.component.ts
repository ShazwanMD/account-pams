import {ChangeDetectionStrategy, Component, Input, ViewContainerRef} from '@angular/core';
import {MdDialog} from '@angular/material';
import {BillingModuleState} from '../../index';
import {Store} from '@ngrx/store';
import {ActivatedRoute, Router} from '@angular/router';
import {DebitNoteActions} from '../debit-note.action';
import {DebitNote} from '../../../../shared/model/billing/debit-note.interface';
@Component({
  selector: 'pams-debit-note-action',
  templateUrl: './debit-note-action.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class DebitNoteActionComponent {
  @Input() debitNote: DebitNote;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private store: Store<BillingModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog,
              private actions: DebitNoteActions) {
  }

}
