import {Component, Input, ChangeDetectionStrategy, ViewContainerRef, OnInit} from '@angular/core';
import {MdDialog, MdDialogConfig, MdDialogRef, MdSnackBar} from '@angular/material';
import {ActivatedRoute, Router} from '@angular/router';
import {BillingModuleState} from '../../index';
import {Store} from '@ngrx/store';
import {CreditNote} from '../credit-note.interface';
import {CreditNoteItem} from '../credit-note-item.interface';
import {CreditNoteActions} from '../credit-note.action';
import {CreditNoteItemEditorDialog} from '../dialog/credit-note-item-editor.dialog';

@Component({
  selector: 'pams-credit-note-item-list',
  templateUrl: './credit-note-item-list.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class CreditNoteItemListComponent implements OnInit {

  private selectedRows: CreditNoteItem[];
  private editorDialogRef: MdDialogRef<CreditNoteItemEditorDialog>;
  private columns: any[] = [
    {name: 'creditNoteItemDate', label: 'Date'},
    {name: 'chargeCode.code', label: 'Charge Code'},
    {name: 'description', label: 'Description'},
    {name: 'amount', label: 'Amount'},
    {name: 'action', label: ''},
  ];

  @Input() creditNote: CreditNote;
  @Input() creditNoteItems: CreditNoteItem[];

  constructor(private router: Router,
              private route: ActivatedRoute,
              private actions: CreditNoteActions,
              private store: Store<BillingModuleState>,
              private vcf: ViewContainerRef,
              private dialog: MdDialog,
              private snackbar: MdSnackBar) {
  }

  ngOnInit(): void {
    this.selectedRows = this.creditNoteItems.filter((value) => value.selected);
  }

  createDialog(): void {
    this.showDialog(null);
  }

  edit(creditNoteItems: CreditNoteItem): void {
    this.showDialog(creditNoteItems);
  }

  delete(): void {
     console.log("length: " + this.selectedRows.length);
     for (let i = 0; i < this.selectedRows.length; i++) {
       this.store.dispatch(this.actions.deleteCreditNoteItem(this.creditNote, this.selectedRows[i]));
     }
     this.selectedRows = [];
   }

  filter(): void {
  }

  selectRow(creditNoteItem: CreditNoteItem): void {
  }

  selectAllRows(creditNoteItems: CreditNoteItem[]): void {
  }

  showDialog(creditNoteItem: CreditNoteItem): void {
    console.log('showDialog');
    let config = new MdDialogConfig();
    config.viewContainerRef = this.vcf;
    config.role = 'dialog';
    config.width = '50%';
    config.height = '60%';
    config.position = {top: '0px'};
    this.editorDialogRef = this.dialog.open(CreditNoteItemEditorDialog, config);
    this.editorDialogRef.componentInstance.creditNote = this.creditNote;
    if (creditNoteItem) this.editorDialogRef.componentInstance.creditNoteItem = creditNoteItem; // set
    this.editorDialogRef.afterClosed().subscribe((res) => {
      console.log('close dialog');
    });
  }
}
