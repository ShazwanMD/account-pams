import {Component, Input, OnInit, ViewContainerRef} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MdDialog, MdDialogConfig, MdSnackBar} from '@angular/material';
import {Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {BillingModuleState} from '../../index';
import {DebitNoteItemEditorDialog} from '../dialog/debit-note-item-editor.dialog';
import {DebitNoteActions} from '../debit-note.action';
import {DebitNoteTask} from '../../../../shared/model/billing/debit-note-task.interface';
import {DebitNoteItem} from '../../../../shared/model/billing/debit-note-item.interface';
import { TdDialogService } from "@covalent/core";

@Component({
  selector: 'pams-debit-note-register-task',
  templateUrl: './debit-note-register-task.panel.html',
})

export class DebitNoteRegisterTaskPanel implements OnInit {

  private DEBIT_NOTE_ITEMS = 'billingModuleState.debitNoteItems'.split('.');
  @Input() debitNoteTask: DebitNoteTask;
  debitNoteItems$: Observable<DebitNoteItem[]>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private viewContainerRef: ViewContainerRef,
              private actions: DebitNoteActions,
              private store: Store<BillingModuleState>,
              private dialog: MdDialog,
              private _dialogService: TdDialogService,
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

  verify() {
    this.store.dispatch(this.actions.completeDebitNoteTask(this.debitNoteTask));
    this.goBack();
  }

  goBack(): void {
    this.router.navigate(['/secure/billing/debit-notes']);
  }
  
  cancel(): void {
      console.log("Debit Note " + this.debitNoteTask.debitNote);
      this._dialogService.openConfirm({
        message: 'Cancel Debit Note ' + this.debitNoteTask.debitNote.referenceNo + ' ?',
        disableClose: false, // defaults to false
        viewContainerRef: this.viewContainerRef,
        cancelButton: 'No', //OPTIONAL, defaults to 'CANCEL'
        acceptButton: 'Yes', //OPTIONAL, defaults to 'ACCEPT'
      }).afterClosed().subscribe((accept: boolean) => {
        if (accept) {
          this.store.dispatch(this.actions.removeDebitNoteTask(this.debitNoteTask));
          this.router.navigate(['/secure/billing/debit-notes']);
        } else {
          // DO SOMETHING ELSE
        }
      });
    }
}
