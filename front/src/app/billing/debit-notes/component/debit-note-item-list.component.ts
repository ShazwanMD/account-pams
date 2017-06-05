import {Component, Input, ChangeDetectionStrategy, ViewContainerRef, OnInit} from '@angular/core';
import {MdDialog, MdDialogConfig, MdDialogRef, MdSnackBar} from "@angular/material";
import {ActivatedRoute, Router} from "@angular/router";
import {BillingModuleState} from "../../index";
import {Store} from "@ngrx/store";
import { DebitNote } from "../debit-note.interface";
import { DebitNoteItem } from "../debit-note-item.interface";
import { DebitNoteActions } from "../debit-note.action";
import { DebitNoteItemEditorDialog } from "../dialog/debit-note-item-editor.dialog";

@Component({
  selector: 'pams-debit-note-item-list',
  templateUrl: './debit-note-item-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class DebitNoteItemListComponent implements OnInit {

  @Input() debitNote: DebitNote;
  @Input() debitNoteItems: DebitNoteItem[];

  private selectedRows: DebitNoteItem[];
  private editorDialogRef: MdDialogRef<DebitNoteItemEditorDialog>;
  private columns: any[] = [
    {name: 'referenceNo', label: 'ReferenceNo'},
    {name: 'description', label: 'Description'},
    {name: 'totalAmount', label: 'Total Amount'},
    {name: 'flowState', label: 'Status'},
    {name: 'action', label: ''}
  ];

  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: DebitNoteActions,
              private store: Store<BillingModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog,
              private snackbar: MdSnackBar) {
  }

  ngOnInit(): void {
    this.selectedRows = this.debitNoteItems.filter(value => value.selected);
  }

  createDialog(): void {
    this.showDialog(null);
  }

  edit(debitNoteItems: DebitNoteItem): void {
    this.showDialog(debitNoteItems);
  }

  // delete(): void {
  //   console.log("length: " + this.selectedRows.length);
  //   for (var i = 0; i < this.selectedRows.length; i++) {
  //     this.store.dispatch(this.actions.deletedebitNoteItems(this.debitNote, this.selectedRows[i]));
  //   }
  // }

  filter(): void {
  }

  selectRow(debitNoteItem: DebitNoteItem): void {
  }

  selectAllRows(debitNoteItems: DebitNoteItem[]): void {
  }


  showDialog(debitNoteItem: DebitNoteItem): void {
    console.log("showDialog");
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '60%';
    config.position = {top: '0px'};
    this.editorDialogRef = this.dialog.open(DebitNoteItemEditorDialog, config);
    this.editorDialogRef.componentInstance.debitNote= this.debitNote;
    if (debitNoteItem) this.editorDialogRef.componentInstance.debitNoteItem = debitNoteItem; // set
    this.editorDialogRef.afterClosed().subscribe(res => {
      console.log("close dialog");
    });
  }
}
