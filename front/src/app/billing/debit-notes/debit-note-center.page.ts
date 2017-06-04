import {Component, OnInit, ChangeDetectionStrategy, state, ViewContainerRef} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {MdDialogConfig, MdDialogRef, MdDialog} from "@angular/material";
import {Store} from "@ngrx/store";
import {Observable} from "rxjs";
import {BillingModuleState} from "../index";
import {DebitNoteCreatorDialog} from "./dialog/debit-note-creator.dialog";
import {DebitNoteTask} from "./debit-note-task.interface";
import {DebitNoteActions} from "./debit-note.action";
import {DebitNote} from "./debit-note.interface";


@Component({
  selector: 'pams-debit-note-center',
  templateUrl: './debit-note-center.page.html',
})

export class DebitNoteCenterPage implements OnInit {

  private POOLED_DEBIT_NOTE_TASKS: string[] = "billingModuleState.pooledDebitNoteTasks".split(".");
  private ASSIGNED_DEBIT_NOTE_TASKS: string[] = "billingModuleState.assignedDebitNoteTasks".split(".");
  private ARCHIVED_DEBIT_NOTES: string[] = "billingModuleState.archivedDebitNotes".split(".");
  private assignedDebitNoteTasks$: Observable<DebitNoteTask[]>;
  private pooledDebitNoteTasks$: Observable<DebitNoteTask[]>;
  private archivedDebitNotes$: Observable<DebitNote[]>;
  private creatorDialogRef: MdDialogRef<DebitNoteCreatorDialog>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: DebitNoteActions,
              private store: Store<BillingModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog) {
    this.assignedDebitNoteTasks$ = this.store.select(...this.ASSIGNED_DEBIT_NOTE_TASKS);
    this.pooledDebitNoteTasks$ = this.store.select(...this.POOLED_DEBIT_NOTE_TASKS);
    this.archivedDebitNotes$ = this.store.select(...this.ARCHIVED_DEBIT_NOTES);
  }

  ngOnInit(): void {
    console.log("find assigned debit note tasks");
    this.store.dispatch(this.actions.findAssignedDebitNoteTasks());
    this.store.dispatch(this.actions.findPooledDebitNoteTasks());
    this.store.dispatch(this.actions.findArchivedDebitNotes());
  }

  goBack(route: string): void {
    this.router.navigate(['/debit-notes']);
  }

  view(task: DebitNoteTask) {
    console.log("DebitNote: " + task.taskId);
    this.router.navigate(['/billing/debit-notes/debit-note-task-detail', task.taskId]);

  }

  viewDebitNote(debitNote: DebitNote) {
    this.router.navigate(['/billing/debit-notes/debit-note-detail', debitNote.referenceNo]);
  }    

  showDialog(): void {
    console.log("showDialog");
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '60%';
    config.position = {top: '0px'};
    this.creatorDialogRef = this.dialog.open(DebitNoteCreatorDialog, config);
    this.creatorDialogRef.afterClosed().subscribe(res => {
      console.log("close dialog");
      // load something here
    });
  }
}

