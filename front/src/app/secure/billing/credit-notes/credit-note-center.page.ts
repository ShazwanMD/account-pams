import {Component, OnInit, ChangeDetectionStrategy, state, ViewContainerRef} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {MdDialogConfig, MdDialogRef, MdDialog} from '@angular/material';
import {Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {BillingModuleState} from '../index';
import {CreditNoteCreatorDialog} from './dialog/credit-note-creator.dialog';
import {CreditNoteActions} from './credit-note.action';
import {CreditNoteTask} from '../../../shared/model/billing/credit-note-task.interface';
import {CreditNote} from '../../../shared/model/billing/credit-note.interface';

@Component({
  selector: 'pams-credit-note-center',
  templateUrl: './credit-note-center.page.html',
})

export class CreditNoteCenterPage implements OnInit {

  private POOLED_CREDIT_NOTE_TASKS: string[] = 'billingModuleState.pooledCreditNoteTasks'.split('.');
  private ASSIGNED_CREDIT_NOTE_TASKS: string[] = 'billingModuleState.assignedCreditNoteTasks'.split('.');
  private ARCHIVED_CREDIT_NOTES: string[] = 'billingModuleState.archivedCreditNotes'.split('.');
  private assignedCreditNoteTasks$: Observable<CreditNoteTask[]>;
  private pooledCreditNoteTasks$: Observable<CreditNoteTask[]>;
  private archivedCreditNotes$: Observable<CreditNote[]>;
  private creatorDialogRef: MdDialogRef<CreditNoteCreatorDialog>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: CreditNoteActions,
              private store: Store<BillingModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {
    this.assignedCreditNoteTasks$ = this.store.select(...this.ASSIGNED_CREDIT_NOTE_TASKS);
    this.pooledCreditNoteTasks$ = this.store.select(...this.POOLED_CREDIT_NOTE_TASKS);
    this.archivedCreditNotes$ = this.store.select(...this.ARCHIVED_CREDIT_NOTES);

  }

  ngOnInit(): void {
    console.log('find assigned credit note tasks');
    this.store.dispatch(this.actions.findAssignedCreditNoteTasks());
    this.store.dispatch(this.actions.findPooledCreditNoteTasks());
    this.store.dispatch(this.actions.findArchivedCreditNotes());
  }

  goBack(route: string): void {
    this.router.navigate(['/credit-notes']);
  }

  viewTask(task: CreditNoteTask) {
    console.log('CreditNote: ' + task.taskId);
    this.router.navigate(['/billing/credit-notes/credit-note-task-detail', task.taskId]);
  }

  claimTask(task: CreditNoteTask) {
    console.log('invoice: ' + task.taskId);
    this.store.dispatch(this.actions.claimCreditNoteTask(task));
  }

  viewCreditNote(creditNote: CreditNote) {
    this.router.navigate(['/billing/credit-notes/credit-note-detail', creditNote.referenceNo]);
  }

  showDialog(): void {
    console.log('showDialog');
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '60%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(CreditNoteCreatorDialog, config);
    this.creatorDialogRef.afterClosed().subscribe((res) => {
      console.log('close dialog');
      // load something here
    });
  }

}

