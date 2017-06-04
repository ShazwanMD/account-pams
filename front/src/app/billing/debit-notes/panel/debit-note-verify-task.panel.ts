import {Component, OnInit, ViewContainerRef, Input} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
//import {InvoiceItem} from "../invoice-item.interface";
import {MdSnackBar, MdDialog, MdDialogRef, MdDialogConfig} from "@angular/material";
//import {InvoiceItemEditorDialog} from "../dialog/invoice-item-editor.dialog";
//import {InvoiceTask} from "../invoice-task.interface";
//import {InvoiceActions} from "../invoice.action";
import {Store} from "@ngrx/store";
import {Observable} from "rxjs";
import { BillingModuleState } from "../../index";
import { DebitNoteItem } from "../debit-note-item.interface";
import { DebitNoteTask } from "../debit-note-task.interface";
import { DebitNoteActions } from "../debit-note.action";
import { DebitNoteItemEditorDialog } from "../dialog/debit-note-item-editor.dialog";


@Component({
  selector: 'pams-debit-note-verify-task',
  templateUrl: './debit-note-verify-task.panel.html',
})

export class DebitNoteVerifyTaskPanel implements OnInit {

  private DEBIT_NOTE_ITEMS = "billingModuleState.debitNoteItems".split(".");
  private debitNoteItems$: Observable<DebitNoteItem[]>;
  @Input() debitNoteTask: DebitNoteTask;

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

  approve() {
    this.store.dispatch(this.actions.completeDebitNoteTask(this.debitNoteTask));
    this.goBack();
  }

  goBack(): void {
    this.router.navigate(['/billing/debit-notes']);
  }
}
