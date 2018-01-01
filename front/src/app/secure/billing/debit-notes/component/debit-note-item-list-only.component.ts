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
   selector: 'pams-debit-note-item-list-only',
   templateUrl: './debit-note-item-list-only.component.html',
   changeDetection: ChangeDetectionStrategy.OnPush,
 })
 export class DebitNoteItemListOnlyComponent{

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

 }
