import {Component, OnInit, ViewContainerRef, Input} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
//import {InvoiceItem} from "../invoice-item.interface";
import {MdSnackBar, MdDialog, MdDialogRef, MdDialogConfig} from "@angular/material";
//import {InvoiceItemEditorDialog} from "../dialog/invoice-item-editor.dialog";
//import {InvoiceTask} from "../invoice-task.interface";
//import {InvoiceActions} from "../invoice.action";
import {Store} from "@ngrx/store";
import {BillingModuleState} from "../../index";
import { Observable } from "rxjs/Observable";
import { DebitNoteItem } from "../debit-note-item.interface";
import { DebitNoteTask } from "../debit-note-task.interface";
import { DebitNoteItemEditorDialog } from "../dialog/debit-note-item-editor.dialog";
import { DebitNoteActions } from "../debit-note.action";


@Component({
  selector: 'pams-debit-note-register-task',
  templateUrl: './debit-note-register-task.panel.html',
})

export class DebitNoteRegisterTaskPanel {

  private DEBITNOTE_ITEMS = "billingModuleState.debitNoteItems".split(".");
  @Input() debitNoteTask: DebitNoteTask;
  debitNoteItems$: Observable<DebitNoteItem[]>;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private viewContainerRef: ViewContainerRef,
              private actions: DebitNoteActions,
              private store: Store<BillingModuleState>,
              private dialog: MdDialog,
              private snackBar: MdSnackBar) {
    this.debitNoteItems$ = this.store.select(...this.DEBITNOTE_ITEMS);
  }

  ngOnInit(): void {
    this.store.dispatch(this.actions.findDebitNoteItems(this.debitNoteTask.DebitNote))
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
    this.router.navigate(['/billing/debit-notes']);
  }
}
