import {ChangeDetectionStrategy, Component, Input, ViewContainerRef} from '@angular/core';
import {MdDialog} from '@angular/material';
import {BillingModuleState} from '../../index';
import {Store} from '@ngrx/store';
import {ActivatedRoute, Router} from '@angular/router';
import {CreditNote} from '../../../../shared/model/billing/credit-note.interface';
import {CreditNoteActions} from '../credit-note.action';
@Component({
  selector: 'pams-credit-note-action',
  templateUrl: './credit-note-action.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class CreditNoteActionComponent {
  @Input() creditNote: CreditNote;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private store: Store<BillingModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog,
              private actions: CreditNoteActions) {
  }

}
