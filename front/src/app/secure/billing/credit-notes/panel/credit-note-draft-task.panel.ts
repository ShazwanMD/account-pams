import {Component, Input, OnInit, ViewContainerRef} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {MdDialog, MdDialogConfig, MdSnackBar} from '@angular/material';
import {Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {BillingModuleState} from '../../index';
import {CreditNoteItemEditorDialog} from '../dialog/credit-note-item-editor.dialog';
import {CreditNoteActions} from '../credit-note.action';
import {CreditNoteItem} from '../../../../shared/model/billing/credit-note-item.interface';
import {CreditNoteTask} from '../../../../shared/model/billing/credit-note-task.interface';

@Component({
  selector: 'pams-credit-note-draft-task',
  templateUrl: './credit-note-draft-task.panel.html',
})

export class CreditNoteDraftTaskPanel implements OnInit {

  private CREDIT_NOTE_ITEMS: string[] = 'billingModuleState.creditNoteItems'.split('.');
  private creditNoteItems$: Observable<CreditNoteItem[]>;
  @Input() creditNoteTask: CreditNoteTask;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private viewContainerRef: ViewContainerRef,
              private actions: CreditNoteActions,
              private store: Store<BillingModuleState>,
              private dialog: MdDialog,
              private snackBar: MdSnackBar) {
    this.creditNoteItems$ = this.store.select(...this.CREDIT_NOTE_ITEMS);
  }

  ngOnInit(): void {
    console.log('reference no ' + this.creditNoteTask.referenceNo);
    this.store.dispatch(this.actions.findCreditNoteItems(this.creditNoteTask));
  }

  editItem(item: CreditNoteItem) {
    let config = new MdDialogConfig();
    config.viewContainerRef = this.viewContainerRef;
    config.role = 'dialog';
    config.width = '70%';
    config.height = '60%';
    config.position = {top: '0px'};
    let editorDialogRef = this.dialog.open(CreditNoteItemEditorDialog, config);
    editorDialogRef.componentInstance.creditNoteItem = item;
  }

  register() {
    this.store.dispatch(this.actions.completeCreditNoteTask(this.creditNoteTask));
    this.goBack();
  }

  goBack(): void {
    this.router.navigate(['/secure/billing/credit-notes']);
  }
}