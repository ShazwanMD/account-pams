import {Component, OnInit, ViewContainerRef, Input} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {MdSnackBar, MdDialog, MdDialogRef, MdDialogConfig} from '@angular/material';
import {Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {BillingModuleState} from '../../index';
import {DebitNoteItemEditorDialog} from '../dialog/debit-note-item-editor.dialog';
import {DebitNoteActions} from '../debit-note.action';
import {DebitNoteTask} from '../../../shared/model/billing/debit-note-task.interface';
import {DebitNoteItem} from '../../../shared/model/billing/debit-note-item.interface';

@Component({
  selector: 'pams-debit-note-draft-task',
  templateUrl: './debit-note-draft-task.panel.html',
})

export class DebitNoteDraftTaskPanel implements OnInit {

  private DEBIT_NOTE_ITEMS: string[] = 'billingModuleState.debitNoteItems'.split('.');
  @Input() debitNoteTask: DebitNoteTask;
  debitNoteItems$: Observable<DebitNoteItem[]>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private viewContainerRef: ViewContainerRef,
              private actions: DebitNoteActions,
              private store: Store<BillingModuleState>,
              private dialog: MdDialog,
              private snackBar: MdSnackBar) {
    this.debitNoteItems$ = this.store.select(...this.DEBIT_NOTE_ITEMS);
  }

  ngOnInit(): void {
    console.log('reference no ' + this.debitNoteTask.referenceNo);
    this.store.dispatch(this.actions.findDebitNoteItems(this.debitNoteTask));
  }

  editItem(item: DebitNoteItem) {
    let config = new MdDialogConfig();
    config.viewContainerRef = this.viewContainerRef;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '60%';
    config.position = {top: '0px'};
    let editorDialogRef = this.dialog.open(DebitNoteItemEditorDialog, config);
    editorDialogRef.componentInstance.debitNoteItem = item;
  }

  register(): void {
    this.store.dispatch(this.actions.completeDebitNoteTask(this.debitNoteTask));
    this.goBack();
  }

  goBack(): void {
    this.router.navigate(['/billing/debit-notes']);
  }
}
