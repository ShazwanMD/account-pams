 import {ChangeDetectionStrategy, Component, Input, OnInit, ViewContainerRef} from '@angular/core';
 import {MdDialog, MdDialogConfig, MdDialogRef, MdSnackBar} from '@angular/material';
 import {ActivatedRoute, Router} from '@angular/router';
 import {BillingModuleState} from '../../index';
 import {Store} from '@ngrx/store';
 import {CreditNoteActions} from '../credit-note.action';
 import {CreditNote} from '../../../../shared/model/billing/credit-note.interface';
 import {CreditNoteItem} from '../../../../shared/model/billing/credit-note-item.interface';
 import {CreditNoteItemEditorDialog} from '../dialog/credit-note-item-editor.dialog';

 @Component({
   selector: 'pams-credit-note-item-list-only',
   templateUrl: './credit-note-item-list-only.component.html',
   changeDetection: ChangeDetectionStrategy.OnPush,
 })
 export class CreditNoteItemListOnlyComponent {

   private selectedRows: CreditNoteItem[];
   private editorDialogRef: MdDialogRef<CreditNoteItemEditorDialog>;
   private columns: any[] = [
     {name: 'creditNoteItemDate', label: 'Date'},
     {name: 'chargeCode.code', label: 'Charge Code'},
     {name: 'description', label: 'Description'},
     {name: 'balanceAmount', label: 'Amount'},
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
 }
