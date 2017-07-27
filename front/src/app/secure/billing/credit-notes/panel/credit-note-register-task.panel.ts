import {Component, OnInit, ViewContainerRef, Input} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {MdSnackBar, MdDialog, MdDialogRef, MdDialogConfig} from '@angular/material';
import {Store} from '@ngrx/store';
import {Observable} from 'rxjs';
import {BillingModuleState} from '../../index';
import {CreditNoteActions} from '../credit-note.action';
import {CreditNoteItemEditorDialog} from '../dialog/credit-note-item-editor.dialog';
import {CreditNoteTask} from '../../../../shared/model/billing/credit-note-task.interface';
import {CreditNoteItem} from '../../../../shared/model/billing/credit-note-item.interface';

@Component({
  selector: 'pams-credit-note-register-task',
  templateUrl: './credit-note-register-task.panel.html',
})

export class CreditNoteRegisterTaskPanel implements OnInit {

  private CREDIT_NOTE_ITEMS = 'billingModuleState.creditNoteItems'.split('.');
  @Input() creditNoteTask: CreditNoteTask;
  creditNoteItems$: Observable<CreditNoteItem[]>;

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

  verify() {
    this.store.dispatch(this.actions.completeCreditNoteTask(this.creditNoteTask));
    this.goBack();
  }

  goBack(): void {
    this.router.navigate(['/secure/billing/credit-notes']);
  }
}
