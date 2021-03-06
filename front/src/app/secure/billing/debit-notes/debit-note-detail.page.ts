import {Component, OnInit, ViewContainerRef} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Observable} from 'rxjs';
import {BillingModuleState} from '../index';
import {Store} from '@ngrx/store';
import {MdDialog} from '@angular/material';
import {DebitNoteActions} from './debit-note.action';
import {DebitNoteTask} from '../../../shared/model/billing/debit-note-task.interface';
import {DebitNoteItem} from '../../../shared/model/billing/debit-note-item.interface';

@Component({
  selector: 'pams-debit-note-detail',
  templateUrl: './debit-note-detail.page.html',
})
export class DebitNoteDetailPage implements OnInit {

  private DEBIT_NOTES = 'billingModuleState.debitNote'.split('.');
  private DEBIT_NOTE_ITEMS = 'billingModuleState.debitNoteItems'.split('.');
  private debitNotes$: Observable<DebitNoteTask[]>;
  private debitNoteItems$: Observable<DebitNoteItem[]>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private store: Store<BillingModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog,
              private actions: DebitNoteActions) {

    this.debitNotes$ = this.store.select(...this.DEBIT_NOTES);
    this.debitNoteItems$ = this.store.select(...this.DEBIT_NOTE_ITEMS);
  }

  ngOnInit(): void {
    this.route.params.subscribe((params: { referenceNo: string }) => {
      let referenceNo: string = params.referenceNo;
      this.store.dispatch(this.actions.findDebitNoteByReferenceNo(referenceNo));
    });
  }

  goBack(): void {
    this.router.navigate(['/secure/billing/debit-notes']);
  }
}

