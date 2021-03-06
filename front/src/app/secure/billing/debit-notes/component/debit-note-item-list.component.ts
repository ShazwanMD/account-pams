 import {Component, Input, ChangeDetectionStrategy, ViewContainerRef, OnInit} from '@angular/core';
 import {MdDialog, MdDialogConfig, MdDialogRef, MdSnackBar} from '@angular/material';
 import {ActivatedRoute, Router} from '@angular/router';
 import {BillingModuleState} from '../../index';
 import {Store} from '@ngrx/store';
 import {DebitNoteActions} from '../debit-note.action';
 import {DebitNoteItemEditorDialog} from '../dialog/debit-note-item-editor.dialog';
 import {DebitNoteItem} from '../../../../shared/model/billing/debit-note-item.interface';
 import {DebitNote} from '../../../../shared/model/billing/debit-note.interface';

 @Component({
   selector: 'pams-debit-note-item-list',
   templateUrl: './debit-note-item-list.component.html',
   changeDetection: ChangeDetectionStrategy.OnPush,
 })
 export class DebitNoteItemListComponent implements OnInit {

   private selectedRows: DebitNoteItem[];
   private editorDialogRef: MdDialogRef<DebitNoteItemEditorDialog>;
   private columns: any[] = [
     {name: 'debitNoteItemDate', label: 'Date'},
     {name: 'chargeCode.code', label: 'Charge Code'},
     {name: 'description', label: 'Description'},
     {name: 'amount', label: 'Amount'},
     {name: 'action', label: ''},
   ];

   @Input() debitNote: DebitNote;
   @Input() debitNoteItems: DebitNoteItem[];

   constructor(private router: Router,
               private route: ActivatedRoute,
               private actions: DebitNoteActions,
               private store: Store<BillingModuleState>,
               private vcf: ViewContainerRef,
               private dialog: MdDialog,
               private snackbar: MdSnackBar) {
   }

   ngOnInit(): void {
     this.selectedRows = this.debitNoteItems.filter((value) => value.selected);
   }

   createDialog(): void {
     this.showDialog(null);
   }

   edit(debitNoteItems: DebitNoteItem): void {
     this.showDialog(debitNoteItems);
   }

   delete(): void {
     console.log("length: " + this.selectedRows.length);
     for (let i = 0; i < this.selectedRows.length; i++) {
       this.store.dispatch(this.actions.deleteDebitNoteItem(this.debitNote, this.selectedRows[i]));
     }
     this.selectedRows = [];
   }

   filter(): void {
   }

   selectRow(debitNoteItem: DebitNoteItem): void {
   }

   selectAllRows(debitNoteItems: DebitNoteItem[]): void {
   }

   showDialog(debitNoteItem: DebitNoteItem): void {
     console.log('showDialog');
     let config = new MdDialogConfig();
     config.viewContainerRef = this.vcf;
     config.role = 'dialog';
     config.width = '50%';
     config.height = '60%';
     config.position = {top: '0px'};
     this.editorDialogRef = this.dialog.open(DebitNoteItemEditorDialog, config);
     this.editorDialogRef.componentInstance.debitNote = this.debitNote;
     if (debitNoteItem) this.editorDialogRef.componentInstance.debitNoteItem = debitNoteItem; // set
     this.editorDialogRef.afterClosed().subscribe((res) => {
      this.selectedRows = []; 
      console.log('close dialog');
     });
   }
 }
